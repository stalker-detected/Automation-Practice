package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class WebTablesPO extends BasePO {
    private static final String WEB_TABLES_URL = "/webtables";

    public WebTablesPO() {
        super(WEB_TABLES_URL);
    }

    private final SelenideElement addSel = $x("//button[@id=\"addNewRecordButton\"]");
    private final SelenideElement firstNameFormSel = $x("//input[@id=\"firstName\"]");
    private final SelenideElement lastNameFormSel = $x("//input[@id=\"lastName\"]");
    private final SelenideElement emailFormSel = $x("//input[@id=\"userEmail\"]");
    private final SelenideElement ageFormSel = $x("//input[@id=\"age\"]");
    private final SelenideElement salaryFormSel = $x("//input[@id=\"salary\"]");
    private final SelenideElement departmentFormSel = $x("//input[@id=\"department\"]");
    private final SelenideElement submitFormSel = $x("//button[@id=\"submit\"]");

    private SelenideElement firstNameTableSel(int rowNumber) {
        return $x("//div[@class=\"rt-tbody\"]/div[" + rowNumber +"]//div[@class=\"rt-td\"][1]");
    }

    private SelenideElement lastNameTableSel(int rowNumber) {
        return $x("//div[@class=\"rt-tbody\"]/div[" + rowNumber +"]//div[@class=\"rt-td\"][2]");
    }

    private SelenideElement ageTableSel(int rowNumber) {
        return $x("//div[@class=\"rt-tbody\"]/div[" + rowNumber +"]//div[@class=\"rt-td\"][3]");
    }

    private SelenideElement emailTableSel(int rowNumber) {
        return $x("//div[@class=\"rt-tbody\"]/div[" + rowNumber +"]//div[@class=\"rt-td\"][4]");
    }

    private SelenideElement salaryTableSel(int rowNumber) {
        return $x("//div[@class=\"rt-tbody\"]/div[" + rowNumber +"]//div[@class=\"rt-td\"][5]");
    }

    private SelenideElement departmentTableSel(int rowNumber) {
        return $x("//div[@class=\"rt-tbody\"]/div[" + rowNumber +"]//div[@class=\"rt-td\"][6]");
    }


    @Step("Click on button Add")
    public void clickOnAdd() {
        addSel.click();
    }

    @Step("Set first name: \"{firstname}\"")
    public void setFirstname(String firstname) {
        firstNameFormSel.setValue(firstname);
    }

    @Step("Set last name: \"{lastName}\"")
    public void setLastName(String lastName) {
        lastNameFormSel.setValue(lastName);
    }

    @Step("Set email: \"{email}\"")
    public void setEmail(String email) {
        emailFormSel.setValue(email);
    }

    @Step("Set age: \"{age}\"")
    public void setAge(String age) {
        ageFormSel.setValue(age);
    }

    @Step("Set salary: \"{salary}\"")
    public void setSalary(String salary) {
        salaryFormSel.setValue(salary);
    }

    @Step("Set department: \"{department}\"")
    public void setDepartment(String department) {
        departmentFormSel.setValue(department);
    }

    @Step("Click on Submit")
    public void clickOnSubmit() {
        submitFormSel.click();
    }

    @Step("Get first name from {rowNumber} row")
    public String getFirstNameInTable(int rowNumber) {
        return firstNameTableSel(rowNumber).getText();
    }

    @Step("Get last name from {rowNumber} row")
    public String getLastNameInTable(int rowNumber) {
        return lastNameTableSel(rowNumber).getText();
    }

    @Step("Get age from {rowNumber} row")
    public String getAgeInTable(int rowNumber) {
        return ageTableSel(rowNumber).getText();
    }

    @Step("Get email from {rowNumber} row")
    public String getEmailInTable(int rowNumber) {
        return emailTableSel(rowNumber).getText();
    }

    @Step("Get salary from {rowNumber} row")
    public String getSalaryInTable(int rowNumber) {
        return salaryTableSel(rowNumber).getText();
    }

    @Step("Get department from {rowNumber} row")
    public String getDepartmentInTable(int rowNumber) {
        return departmentTableSel(rowNumber).getText();
    }
}
