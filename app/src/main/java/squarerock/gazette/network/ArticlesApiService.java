package squarerock.gazette.network;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import squarerock.gazette.model.Articles;
import squarerock.gazette.utils.Constants;

/**
 * Created by pranavkonduru on 10/17/16.
 */

public class ArticlesApiService {

    private ArticlesApi api;

    public ArticlesApiService(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.NYT_BASE_URL)
                .build();

        api = retrofit.create(ArticlesApi.class);
    }

    public void getArticles(String query, HashMap<String, String> options, Callback<Articles> callback){
        Call<Articles> call = api.getArticles(query, options, Constants.NYT_API_KEY);
        call.enqueue(callback);
    }
}
