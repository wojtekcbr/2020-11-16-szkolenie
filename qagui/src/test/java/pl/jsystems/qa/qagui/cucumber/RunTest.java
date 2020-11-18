package pl.jsystems.qa.qagui.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "classpath:pl.jsystems.qa.qagui.cucumber",
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json",
                "juint:target/cucumber,xml",
                "return:target/cucumber.txt"
        },

        tags = {
        "@userpanel",
        "@wordpress",

        }

)
public class RunTest {


}
