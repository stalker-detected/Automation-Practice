package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class MainPagePO extends BasePO {
    private static final String MAIN_URL = "/";

    public MainPagePO() {
        super(MAIN_URL);
    }

    private SelenideElement elementSel(int index) {
        return $x("//div[@class=\"category-cards\"]/div[" + index + "]//div[@class=\"card-body\"]");
    }

    @Step("Click on element with index: {index}")
    public MainPagePO clickOnElementByIndex(int index) {
        elementSel(index).scrollTo().click();
        return this;
    }

    @Step("Get text element with index: {index}")
    public String getElementName(int index) {
        return elementSel( index).getText();
    }
}
