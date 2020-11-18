package pl.jsystem.qa.qajunit.junittest;

import org.junit.jupiter.api.*;
import pl.jsystem.qa.qajunit.ConfigJunit;
import pl.jsystems.qa.junit.GamePlay;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Junit test")
public class JunitTest extends ConfigJunit {

    private static final String STRING_TESTOWY = "stringTestowy";


    @RepeatedTest(5)
    @DisplayName("First juint test")
    @Test
    public void firstJunittest() {
        assertEquals(5, 2 + 3);
        assertNotEquals(4, 2 + 3);
        assertTrue(STRING_TESTOWY.contains("str"));
        assertTrue(STRING_TESTOWY.endsWith("wy"));

    }

    @DisplayName("Second juint test")
    @Test
    public void secondJunittest() {
        System.out.println(0.2 * 0.2);
        double result = new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue();
        assertEquals(0.04, result);
        assertNotEquals(4, 0.2 * 0.2, 0.0);
    }

    @DisplayName("String test")
    @Test
    public void stringtest() {
        String simpleString = "simpleString";
        String simple = "simpleString";

        String simpleString_1 = "simpleString";
        String simpleString_2 = "simpleString";

        assertSame("simpleString", simpleString);
        assertSame(simpleString, simple);
        assertSame(simpleString, simpleString_1);
        assertSame(simpleString_1, simpleString_2);
    }

    @DisplayName("google test")
    @Test
    public void googleTruthTest() {
        assertThat(STRING_TESTOWY).contains("string");
    }

    @Test
    public void zad1() {
        String resultString = "Wordpres powers 100% of the internet";
        String expectedString = "Wordpres powers [number]% of the internet";
        assertThat(resultString).startsWith("Wordpres powers");
        assertThat(resultString.endsWith("the internet"));
        assertThat(resultString).matches("Wordpres powers \\d+% of the internet");
        String result = resultString.replace("Wordpres powers ", "").replace("% of the internet", "");
        assertThat(result).matches("^\\d+$");
        int resultNumber = Integer.parseInt(result);
        assertThat(resultNumber > 0);
    }

    @DisplayName("nested Suite")
    @Nested
    public class NestedTest {

        @DisplayName("list test")
        @Test
        public void listTest() {
            List<Integer> result = Arrays.asList(1, 2, 3, 4, 5);
            List<Integer> expected = Arrays.asList(3, 4, 5);
            assertThat(result).containsAnyIn(expected);
            assertThat(result.containsAll(expected));
            assertThat(result).hasSize(5);
            assertThat(result).containsAnyOf(1, 2, 3);

        }
        @DisplayName("Exception test")
        @Test
        public void exceptionTest() {
            GamePlay gamePlay = new GamePlay();
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> {
                        gamePlay.play(0);
                    }
                    );
        }
    }
}
