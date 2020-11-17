package pl.jsystem.qa.qajunit.junittest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tags({@Tag("paramTest"), @Tag("junit")})
@DisplayName("Parameterized Test")
public class JunitParameterizedTest {

    @Tag("junit")
    @DisplayName("First paramerized test")
    @ParameterizedTest(name = "Parameter test witch value 5.")
    @ValueSource(ints = {5, 15, 20})
    public void firstParamtest (int number) {
        assertEquals(0, number % 5);
    }

    @DisplayName("Paramerized test say hello")
    @ParameterizedTest(name = "Parametrized test: {0}")
    @ValueSource(strings = {"Hello", "Hello Junit", "Hello students"})
    public void seconParamerizedTest (String param) {
        assertThat(param).contains("Hello");
    }

    @DisplayName("Parame test with multi param")
    @ParameterizedTest(name = "Parametrized test fort multi param: {0}, {1}")
    @CsvSource(value = {"Hello; 5", "Hello junit; 15", "'Hello, students'; 35"}, delimiter = ';')
    public void paramMultiTest (String text, int number) {
        assertThat(text).contains("Hello");
        assertEquals(0, number % 5);
    }

    @DisplayName("Parame test with value from csv file")
    @ParameterizedTest(name = "Param test fort multi param: {0}, {1}")
    @CsvFileSource(resources = "/plik.csv", delimiter = ',')
    public void paramMultiFileTest(String text, int number) {
        assertThat(text).contains("Hello");
        assertEquals(0, number % 5);
    }

    @DisplayName("Enum param test")
    @ParameterizedTest(name = "Enum param test: {0}")
    @EnumSource(value = ParamEnum.class)
    public void enumTest(ParamEnum paramEnum) {
        assertThat(paramEnum.toString()).contains("ENUM");
    }

    @Tag("wordpress")
    @DisplayName("Wordpress test")
    @ParameterizedTest(name = "Wordpress test {0}")
    @ValueSource(strings = {"1", "1000", "10000"})
    public void wordPressTeast (String value) {
        String resultString = "Wordpres powers " + value + "% of the internet";

        assertThat(resultString).startsWith("Wordpres powers");
        assertThat(resultString.endsWith("the internet"));
        assertThat(resultString).matches("Wordpres powers \\d+% of the internet");

        String result = resultString.replace("Wordpres powers ", "").replace("% of the internet", "");

        assertThat(result).matches("^\\d+$");
        int resultNumber = Integer.parseInt(result);
        assertThat(resultNumber > 0);
    }

    enum ParamEnum {
        ENUM_ONE,
        ENUM_TWO
    }
}
