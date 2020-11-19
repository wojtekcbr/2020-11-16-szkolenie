package pl.jsystems.qa.qagui.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "classpath:pl.jsystems.qa.qagui.cucumber",
        plugin = { "pretty", "summary", "html:target/cucumber/report.html", "json:target/cucumber.json",
                "juint:target/cucumber,xml",
                "return:target/cucumber.txt"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,

        tags = ""
             + "not " +
                    "@userpane" +
                    " and " +
            "not " +
                    "@wordpress" +
                    " and " +
            "not " +
                    "@login"
                            +
                    " and " +
            "not " +
                    "@BDD" +
                    " and " +
            "not " +
                    "@search" +
                    " and " +
            "not " +
                    "@website" +
                    " and " +
            "not " +
                    "@website_2" +
                    " and " +
            "not " +
                "@website_3"
)
public class RunTest {


}
