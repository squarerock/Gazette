package squarerock.gazette.model;

/**
 * Created by pranavkonduru on 10/17/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Doc {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("print_page")
    @Expose
    private String printPage;
    @SerializedName("blog")
    @Expose
    private List<String> blog = new ArrayList<String>();
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("keywords")
    @Expose
    private List<Keywords> keywords;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desK")
    @Expose
    private String newsDesK;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
   /* @SerializedName("byline")
    @Expose
    private Byline byline;*/
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private String wordCount;
    @SerializedName("slideshow_credits")
    @Expose
    private String slideshowCredits;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedia> multimedia = new ArrayList<>();

    /**
     *
     * @return
     * The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     *
     * @param webUrl
     * The web_url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     *
     * @return
     * The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     *
     * @param snippet
     * The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     *
     * @return
     * The leadParagraph
     */
    public String getLeadParagraph() {
        return leadParagraph;
    }

    /**
     *
     * @param leadParagraph
     * The lead_paragraph
     */
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    /**
     *
     * @return
     * The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     *
     * @param _abstract
     * The abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     *
     * @return
     * The printPage
     */
    public String getPrintPage() {
        return printPage;
    }

    /**
     *
     * @param printPage
     * The print_page
     */
    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    /**
     *
     * @return
     * The blog
     */
    public List<String> getBlog() {
        return blog;
    }

    /**
     *
     * @param blog
     * The blog
     */
    public void setBlog(List<String> blog) {
        this.blog = blog;
    }

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return
     * The headline
     */
    public Headline getHeadline() {
        return headline;
    }

    /**
     *
     * @param headline
     * The headline
     */
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    /**
     *
     * @return
     * The keywords
     */
    public List<Keywords> getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(List<Keywords> keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     * The pub_date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     * The documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     *
     * @param documentType
     * The document_type
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     *
     * @return
     * The newsDesK
     */
    public String getNewsDesk() {
        return newsDesK;
    }

    /**
     *
     * @param newsDesK
     * The news_desK
     */
    public void setNewsDesk(String newsDesK) {
        this.newsDesK = newsDesK;
    }

    /**
     *
     * @return
     * The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     *
     * @param sectionName
     * The section_name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     *
     * @return
     * The subsectionName
     */
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     *
     * @param subsectionName
     * The subsection_name
     */
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    /**
     *
     * @return
     * The byline
     *//*
    public Byline getByline() {
        return byline;
    }

    *//**
     *
     * @param byline
     * The byline
     *//*
    public void setByline(Byline byline) {
        this.byline = byline;
    }
*/
    /**
     *
     * @return
     * The typeOfMaterial
     */
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     *
     * @param typeOfMaterial
     * The type_of_material
     */
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The wordCount
     */
    public String getWordCount() {
        return wordCount;
    }

    /**
     *
     * @param wordCount
     * The word_count
     */
    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    /**
     *
     * @return
     * The slideshowCredits
     */
    public String getSlideshowCredits() {
        return slideshowCredits;
    }

    /**
     *
     * @param slideshowCredits
     * The slideshow_credits
     */
    public void setSlideshowCredits(String slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
    }

    /**
     *
     * @return
     * The multimedia
     */
    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    /**
     *
     * @param multimedia
     * The multimedia
     */
    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

}