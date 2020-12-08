package jobsearch.framework.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ReflectionUtility {

    public static void InitializeModel(Object instance, Map<String, String> valueStore) throws InvocationTargetException, IllegalAccessException {
        for (String key : valueStore.keySet()) {
            BeanUtils.setProperty(instance, key.trim(), valueStore.get(key).trim());
        }
    }
}
