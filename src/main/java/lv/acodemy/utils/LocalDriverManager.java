package lv.acodemy.utils;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.rmi.Remote;
import java.util.HashMap;
import java.util.Map;

public class LocalDriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private LocalDriverManager() {
    }

    public static WebDriver getInstance() {
        if (driver.get() == null) {
            if (ConfigurationProperties.getConfiguration().getBoolean("run.locally")) {
                driver.set(new ChromeDriver());
            } else {
                driver.set(configureRemote());
            }
        } else {
            return driver.get();
        }
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }


    @SneakyThrows

    public static RemoteWebDriver configureRemote() {
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-artjomsbesedins-acd59");
        sauceOptions.put("accessKey", "99412c84-62aa-46a8-a70e-272de3f7ee90");
        sauceOptions.put("build", "selenium-build-6YXEP");
        sauceOptions.put("name", "<your test name>");
        browserOptions.setCapability("sauce:options", sauceOptions);

        URL url = new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub");
        return new  RemoteWebDriver(url, browserOptions);
    }
}