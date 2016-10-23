package squarerock.gazette.presenter;

import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import squarerock.gazette.R;
import squarerock.gazette.interfaces.MainPresenter;
import squarerock.gazette.interfaces.MainView;
import squarerock.gazette.model.Articles;
import squarerock.gazette.network.ArticlesApiService;
import squarerock.gazette.utils.Constants;
import squarerock.gazette.utils.Preferences;

/**
 * Created by pranavkonduru on 10/22/16.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private ArticlesApiService apiService;
    private static final String TAG = "MainPresenterImpl";

    public MainPresenterImpl(MainView view) {
        this.view = view;
        apiService = new ArticlesApiService();
    }

    @Override
    public void fetchArticles(final String query, String page) {
        if(view.isNetworkAvailable()) {
            Log.d(TAG, "fetchArticles: ");
            if (!TextUtils.isEmpty(query)) {
                view.setTitle(query);
            }
            apiService.getArticles(query, getOptionsMap(page), new Callback<Articles>() {
                @Override
                public void onResponse(Call<Articles> call, Response<Articles> response) {
                    if (view.isRefreshing()) {
                        view.setRefreshing(false);
                    }
                    if (response.body() != null) {
                        view.updateArticlesView(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Articles> call, final Throwable t) {
                    if (view.isRefreshing()) {
                        view.setRefreshing(false);
                    }
                    view.showError(t.getMessage(), null, Snackbar.LENGTH_LONG, null);
                    Log.e(TAG, "onFailure: " + t.getMessage());
                }
            });
        } else {
            if (view.isRefreshing()) {
                view.setRefreshing(false);
            }

            view.showError(view.getStringResource(R.string.no_internet_connection),
                    view.getStringResource(R.string.retry),
                    Snackbar.LENGTH_INDEFINITE, v -> fetchArticles(query, "0"));
        }
    }

    @Override
    public void destroyView() {
        view = null;
    }

    @NonNull
    private HashMap<String, String> getOptionsMap(String page) {
        HashMap<String, String> optionsMap = new HashMap<>();
        optionsMap.put(Constants.QUERY_OPTION_PAGE, page);

        String sortOrder = Preferences.getString(Preferences.PREF_KEY_SORT_ORDER);
        if(!sortOrder.isEmpty()) {
            optionsMap.put(Constants.QUERY_OPTION_SORT, sortOrder);
        }

        String beginDate = Preferences.getString(Preferences.PREF_KEY_START_DATE);
        if(!beginDate.isEmpty()) {
            optionsMap.put(Constants.QUERY_OPTION_BEGIN_DATE, beginDate);
        }

        String endDate = Preferences.getString(Preferences.PREF_KEY_END_DATE);
        if(!endDate.isEmpty()) {
            optionsMap.put(Constants.QUERY_OPTION_END_DATE, endDate);
        }

        if(getNewsDeskItems() != null){
            optionsMap.put(Constants.QUERY_OPTION_NEWSDESK, getNewsDeskItems());
        }
        return optionsMap;
    }

    private String getNewsDeskItems(){
        StringBuilder builder = new StringBuilder();
        builder.append("(");

        if(Preferences.getBoolean(Preferences.PREF_KEY_NEWSDESK_ARTS)){
            builder.append("Arts ");
        }
        if(Preferences.getBoolean(Preferences.PREF_KEY_NEWSDESK_FASHION)){
            builder.append("Fashion & Style ");
        }
        if(Preferences.getBoolean(Preferences.PREF_KEY_NEWSDESK_SPORTS)){
            builder.append("Sports");
        }

        if(builder.length() > 1){
            builder.append(")");
            Log.d(TAG, "getNewsDeskItems: "+builder.toString());
            return builder.toString();
        } else{
            return null;
        }
    }
}
