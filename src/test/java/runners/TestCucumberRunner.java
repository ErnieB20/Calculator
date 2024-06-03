package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty",
                "rerun:target/rerun.txt",
                "json:target/report.json",
                "html:target/cucumber-html-report",},
        tags = "@calc_all_env",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true

)
public class TestCucumberRunner {
}
