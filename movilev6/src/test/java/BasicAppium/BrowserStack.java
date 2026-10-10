package BasicConfig;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserStack {

    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:deviceName", "Medium Phone API 37.0");
        capabilities.setCapability("appium:udid", "emulator-5554");
        capabilities.setCapability("appium:platformVersion", "17.0");
        capabilities.setCapability("appium:appPackage", "com.browserstack.demo.app");
        capabilities.setCapability("appium:appActivity", "host.exp.exponent.MainActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),capabilities);
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
    }

    @Test
    public void verifyLoginInDemoApp() throws InterruptedException {

        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu\"]")).click();
        driver.findElement(AppiumBy.accessibilityId("nav-signin")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Accepted usernames are\"]")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text=\"demouser\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/text1\" and @text=\"Password for all users\"]")).click();
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text=\"testingisfun99\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sign in\"]")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text=\"Add to cart\"]\n")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu\"]")).click();
        Assertions.assertTrue(driver.findElement(AppiumBy.accessibilityId("nav-logout")).isDisplayed());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();

    }
}
