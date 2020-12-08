package jobsearch.framework.testmanagement;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Describes the custom extension to create a new Arguments Source that reads arguments from json file
 * <p>
 *
 * @author Iryna.Zhukava
 * Created  on 3/20/2019
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JsonArgumentsProvider.class)
public @interface JsonSource {

    String resourcePath();

    Class<?> type();
}