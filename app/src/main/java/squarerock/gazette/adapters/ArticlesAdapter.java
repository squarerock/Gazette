package squarerock.gazette.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import squarerock.gazette.R;
import squarerock.gazette.interfaces.CustomTabsLoader;
import squarerock.gazette.model.Doc;
import squarerock.gazette.model.Multimedia;
import squarerock.gazette.utils.Constants;
import squarerock.gazette.utils.DynamicHeightImageView;
import squarerock.gazette.utils.Utilities;

/**
 * Created by pranavkonduru on 10/19/16.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Doc> docs;
    private Context context;
    private CustomTabsLoader loader;
    private static final String TAG = "ArticlesAdapter";

    /**
     * Default constructor
     * @param loader Pass custom tabs loader
     * @param docs Pass a list of docs
     */
    public ArticlesAdapter(CustomTabsLoader loader, List<Doc> docs) {
        this.docs = docs;
        this.loader = loader;
    }

    @Override
    public int getItemViewType(int position) {
        Doc doc = docs.get(position);
        boolean shouldLoadImages = false;

        for(Multimedia multimedia : doc.getMultimedia()){
            if(Constants.EXTRA_SUBTYPE_WIDE.equals(multimedia.getSubtype())
                    || Constants.EXTRA_SUBTYPE_XLARGE.equals(multimedia.getSubtype())){
                shouldLoadImages = true;
                break;
            } else if(Constants.EXTRA_SUBTYPE_THUMBNAIL.equals(multimedia.getSubtype())){
                shouldLoadImages = false;
            }
        }

        if(shouldLoadImages){
            return R.layout.item_article;
        } else {
            return R.layout.item_article_text;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        if(viewType == R.layout.item_article) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
            return new ArticlesViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article_text, parent, false);
            return new ArticlesTextViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Doc doc = docs.get(position);
        Log.d(TAG, "onBindViewHolder: "+position);
        Log.d(TAG, "onBindViewHolder: "+getItemCount());

        if(viewHolder.getItemViewType() == R.layout.item_article) {
            ArticlesViewHolder holder = (ArticlesViewHolder) viewHolder;
            if (doc.getMultimedia().size() > 0) {
                for (Multimedia multimedia : doc.getMultimedia()) {
                    if (Constants.EXTRA_TYPE_IMAGE.equals(multimedia.getType()) &&
                            (Constants.EXTRA_SUBTYPE_XLARGE.equals(multimedia.getSubtype()))
                            || Constants.EXTRA_SUBTYPE_WIDE.equals(multimedia.getSubtype())) {
                        Utilities.loadImage(context, Constants.NYT_IMAGE_BASE_URL + multimedia.getUrl(), holder.ivArticleImage);
                        break;
                    }
                }
            }

            holder.ivArticleImage.setHeightRatio(1.0);
            holder.tvHeadline.setText(doc.getHeadline().getMain());
        } else {
            ArticlesTextViewHolder holder = (ArticlesTextViewHolder) viewHolder;
            holder.tvArticlesText.setText(doc.getHeadline().getMain());
        }
    }

    @Override
    public int getItemCount() {
        return docs.size();
    }

    /**
     * update items in adapter
     * @param docsList new items that need to be added
     */
    public void updateItems(List<Doc> docsList){
        int currSize = getItemCount();
        docs.addAll(docs.size(), docsList);
        notifyItemRangeInserted(currSize, docsList.size());
    }

    /**
     * clear items in adapter
     */
    public void clearItems(){
        docs.clear();
        notifyDataSetChanged();
    }

    public class ArticlesViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, Target{
        private DynamicHeightImageView ivArticleImage;
        private TextView tvHeadline;

        public ArticlesViewHolder(View itemView) {
            super(itemView);

            ivArticleImage = (DynamicHeightImageView) itemView.findViewById(R.id.iv_news_image);
            tvHeadline = (TextView) itemView.findViewById(R.id.tv_headline);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            float ratio = (float) bitmap.getHeight() / (float) bitmap.getWidth();
            // Set the ratio for the image
            ivArticleImage.setHeightRatio(ratio);
            // Load the image into the view
            ivArticleImage.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Doc doc = docs.get(position);
                if (loader != null) {
                    loader.loadCustomTab(doc.getWebUrl());
                }
            }
        }
    }

    public class ArticlesTextViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{
        private TextView tvArticlesText;

        public ArticlesTextViewHolder(View itemView) {
            super(itemView);
            tvArticlesText = (TextView) itemView.findViewById(R.id.tv_text_heading);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                Doc doc = docs.get(position);
                if (loader != null) {
                    loader.loadCustomTab(doc.getWebUrl());
                }
            }
        }
    }
}
