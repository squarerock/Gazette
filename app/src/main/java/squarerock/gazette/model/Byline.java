package squarerock.gazette.model;

/**
 * Created by pranavkonduru on 10/19/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Byline {

    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("person")
    @Expose
    private List<Person> person = new ArrayList<>();

    /**
     *
     * @return
     * The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     *
     * @param organization
     * The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     *
     * @return
     * The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     *
     * @param original
     * The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     *
     * @return
     * The person
     */
    public List<Person> getPerson() {
        return person;
    }

    /**
     *
     * @param person
     * The person
     */
    public void setPerson(List<Person> person) {
        this.person = person;
    }

}
