package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "stepDefinitions",
        features = "@target/rerun.txt"
)

public class FailedTestRunner {
    /*
    we can re-run only failed scenarios whether it comes from scenario outline iteration or a single scenario
     */
}
