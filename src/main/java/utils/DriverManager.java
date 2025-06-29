package utils;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;



import java.util.HashMap;
import java.util.Map;

import static pages.BasePO.getBaseUrl;
import static utils.PropertiesLoader.getProp;

public class DriverManager {

    private static String browserName = System.getProperty("browser");

    public static void init() {
        if (browserName != null) {
            System.out.println("Current browser is: " +
                    browserName.toUpperCase());
        } else {
            browserName = getProp("browser");
            System.out.println("Browser is not stated. Default stated browser is: " + browserName.toUpperCase());
        }

        System.out.println("BaseUrl is: " + getBaseUrl());

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
    }

    private static void getFirefoxInstance()  {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--ignore-certificate-errors");
            Configuration.browserCapabilities = firefoxOptions;
    }
}
