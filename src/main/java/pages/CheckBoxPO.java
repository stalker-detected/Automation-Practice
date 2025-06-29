package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CheckBoxPO extends BasePO {
    private static final String CHECKBOX_URL = "/checkbox";

    public CheckBoxPO() {
        super(CHECKBOX_URL);
    }

    private SelenideElement elementSel(String element) {
        return $x("//span[text()='" + element + "']/../span[@class=\"rct-checkbox\"]");
    }

    private SelenideElement checkBoxSel(String element) {
        return $x("//span[text()='" + element + "']/../input[@type=\"checkbox\"]");
    }

    private final SelenideElement expandAllSel = $x("//button[@aria-label=\"Expand all\"]");

    private SelenideElement resultTextByIndex(String index) {
        return $x("//span[@class=\"text-success\"][" + index + "]");
    }

    @Step("Click \"Expand all\"")
    public void clickExpandAll() {
        expandAllSel.click();
    }

    @Step("Click on \"{element}\"")
    public void clickOnElement(String element) {
        elementSel(element).click();
    }

    @Step("Element \"{element}\" is selected")
    public boolean elementIsSelected(String element) {
        return checkBoxSel(element).isSelected();
    }

    @Step("Get result text by index {index}")
    public String getResultTextByIndex(String index) {
        return resultTextByIndex(index).getText();
    }
}
