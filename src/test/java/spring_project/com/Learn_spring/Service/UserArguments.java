package spring_project.com.Learn_spring.Service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import spring_project.com.Learn_spring.Entry.UserEntry;

import java.util.stream.Stream;

public class UserArguments implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(UserEntry.builder().userName("Likith").password("Likith").build()),
                Arguments.of(UserEntry.builder().userName("Charan").password("Charan").build())
        );
    }
}
