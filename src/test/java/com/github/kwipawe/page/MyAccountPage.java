package com.github.kwipawe.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private WebDriver driver;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToAddressPage() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=addresses");
        driver.findElement(By.cssSelector("a[data-link-action='add-address']")).click();
    }

    public CreateNewAddressPage goToAddressPageWithObject() {
        goToAddressPage();
        return new CreateNewAddressPage(driver);
    }
}