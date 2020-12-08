package jobsearch.framework.testmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.opentest4j.AssertionFailedError;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * Implementation of the new custom Arguments Source that reads arguments from array json file
 * <p>
 * type descried the Type of the object that we are reading.
 * resource path is the path to json file in the resource folder
 *
 * @author Iryna.Zhukava
 * Created  on 3/20/2019
 */
public class ArrayJsonArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<ArrayJsonSource> {

    private String resourcePath;
    private Class<?> type;

    @Override
    public void accept(final ArrayJsonSource annotation) {
        resourcePath = annotation.resourcePath();
        type = annotation.type();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext context) {
        ObjectMapper mapper = new ObjectMapper();
        ClassLoader classLoader = JsonArgumentsProvider.class.getClassLoader();
        TypeFactory factory = mapper.getTypeFactory();

        CollectionType listType =
            factory.constructCollectionType(List.class, type);
        List <Object> pojo = null;

        try {
            pojo = mapper.readValue(new File(classLoader.getResource(resourcePath).getFile()), listType);
        } catch (IOException e) {
            throw new AssertionFailedError("The data object for the test hasn't been created" + e.getLocalizedMessage());
        }
        return Stream.of(pojo).map(Arguments::of);
    }
}