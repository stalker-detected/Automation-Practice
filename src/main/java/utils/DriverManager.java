package utils;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.util.HashMap;
import java.util.Map;

import static pages.BasePO.getBaseUrl;
import static utils.PropertiesLoader.getProp;

public class DriverManager {

    private static String browserName = System.getProperty("browser");
    private static String browserType = System.getProperty("driverType");

    private static final String REMOTE_URL = getProp("remoteServerURL") + ":4444/wd/hub";

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
        Configuration.proxyEnabled = true;

        if (browserName.equalsIgnoreCase("chrome"))
            getChromeInstance();
        else if (browserName.equalsIgnoreCase("firefox"))
            getFirefoxInstance();
        else if (browserName.equalsIgnoreCase("edge"))
            getEdgeInstance();
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
        }
    }

    private static void getFirefoxInstance()  {
        if (!browserType.equalsIgnoreCase("remote")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--ignore-certificate-errors");

            Configuration.browserCapabilities = firefoxOptions;
        } else {
            DesiredCapabilities capabilities2 = new DesiredCapabilities();

            capabilities2.setBrowserName("firefox");
            capabilities2.setVersion("125.0");
            capabilities2.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", false,
                    "sessionTimeout", "10m"
            ));

            Configuration.browserSize = "1920x1080";
            Configuration.browserCapabilities = capabilities2;
            Configuration.remote = REMOTE_URL;
        }

    }

    private static void getEdgeInstance() {
        if (!browserType.equalsIgnoreCase("remote")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--ignore-certificate-errors");
            edgeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            edgeOptions.setExperimentalOption("prefs", prefs);
            Configuration.browserCapabilities = edgeOptions;
        } else {
            DesiredCapabilities capabilities3 = new DesiredCapabilities();

            capabilities3.setBrowserName("MicrosoftEdge");
            capabilities3.setVersion("124.0");
            capabilities3.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", false,
                    "sessionTimeout", "10m"
            ));

            Configuration.browserSize = "1920x1080";
            Configuration.browserCapabilities = capabilities3;
            Configuration.remote = REMOTE_URL;
        }
    }
}
