package utils;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.net.NetworkUtils;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.HashMap;
import java.util.Map;

import static pages.BasePO.getBaseUrl;
import static utils.PropertiesLoader.getProp;

public class DriverManager {

    private static String browserName = System.getProperty("browser");
    private static String browserType = System.getProperty("driverType");

    private static final String REMOTE_URL = getProp("remoteServerURL");

    public static void init() {
        if (browserName != null) {
            System.out.println("Current browser is: " +
                    browserName.toUpperCase());
        } else {
            browserName = getProp("browser");
            System.out.println("Browser is not stated. Default stated browser is: " + browserName.toUpperCase());
        }

        System.out.println("BaseUrl is: " + getBaseUrl());

        if (browserType == null) {
            browserType = getProp("driverType");
        }

        Configuration.browser = browserName;
        Configuration.pageLoadTimeout = Long.parseLong(PropertiesLoader.getProp("pageLoadTimeout"));
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.timeout = Long.parseLong(PropertiesLoader.getProp("timeout"));

        if (browserName.equalsIgnoreCase("chrome"))
            getChromeInstance();
        else if (browserName.equalsIgnoreCase("firefox"))
            getFirefoxInstance();
        else
            getChromeInstance();
    }

    private static void getChromeInstance() {
        if (!browserType.equalsIgnoreCase("remote")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--remote-allow-origins=*");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            chromeOptions.setExperimentalOption("prefs", prefs);
            Configuration.browserCapabilities = chromeOptions;
            Configuration.browserSize = null;
            Configuration.proxyEnabled = true;
        } else {
            DesiredCapabilities capabilities1 = new DesiredCapabilities();

            ChromeOptions options = new ChromeOptions();
            capabilities1.setBrowserName("chrome");
            capabilities1.setVersion("128.0");
            capabilities1.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", false,
                    "sessionTimeout", "10m"
            ));
            options.addArguments("--disable-site-isolation-trials");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("disable-features=NetworkService");
            options.addArguments("--no-sandbox");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("--disable-dev-shm-usage");
            ChromeOptions merge = options.merge(capabilities1);

            Configuration.browserSize = "1920x1080";
            Configuration.browserCapabilities = merge;
            Configuration.remote = REMOTE_URL;
            Configuration.proxyEnabled = true;
            Configuration.proxyHost = new NetworkUtils().getNonLoopbackAddressOfThisMachine();
            System.out.println(new NetworkUtils().getNonLoopbackAddressOfThisMachine());
        }
    }

    private static void getFirefoxInstance()  {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--ignore-certificate-errors");
            Configuration.browserCapabilities = firefoxOptions;
    }
}
