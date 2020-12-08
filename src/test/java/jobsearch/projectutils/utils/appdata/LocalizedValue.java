package jobsearch.projectutils.utils.appdata;

import jobsearch.framework.utils.Localizer;
import jobsearch.framework.utils.Utils;

import javafx.util.Pair;

import java.util.Locale;

public enum LocalizedValue {
    BTN_EDIT,
    BTN_UPDATE,
    BTN_CANCEL,
    BTN_DELETE,
    ;
    private Localizer localizer = new Localizer(Locale.getDefault());

    private String handleTerminology(String value){
//        Pair<String,String> result = Utils.getValueViaRegexGroup(Localizer.PROPERTY_VAR_REGEX, value);
//        if (result == null)
//            return value;
//        return value.replace(result.getKey(), localizer.getLocalizedText(result.getValue()));
        return "";
    }

    @Override
    public String toString() {
        return handleTerminology(localizer.getLocalizedText(name()));
    }

    public String get() {
        return toString();
    }
}
