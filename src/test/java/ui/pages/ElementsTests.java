package ui.pages;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ElementsTests extends BaseTest{
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
}
