package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.*;
import java.util.*;

public class CalculatorStepDefinitions extends TestState {

    WebDriver driver = Driver.getDriverInstance();
    CommonFunctions common = new CommonFunctions();



    @Given("^(?:|we |I )navigate to \"([^\"]*)\"$")
    public void navigateToUrl(String envUrl) {
        String url;
        if (envUrl.contains("prod")){
            url = ConfigurationManager.getProperty("prodEnvUrl");
        }else if (envUrl.contains("stage")){
            url = ConfigurationManager.getProperty("stageEnvUrl");
        }else {
            throw new RuntimeException("PROVIDE ENVIRONMENT URL! " + envUrl);
        }
        driver.get(url);
    }

    @When("^(?:|we are|I am) on (.*)$")
    public void onPage(String name) throws RuntimeException {
        YamlLocatorReader reader = new YamlLocatorReader();
        setPage(new HashMap<>(reader.read(name)));
    }

    @Then("^the (.*) should be (visible|displayed|invisible|hidden)$")
    public void validateVisibilityOfElement(String locator, String condition) {
        By by = get(locator);
        List<WebElement> elementList = driver.findElements(by);
        if (condition.equals("visible") || condition.equals("displayed")) {
            Assert.assertFalse(elementList.isEmpty());
        } else if (condition.equals("invisible") || condition.equals("hidden")) {
            Assert.assertTrue(elementList.isEmpty());
        }
    }

    @Then("^the following elements should be (displayed|visible|present|hidden|invisible):$")
    public void visibilityOfElements(String condition, List<String> elements) {
        if (!elements.isEmpty()) {
            List<String> elementList = new ArrayList<>(elements);
            CommonFunctions.waitFor(500L);
            for (String locator : elementList) {
                if (condition.equals("displayed") || condition.equals("visible") || condition.equals("present")) {
                    By by = get(locator);
                    WebElement element = driver.findElement(by);
                    Assert.assertTrue(element.isDisplayed());
                } else if (condition.equals("invisible") || condition.equals("hidden")) {
                    By by = get(locator);
                    WebElement element = driver.findElement(by);
                    Assert.assertFalse(element.isDisplayed());
                }
            }
        } else {
            throw new NullPointerException("***************LIST IS EMPTY!");
        }
    }

    @When("^(?:|we |I )click on the (.*)$")
    public void clickOn(String locator) {
        By by = get(locator);
        common.clickWhenVisible(by);
    }

    @And("^(?:|we |I )wait( for 1 second| for \\d+ seconds| for \\d+ minutes|)$")
    public void weWait(String pause) {
        if (pause.isEmpty()) {
            CommonFunctions.waitFor(5);
        } else if (pause.contains("minutes")) {
            int min = Integer.parseInt(pause.replaceAll("\\D", ""));
            CommonFunctions.waitFor(min * 60);
        } else {
            int sec = Integer.parseInt(pause.replaceAll("\\D", ""));
            CommonFunctions.waitFor(sec);
        }
    }

    @Then("^the (.*) field content should (contain the|be equal to the|NOT contain the|NOT be equal to) following \"([^\"]*)\"$")
    public void verifyText(String locator, String condition, String expectedText) {
        By by = get(locator);
        String actualText = common.getTextWhenVisible(by);
        if (!condition.startsWith("NOT")) {
            if (condition.startsWith("be")) {
                Assert.assertEquals("Expected text does NOT equal to actual ",expectedText, actualText);
            } else if (condition.startsWith("contain")) {
                Assert.assertTrue("Expected text does NOT contain actual ",actualText.contains(expectedText));
            }
        } else {
            if (condition.contains("NOT be")) {
                Assert.assertNotEquals("Expected text does EQUAL to actual ",expectedText, actualText);
            } else if (condition.contains("NOT contain")) {
                Assert.assertFalse("Expected text does CONTAIN actual ",actualText.contains(expectedText));
            }
        }
    }
}
