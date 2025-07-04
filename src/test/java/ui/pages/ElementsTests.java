package ui.pages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ElementsTests extends BaseTest {
    @Epic(value = "UI")
    @Feature(value = "Elements")
    @Test
    public void checkTextBox() {
        textBox.open();
        textBox.setFullName("Yuriy Muzhailo");
        textBox.setEmail("muzhailo@gmail.com");
        textBox.setCurrentAddress("city Kyiv");
        textBox.setPermanentAddress("city Lviv");
        textBox.clickSubmit();
        assertEquals(textBox.getOutputName(), "Name:Yuriy Muzhailo");
        assertEquals(textBox.getOutputEmail(), "Email:muzhailo@gmail.com");
        assertEquals(textBox.getOutputCurrentAddress(), "Current Address :city Kyiv");
        assertEquals(textBox.getOutputPermanentAddress(), "Permananet Address :city Lviv");
    }

    @Epic(value = "UI")
    @Feature(value = "Elements")
    @Test
    public void checkCheckBox() {
        checkBox.open();
        checkBox.clickExpandAll();
        checkBox.clickOnElement("Public");
        assertTrue(checkBox.elementIsSelected("Public"));
        assertEquals(checkBox.getResultTextByIndex("1"), "public");
    }

    @Epic(value = "UI")
    @Feature(value = "Elements")
    @Test
    public void checkRadioButton() {
        radioButton.open();
        radioButton.clickOnElement("Yes");
        assertEquals(radioButton.getResultText(), "Yes");
        radioButton.clickOnElement("Impressive");
        assertEquals(radioButton.getResultText(), "Impressive");
        assertFalse(radioButton.elementIsSelected("No"));
    }

    @Epic(value = "UI")
    @Feature(value = "Elements")
    @Test
    public void checkWebTables() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String age = String.valueOf(faker.number().numberBetween(18, 100));
        String salary = String.valueOf(faker.number().numberBetween(1000, 10000));
        String department = faker.commerce().department();

        webTables.open();
        webTables.clickOnAdd();
        webTables.setFirstname(firstName);
        webTables.setLastName(lastName);
        webTables.setEmail(email);
        webTables.setAge(age);
        webTables.setSalary(salary);
        webTables.setDepartment(department);
        webTables.clickOnSubmit();
        assertEquals(webTables.getFirstNameInTable(4), firstName);
        assertEquals(webTables.getLastNameInTable(4), lastName);
        assertEquals(webTables.getAgeInTable(4), age);
        assertEquals(webTables.getEmailInTable(4), email);
        assertEquals(webTables.getSalaryInTable(4), salary);
        assertEquals(webTables.getDepartmentInTable(4), department);
    }

    @Epic(value = "UI")
    @Feature(value = "Elements")
    @Test
    public void checkLinks() {
        links.open();
        startTrafficCapture();
        links.clickOnCreated();
        assertEquals(interceptRequest("/created").getResponse().getStatus(), 201);
        assertEquals(links.getResponseText(), "Link has responded with staus 201 and status text Created");

        startTrafficCapture();
        links.clickOnNoContent();
        assertEquals(interceptRequest("/no-content").getResponse().getStatus(), 204);
        assertEquals(links.getResponseText(), "Link has responded with staus 204 and status text No Content");

        startTrafficCapture();
        links.clickOnMoved();
        assertEquals(interceptRequest("/moved").getResponse().getStatus(), 301);
        assertEquals(links.getResponseText(), "Link has responded with staus 301 and status text Moved Permanently");

        startTrafficCapture();
        links.clickOnBadRequest();
        assertEquals(interceptRequest("/bad-request").getResponse().getStatus(), 400);
        assertEquals(links.getResponseText(), "Link has responded with staus 400 and status text Bad Request");

        startTrafficCapture();
        links.clickOnUnauthorized();
        assertEquals(interceptRequest("/unauthorized").getResponse().getStatus(), 401);
        assertEquals(links.getResponseText(), "Link has responded with staus 401 and status text Unauthorized");

        startTrafficCapture();
        links.clickOnForbidden();
        assertEquals(interceptRequest("/forbidden").getResponse().getStatus(), 403);
        assertEquals(links.getResponseText(), "Link has responded with staus 403 and status text Forbidden");

        startTrafficCapture();
        links.clickOnNotFound();
        assertEquals(interceptRequest("/invalid-url").getResponse().getStatus(), 404);
        assertEquals(links.getResponseText(), "Link has responded with staus 404 and status text Not Found");
    }
}
