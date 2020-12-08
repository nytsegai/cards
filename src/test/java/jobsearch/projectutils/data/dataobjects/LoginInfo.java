package jobsearch.projectutils.data.dataobjects;

import jobsearch.framework.utils.ReflectionUtility;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Add class description here;
 * <p>
 *
 * @author Iryna.Zhukava
 * Created  on 11/8/2018
 */
public class LoginInfo {
    private String UserName;
    private String Password;
    private String LandingPage;

    public LoginInfo(Map<String, String> valueStore) throws InvocationTargetException, IllegalAccessException {
        ReflectionUtility.InitializeModel(this, valueStore);
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        this.UserName = userName;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public String getLandingPage() {
        return LandingPage;
    }
    public void setLandingPage(String landingPage) {
        LandingPage = landingPage;
    }

}