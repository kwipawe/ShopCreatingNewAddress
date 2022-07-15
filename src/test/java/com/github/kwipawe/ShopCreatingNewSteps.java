package com.github.kwipawe;

import com.github.kwipawe.page.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ShopCreatingNewSteps {

    private WebDriver driver;
    private String lastName;
    private String firstName;
    private String country;


    @Given("I am on shop main page")
    public void iAmOnShopMainPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @When("I go to sign in page")
    public void iGoToSignInPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.signIn();
    }

    @And("I log in as an user using {string} and {string}")
    public void iLogInAsAnUserUsingEmailAndPassword(String login, String password) {
        SignInPage signInPage = new SignInPage(driver);
        signInPage.loginAs(login, password);
    }

    @And("I go to Add First Address Page")
    public void iGoToAddFirstAddressPage() {
        MyAccountPage myAccountPage = new MyAccountPage(driver);
        myAccountPage.goToAddressPage();
    }

    @And("I put  {string}, {string}, {string}, {string}, {string} and save")
    public void iPutAndSave(String alias, String address, String city, String postalcode, String phone) {
        CreateNewAddressPage createNewAddressPage = new CreateNewAddressPage(driver);
        createNewAddressPage.enterNewAddress(alias, address, postalcode, city, phone);
        //write data to vars for test later
        firstName = createNewAddressPage.getFirstName();
        lastName = createNewAddressPage.getLastName();
        country = createNewAddressPage.getCountry();
        createNewAddressPage.saveAddress();
    }

    @Then("I see {string}, {string}, {string}, {string}, {string}")
    public void iSee(String alias, String address, String city, String postalcode, String phone) {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.getNewestAddressId();
        String actualAddress = yourAddressesPage.getFirstAddressAsText();
        String nameSurname = firstName + " " + lastName;
        String expectedAddress = String.join("\n", alias, nameSurname, address,
                postalcode, city, country, phone);
        Assert.assertEquals(expectedAddress, actualAddress);
    }

    @And("I delete created address")
    public void iDeleteCreatedAddress() {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        yourAddressesPage.deleteNewestAddress();
    }

    @And("I check if created address is deleted")
    public void iCheckIfCreatedAddressIsDeleted() {
        YourAddressesPage yourAddressesPage = new YourAddressesPage(driver);
        boolean check = yourAddressesPage.checkIfAddressIsDeleted();
        Assert.assertFalse(check);
    }
}