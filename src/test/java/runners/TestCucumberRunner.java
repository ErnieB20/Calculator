package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = false,
        features = "src/test/resources/features",
        glue = "stepDefinitions",
        tags = "@smoke",
        dryRun = false,
        plugin = {
                "pretty",
                "rerun:target/rerun.txt",
                "json:target/report.json",
                "html:target/default-cucumber-report.html"}
)
public class TestCucumberRunner {
}
