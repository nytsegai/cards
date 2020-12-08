package jobsearch.projectutils.data.dataobjects;

import jobsearch.projectutils.utils.appdata.ApplicationConfig;

/**
 * @author Shawn P. Conlin <sconlin@practicevelocity.com>
 *
 */
public class Config {
    private String serverURL;
    private String version;
    private String userName;
    private String passWord;
    private String homePage;

    /**
     *
     */
    public Config() {
        this.serverURL = ApplicationConfig.FULL_BASE_URL;
        this.version = "";
        this.homePage = "PVM > Announcement";
    }

    /**
     *
     */
    public Config(String URL, String ver, String user, String pw, String home) {
        this.serverURL = URL;
        this.version = ver;
        this.userName = user;
        this.passWord = pw;
        this.homePage = home;
    }

    /**
     * @return the serverURL
     */
    public String getServerURL() {
        return serverURL;
    }

    /**
     * @param serverURL the serverURL to set
     */
    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the passWord
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * @param passWord the passWord to set
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * @return the homePage
     */
    public String getHomePage() {
        return homePage;
    }

    /**
     * @param homePage the homePage to set
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

}
