package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class RadioButtonPO extends Base {
    private static final String RADIO_BUTTON_URL = "/radio-button";

    public RadioButtonPO() {
        super(RADIO_BUTTON_URL);
    }

    private SelenideElement elementSel(String element) {
        return $x("//div[contains(@class,\"custom-control\")]/label[text()='" + element + "']");
    }

    private final SelenideElement resultTextSel = $x("//span[@class=\"text-success\"]");

    private SelenideElement inputSel(String element) {
        return $x("//div[contains(@class,\"custom-control\")]/label[text()='" + element + "']/../input");
    }

    @Step("Click on element\"{element}\"")
    public void clickOnElement(String element) {
        elementSel(element).click();
    }

    @Step("Get result test")
    public String getResultText() {
        return resultTextSel.getText();
    }

    @Step("Element \"{element}\" is selected")
    public boolean elementIsSelected(String element) {
        return inputSel(element).isSelected();
    }


}
