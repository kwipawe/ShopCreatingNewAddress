package com.github.kwipawe.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void signIn() {
        driver.findElement(By.cssSelector("a[title='Log in to your customer account']")).click();
    }
}