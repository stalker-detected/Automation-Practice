package pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.testng.Reporter;

import static utils.PropertiesLoader.getProp;

public abstract class Base {
    private final String REL_URL;
    private static String BASE_URL;

    public Base(String url) {
        REL_URL = url;

        if (BASE_URL == null) {
            BASE_URL = System.getProperty("baseUrl");
            if (BASE_URL == null) {
                BASE_URL = getProp("baseUrl");
            }
        }

        switch (BASE_URL) {
            case "petStore":
                BASE_URL = getProp("petStore");
                break;
            case "demoQa":
                BASE_URL = getProp("demoQa");
                break;
        }

    }

    public static String getBaseUrl() {
        return BASE_URL;
    }

    @Step("Go to: {url}")
    public static void openUrl(String url) {
        Reporter.log("Go to : " + url, true);
        Selenide.open(url);
    }

    public Base open() {
        String s = BASE_URL + REL_URL;
        openUrl(s);
        return this;
    }
}
