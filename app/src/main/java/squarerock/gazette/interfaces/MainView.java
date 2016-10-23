package squarerock.gazette.interfaces;

import android.view.View;

import squarerock.gazette.model.Articles;

/**
 * Created by pranavkonduru on 10/22/16.
 */

public interface MainView {

    /**
     * Call this to update the recycler view with the articles from API
     * @param articles Articles that need to be shown
     */
    void updateArticlesView(Articles articles);

    /**
     * Generic error function using SnackBar
     * @param message Message that needs to be shown
     * @param actionMessage Text for action message
     * @param duration Duration the message needs to be shown
     * @param listener Listener for click of action
     */
    void showError(String message, String actionMessage, int duration, View.OnClickListener listener);

    /**
     * Check if the swipe refresh view is refreshing
     * @return true if refreshing. false if not
     */
    boolean isRefreshing();

    /**
     * Set the state of refresh for swipe refresh view
     * @param state state that needs to be set
     */
    void setRefreshing(boolean state);

    /**
     * Check if device is connected to Network.
     * @return true if connected. false if not
     */
    boolean isNetworkAvailable();

    /**
     * Get string resource from ID
     * @param resId String resource ID
     * @return String resource
     */
    String getStringResource(int resId);

    /**
     * Use this to set title of Toolbar
     * @param title Title that needs to be set.
     */
    void setTitle(String title);

}
