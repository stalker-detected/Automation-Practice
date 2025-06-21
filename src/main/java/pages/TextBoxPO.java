package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class TextBoxPO extends Base{
    private static final String TESTBOX_URL = "/text-box";
    public TextBoxPO() {
        super(TESTBOX_URL);
    }

    private final SelenideElement fullNameSel = $x("//input[@id=\"userName\"]");
    private final SelenideElement userEmailSel = $x("//input[@id=\"userEmail\"]");
    private final SelenideElement currentAddressSel = $x("//textarea[@id=\"currentAddress\"]");
    private final SelenideElement permanentAddressSel = $x("//textarea[@id=\"permanentAddress\"]");
    private final SelenideElement submitSel = $x("//*[@id=\"submit\"]");
    private final SelenideElement outputNameSel = $x("//p[@id=\"name\"]");
    private final SelenideElement outputEmailSel = $x("//p[@id=\"email\"]");
    private final SelenideElement outputCurrentAddressSel = $x("//p[@id=\"currentAddress\"]");
    private final SelenideElement outputPermanentAddressSel = $x("//p[@id=\"permanentAddress\"]");

    @Step("Enter full name: {fullName}")
    public TextBoxPO setFullName(String fullName) {
        fullNameSel.setValue(fullName);
        return this;
    }

    @Step("Enter email: {email}")
    public TextBoxPO setEmail(String email) {
        userEmailSel.setValue(email);
        return this;
    }

    @Step("Enter currentAddress: {currentAddress}")
    public TextBoxPO setCurrentAddress(String currentAddress) {
        currentAddressSel.setValue(currentAddress);
        return this;
    }

    @Step("Enter permanentAddress: {permanentAddress}")
    public TextBoxPO setPermanentAddress(String permanentAddress) {
        permanentAddressSel.setValue(permanentAddress);
        return this;
    }

    @Step("Click submit")
    public TextBoxPO clickSubmit() {
        submitSel.click();
        return this;
    }

    @Step("Get output name")
    public String getOutputName() {
        return outputNameSel.getText();
    }

    @Step("Get output email")
    public String getOutputEmail() {
        return outputEmailSel.getText();
    }

    @Step("Get output current address")
    public String getOutputCurrentAddress() {
        return outputCurrentAddressSel.getText();
    }

    @Step("Get output permanent  address")
    public String getOutputPermanentAddress() {
        return outputPermanentAddressSel.getText();
    }
}
