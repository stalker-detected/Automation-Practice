package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LinksPO extends BasePO{
    private static final String LINKS_URL = "/links";
    public LinksPO() {
        super(LINKS_URL);
    }

    private final SelenideElement createdSel = $x("//a[@id=\"created\"]");
    private final SelenideElement noContentSel = $x("//a[@id=\"no-content\"]");
    private final SelenideElement movedSel = $x("//a[@id=\"moved\"]");
    private final SelenideElement badRequestSel = $x("//a[@id=\"bad-request\"]");
    private final SelenideElement unauthorizedSel = $x("//a[@id=\"unauthorized\"]");
    private final SelenideElement forbiddenSel = $x("//a[@id=\"forbidden\"]");
    private final SelenideElement notFoundSel = $x("//a[@id=\"invalid-url\"]");
    private final SelenideElement responseTextSel = $x("//p[@id=\"linkResponse\"]");

    @Step("Click on link Created")
    public void clickOnCreated() {
        createdSel.click();
    }

    @Step("Click on link No content")
    public void clickOnNoContent() {
        noContentSel.click();
    }

    @Step("Click on link Moved")
    public void clickOnMoved() {
        movedSel.click();
    }

    @Step("Click on link Bad Request")
    public void clickOnBadRequest() {
        badRequestSel.click();
    }

    @Step("Click on link Unauthorized")
    public void clickOnUnauthorized() {
        unauthorizedSel.click();
    }

    @Step("Click on link Forbidden")
    public void clickOnForbidden() {
        forbiddenSel.click();
    }

    @Step("Click on link Not Found")
    public void clickOnNotFound() {
        notFoundSel.click();
    }

    @Step("Get response text")
    public String getResponseText() {
        return responseTextSel.getText();
    }
}
