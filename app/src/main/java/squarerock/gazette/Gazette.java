package squarerock.gazette;

import android.app.Application;
import android.content.Context;

/**
 * Created by pranavkonduru on 10/22/16.
 */

public class Gazette extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
