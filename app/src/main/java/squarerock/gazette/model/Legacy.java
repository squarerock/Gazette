package squarerock.gazette.model;

/**
 * Created by pranavkonduru on 10/19/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Legacy {

    @SerializedName("wide")
    @Expose
    private String wide;
    @SerializedName("wideheight")
    @Expose
    private String wideheight;
    @SerializedName("widewidth")
    @Expose
    private String widewidth;

    /**
     *
     * @return
     * The wide
     */
    public String getWide() {
        return wide;
    }

    /**
     *
     * @param wide
     * The wide
     */
    public void setWide(String wide) {
        this.wide = wide;
    }

    /**
     *
     * @return
     * The wideheight
     */
    public String getWideheight() {
        return wideheight;
    }

    /**
     *
     * @param wideheight
     * The wideheight
     */
    public void setWideheight(String wideheight) {
        this.wideheight = wideheight;
    }

    /**
     *
     * @return
     * The widewidth
     */
    public String getWidewidth() {
        return widewidth;
    }

    /**
     *
     * @param widewidth
     * The widewidth
     */
    public void setWidewidth(String widewidth) {
        this.widewidth = widewidth;
    }

}
