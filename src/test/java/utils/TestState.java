package utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestState {

    private Map<String, String> page;

    public TestState(){
        this.page = new HashMap<>();
    }

    public Map<String, String> getPage() {
        return page;
    }

    public void setPage(Map<String, String> page) {
        this.page = page;
    }

    public synchronized By get(String locator) {
        String result = getPage().get(locator);
        if (result == null) {
            throw new NullPointerException("Verify Following Page Object Mapped Correctly: " + locator);
        }
        String[] parts = result.split(":", 2);
        String key = parts[0].trim();
        String value = parts[1].trim();

        return switch (key) {
            case "id" -> By.id(value);
            case "name" -> By.name(value);
            case "xpath" -> By.xpath(value);
            case "css" -> By.cssSelector(value);
            case "classname" -> By.className(value);
            case "linkText" -> By.linkText(value);
            case "partialLinkText" -> By.partialLinkText(value);
            case "tagName" -> By.tagName(value);
            default -> throw new IllegalArgumentException("Unsupported locator type: " + key);
        };

    }

    private static void takeScreenShot(Scenario scenario){
        byte[] data = ((TakesScreenshot)Driver.getDriverInstance()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(data,"image/png",scenario.getName());
    }

    public static void setUpBeforeScenario(){
        ConsoleColors.printColorGreenMessage(":::STARTING AUTOMATION:::");
        Driver.getDriverInstance().manage().window().maximize();
        Driver.getDriverInstance().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Driver.getDriverInstance().manage().deleteAllCookies();
    }

    public static void tearDownAfterScenario(Scenario scenario) {
        if (scenario.isFailed()) {
            takeScreenShot(scenario);
            ConsoleColors.printColorRedMessage("::::::TEST FAILED::::::");
        }else {
            ConsoleColors.printColorGreenMessage("::::::TEST PASSED::::::");
        }
        ConsoleColors.printColorBlackMessage("::::::  END OF TEST EXECUTION! ::::::");
        Driver.closeDriverInstance();
    }

}
