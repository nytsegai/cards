package jobsearch.framework.utils;

import jobsearch.framework.logger.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localizer {

    public static final String PROPERTY_VAR_REGEX = "\\$\\{(?<key>[A-Za-z_\\s]+)\\}";
    private String resourceBundle;
    private Locale locale;
    protected final Logger logger = Logger.getInstance(Localizer.class);

    public Localizer(Locale locale) {
        this.locale = locale;
        this.resourceBundle = PathUtility.LOC_RESOURCE_BUNDLE;
    }

    public Localizer(Locale locale, String bundle) {
        this.locale = locale;
        this.resourceBundle = bundle;
    }

    public String getLocalizedText(String key)
    {
        try
        {
            ResourceBundle bundle = ResourceBundle.getBundle(resourceBundle,
                    locale, this.getClass().getClassLoader());

            if (bundle.keySet().contains(key)) {
                return bundle.getString(key);
            }
            else {
                return key + "(No localization entry found)";
            }
        }
        catch (Exception e)
        {
            logger.error(e.getStackTrace());
            return "LOCALIZATION FAILED: " + e.toString();
        }
    }

    public Locale getLocale() { return this.locale; }

}
