package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {

    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

    private Driver(){}

    public static WebDriver getDriverInstance(){
        if (driverPool.get() == null){
            synchronized (Driver.class){
                String browser = ConfigurationManager.getProperty("browser");
                switch (browser.toLowerCase()){
                    case "chrome":
                        driverPool.set(new ChromeDriver());
                        break;
                    case "edge":
                        driverPool.set(new EdgeDriver());
                        break;
                    case "firefox":
                        driverPool.set(new FirefoxDriver());
                        break;
                    case "safari":
                        driverPool.set(new SafariDriver());
                        break;
                    default:
                        throw new RuntimeException("Failed to initialize the browser type");
                }
            }
        }
        return driverPool.get();
    }

    public static void closeDriverInstance(){
        if (driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }


}
