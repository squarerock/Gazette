package squarerock.gazette.network;

/**
 * Created by pranavkonduru on 10/17/16.
 */

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import squarerock.gazette.model.Articles;

public interface ArticlesApi {

    @GET("v2/articlesearch.json")
    Call<Articles> getArticles(
            @Query("q") String query,
            @QueryMap(encoded = true) Map<String, String> options,
            @Query("api-key") String key);
}
