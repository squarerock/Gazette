package squarerock.gazette.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by pranavkonduru on 10/19/16.
 */

public class Utilities {

    public static void loadImage(Context context, String imagePath, ImageView imageView){
//        loadWithGlide(context, imagePath, imageView);

        loadWithPicasso(context, imagePath, imageView);
    }

    private static void loadWithPicasso(Context context, String imagePath, ImageView imageView) {
        Picasso.with(context)
                .load(imagePath)
                .transform(new RoundedCornersTransformation(5, 1))
                .into(imageView);
    }

    private static void loadWithGlide(Context context, String imagePath, ImageView imageView) {
        Glide.with(context)
                .load(imagePath)
                .into(imageView);
    }

}
