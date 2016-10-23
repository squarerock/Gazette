package squarerock.gazette.model;

/**
 * Created by pranavkonduru on 10/17/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Response {

    @SerializedName("docs")
    @Expose
    private List<Doc> docs = new ArrayList<Doc>();
    @SerializedName("meta")
    @Expose
    private Meta meta;

    /**
     *
     * @return
     * The docs
     */
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     *
     * @param docs
     * The docs
     */
    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

    /**
     *
     * @return
     * The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}