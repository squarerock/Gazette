package squarerock.gazette.interfaces;

/**
 * Created by pranavkonduru on 10/22/16.
 */

public interface MainPresenter {

    /**
     * Call this to fetch articles from NYT API
     * @param query Query that needs to be searched for
     * @param page page that needs to be returned from search
     */
    void fetchArticles(String query, String page);

    /**
     * Call this before destroying the view.
     * This nulls the view in presenter
     */
    void destroyView();

}
