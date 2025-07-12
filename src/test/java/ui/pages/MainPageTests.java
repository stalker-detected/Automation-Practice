package ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;
import pages.BasePO;

import static org.testng.Assert.assertEquals;

public class MainPageTests extends BaseTest {

    @Epic(value = "UI")
    @Feature(value = "Main page")
    @Test
    public void checkMainPage() {
        mainPagePO.open();
        assertEquals(mainPagePO.getElementName(1), "Elements");
        mainPagePO.clickOnElementByIndex(1);
        assertEquals(WebDriverRunner.url(), BasePO.getBaseUrl() + "/elements");

        Selenide.back();
        assertEquals(mainPagePO.getElementName(2), "Forms");
        mainPagePO.clickOnElementByIndex(2);
        assertEquals(WebDriverRunner.url(), BasePO.getBaseUrl() + "/forms");

        Selenide.back();
        assertEquals(mainPagePO.getElementName(3), "Alerts, Frame & Windows");
        mainPagePO.clickOnElementByIndex(3);
        assertEquals(WebDriverRunner.url(), BasePO.getBaseUrl() + "/alertsWindows");

        Selenide.back();
        assertEquals(mainPagePO.getElementName(4), "Widgets");
        mainPagePO.clickOnElementByIndex(4);
        assertEquals(WebDriverRunner.url(), BasePO.getBaseUrl() + "/widgets");

        Selenide.back();
        assertEquals(mainPagePO.getElementName(5), "Interactions");
        mainPagePO.clickOnElementByIndex(5);
        assertEquals(WebDriverRunner.url(), BasePO.getBaseUrl() + "/interaction");

        Selenide.back();
        assertEquals(mainPagePO.getElementName(6), "Book Store Application");
        mainPagePO.clickOnElementByIndex(6);
        assertEquals(WebDriverRunner.url(), BasePO.getBaseUrl() + "/books");
    }
}
