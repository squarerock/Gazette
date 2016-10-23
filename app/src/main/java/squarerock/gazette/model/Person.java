package squarerock.gazette.model;

/**
 * Created by pranavkonduru on 10/19/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("qualifier")
    @Expose
    private String qualifier;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("middlename")
    @Expose
    private String middlename;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("organization")
    @Expose
    private String organization;

    /**
     *
     * @return
     * The qualifier
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     *
     * @param qualifier
     * The qualifier
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     * The middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     *
     * @param middlename
     * The middlename
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     * The rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     * The rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
    }

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

}