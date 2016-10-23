package squarerock.gazette.activities;

import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import squarerock.gazette.R;
import squarerock.gazette.adapters.ArticlesAdapter;
import squarerock.gazette.databinding.ActivityMainBinding;
import squarerock.gazette.fragments.FilterDialogFragment;
import squarerock.gazette.interfaces.CustomTabsLoader;
import squarerock.gazette.interfaces.MainView;
import squarerock.gazette.model.Articles;
import squarerock.gazette.model.Doc;
import squarerock.gazette.presenter.MainPresenterImpl;
import squarerock.gazette.utils.Constants;
import squarerock.gazette.utils.EndlessRecyclerViewScrollListener;

public class MainActivity extends AppCompatActivity implements
        FilterDialogFragment.FilterDialogListener,
        SwipeRefreshLayout.OnRefreshListener,
        CustomTabsLoader, MainView {

    Toolbar toolbar;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    private static final String TAG = "MainActivity";
    private ArticlesAdapter adapter;
    private SearchView searchView;
    private String querySearched;
    private MainPresenterImpl presenter;
    private ActivityMainBinding binding;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // bind views
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        toolbar = binding.tbMainActivity;
        swipeRefreshLayout = binding.swipeContainer;

        // init views
        swipeRefreshLayout.setOnRefreshListener(this);
        setSupportActionBar(toolbar);
        initRecyclerView();

        presenter = new MainPresenterImpl(this);
        presenter.fetchArticles(Constants.DEFAULT_SEARCH_STRING, Constants.DEFAULT_PAGE);
    }

    /**
     * Reset scroll state upon onResume to a fresh state.
     */
    @Override
    protected void onResume() {
        super.onResume();
        scrollListener.reset();
    }


    /**
     * Inflate a SearchView with a query text listener. Fetch articles upon submission.
     * @param menu Menu that will be inflated upon creation of options
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.menu_main_activity_toolbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                querySearched = query;
                scrollListener.reset();
                searchView.clearFocus();
                adapter.clearItems();
                presenter.fetchArticles(query, Constants.DEFAULT_PAGE);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setQueryHint(getStringResource(R.string.search_string));
        searchView.setIconified(false);
        return true;
    }

    /**
     * Launch Filter fragment upon clicking of Filter button on Toolbar
     * @param item Menu item that was selected
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected: "+item.getItemId());
        if(item.getItemId() == R.id.action_filter){
            FragmentManager fm = getSupportFragmentManager();
            FilterDialogFragment editNameDialogFragment = FilterDialogFragment.newInstance("Filter");
            editNameDialogFragment.show(fm, "fragment_filter");
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback from Filter fragment. Fetch articles with new settings and query
     */
    @Override
    public void onPreferencesSaved() {
        Log.d(TAG, "onPreferencesSaved: ");
        adapter.clearItems();
        presenter.fetchArticles(querySearched, Constants.DEFAULT_PAGE);
    }

    /**
     * Called when swipe refresh is executed
     */
    @Override
    public void onRefresh() {
        if(querySearched == null){
            querySearched = Constants.DEFAULT_SEARCH_STRING;
        }
        adapter.clearItems();
        presenter.fetchArticles(querySearched, Constants.DEFAULT_PAGE);
    }

    /**
     * Called from onClick of recycler view items.
     * @param uri The URL that needs to loaded in Custom Tab.
     */
    @Override
    public void loadCustomTab(String uri) {
        Log.d(TAG, "loadCustomTab: "+uri);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.colorAccent));

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_share_custom_tab);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, uri);
        int requestCode = 100;

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setActionButton(bitmap, "Share Link", pendingIntent, true);

        CustomTabsIntent customTabsIntent = builder.build();

        customTabsIntent.launchUrl(this, Uri.parse(uri));
    }

    /**
     * Check if network is available
     * @return true if available. false if not.
     */
    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /**
     * Get a string resource for the resource ID that was passed
     * @param resId resource
     * @return String for the corresponding resource
     */
    @Override
    public String getStringResource(int resId) {
        return getString(resId);
    }

    /**
     * Will set the title of the toolbar as the search query. Called from search
     * @param title Title that needs to be set
     */
    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    /**
     * Call back from presented to update the views with new articles
     * @param articles Articles that need to be shown
     */
    @Override
    public void updateArticlesView(Articles articles) {
        adapter.updateItems(articles.getResponse().getDocs());
    }

    /**
     * Generic error function which is shown in SnackBar
     * @param message Message that needs to be shown
     * @param actionMessage Any action message
     * @param duration How long the SnackBar needs to be shown
     * @param listener Listener for the action
     */
    @Override
    public void showError(String message, String actionMessage, int duration, View.OnClickListener listener) {
        Snackbar.make(recyclerView, message, duration)
                .setAction(actionMessage, listener)
                .show();
    }

    /**
     * Returns if the view is refreshing
     * @return true if refreshing. false if not.
     */
    @Override
    public boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    /**
     * Will set the view refreshing state.
     * @param state state that needs to be set.
     */
    @Override
    public void setRefreshing(boolean state) {
        swipeRefreshLayout.setRefreshing(state);
    }

    /**
     * Detroy presenter view upon onDestroy to avoid leaks
     */
    @Override
    protected void onDestroy() {
        if(presenter != null){
            presenter.destroyView();
        }
        super.onDestroy();
    }

    /**
     * initialize RecyclerView
     */
    private void initRecyclerView() {
        recyclerView = binding.rvArticles;

        adapter = new ArticlesAdapter(this, new ArrayList<Doc>());
        recyclerView.setAdapter(adapter);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                Log.d(TAG, "onLoadMore: "+page);
                presenter.fetchArticles(querySearched, String.valueOf(page));
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

    }

}
