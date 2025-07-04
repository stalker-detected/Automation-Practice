package ui.pages;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import de.sstoehr.harreader.model.HarEntry;
import io.qameta.allure.Allure;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.*;
import utils.DriverManager;


import java.util.List;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;


public abstract class BaseTest {
    public SessionId session;
    BrowserUpProxy bmp;
    public Faker faker = new Faker();
    protected TextBoxPO textBox = new TextBoxPO();
    protected CheckBoxPO checkBox = new CheckBoxPO();
    protected RadioButtonPO radioButton = new RadioButtonPO();
    protected WebTablesPO webTables = new WebTablesPO();
    protected LinksPO links = new LinksPO();


    @AfterMethod
    public void afterMethod(ITestResult result) {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

        StringBuilder toString = new StringBuilder();

        List<String> consoleOutput = Reporter.getOutput(result);
        for (String list : consoleOutput) {
            list = list + " \n";
            toString.append(list);
        }

        Allure.addAttachment("Output in console", toString.toString());

    }


    @BeforeTest
    public void beforeTest() {
        DriverManager.init();

        Selenide.open();
        bmp = getSelenideProxy().getProxy();
        bmp.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        bmp.blocklistRequests("https?://.*\\.googlesyndication\\.com/.*", 403); //ads breaks tests

        WebDriverRunner.getWebDriver().manage().window().maximize();

        session = Selenide.sessionId();
        System.out.println("ID this session = " + session);
        Allure.addAttachment("ID this session ", session.toString());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext iTestContext) {
        closeWebDriver();
    }

    public void startTrafficCapture() {
        bmp.newHar("har");
    }

    public HarEntry interceptRequest(String url) {
        try {
            Selenide.sleep(200);
            List<HarEntry> entries = bmp.getHar().getLog().getEntries();
            for (HarEntry entry : entries) {
                if (entry.getRequest().getUrl().endsWith(url)) {
                    int time = 5000; //wait response
                    for (int i = 0; i < time; i += 200) {
                        if (entry.getResponse().getStatus() > 0) {
                            break;
                        }
                        Selenide.sleep(200);
                    }
                    return entry;
                }
            }
            return null;
        } finally {
            bmp.endHar();
        }
    }
}

