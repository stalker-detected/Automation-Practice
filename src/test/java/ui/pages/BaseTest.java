package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.openqa.selenium.remote.SessionId;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import pages.CheckBoxPO;
import pages.RadioButtonPO;
import pages.TextBoxPO;
import utils.DriverManager;


import java.util.List;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public abstract class BaseTest {
    public Faker faker = new Faker();
    protected TextBoxPO textBox = new TextBoxPO();
    protected CheckBoxPO checkBox = new CheckBoxPO();
    protected RadioButtonPO radioButton = new RadioButtonPO();


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

    public SessionId session;


    @BeforeTest
    public void beforeTest() {
        DriverManager.init();

        Selenide.open();
        WebDriverRunner.getWebDriver().manage().window().maximize();

        session = Selenide.sessionId();
        System.out.println("ID this session = " + session);
        Allure.addAttachment("ID this session ", session.toString());
    }


    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext iTestContext) {
        closeWebDriver();
    }
}

