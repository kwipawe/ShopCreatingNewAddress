package com.github.kwipawe.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewAddressPage {
    private WebDriver driver;

    @FindBy(css = "input[name='alias']")
    private WebElement aliasTextField;

    @FindBy(css = "input[name='address1']")
    private WebElement addressTextField;

    @FindBy(css = "input[name='postcode']")
    private WebElement postCodeTextField;

    @FindBy(css = "input[name='city']")
    private WebElement cityTextField;

    @FindBy(css = "select[name='id_country']")
    private WebElement countryList;

    @FindBy(xpath = "//*[@id='content']/div/div/form/section/div[10]/div[1]/select/option[2]")
    private WebElement particularCountryItem;

    @FindBy(css = "input[name='phone']")
    private WebElement phoneTextField;

    @FindBy(css = "button[class='btn btn-primary float-xs-right']")
    private WebElement saveBtn;

    @FindBy(css = "input[name='firstname']")
    private WebElement firstNameTextField;

    @FindBy(css = "input[name='lastname']")
    private WebElement lastNameTextField;

    public CreateNewAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterNewAddress(String alias, String address, String city, String postalcode, String phone) {
        aliasTextField.clear();
        aliasTextField.sendKeys(alias);

        addressTextField.clear();
        addressTextField.sendKeys(address);

        cityTextField.clear();
        cityTextField.sendKeys(city);

        postCodeTextField.clear();
        postCodeTextField.sendKeys(postalcode);

        countryList.click();
        particularCountryItem.click();

        //to avoid stale DOM exception
        //https://stackoverflow.com/questions/18225997/stale-element-reference-element-is-not-attached-to-the-page-document
        try {
            phoneTextField.clear();
            phoneTextField.sendKeys(phone);
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            phoneTextField.clear();
            phoneTextField.sendKeys(phone);
        }

    }

    public String getFirstName() {
        try {
            return firstNameTextField.getAttribute("value");
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            return firstNameTextField.getAttribute("value");
        }
    }

    public String getLastName() {
        try {
            return lastNameTextField.getAttribute("value");
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            return lastNameTextField.getAttribute("value");
        }
    }

    public String getCountry() {
        Select select = new Select(countryList);
        return select.getFirstSelectedOption().getText();
    }

    public void saveAddress() {
        //to avoid stale DOM exception
        //https://stackoverflow.com/questions/18225997/stale-element-reference-element-is-not-attached-to-the-page-document
        try {
            saveBtn.click();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            saveBtn.click();
        }
    }
}
