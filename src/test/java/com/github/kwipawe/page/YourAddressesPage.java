package com.github.kwipawe.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class YourAddressesPage {

    private WebDriver driver;
    private static String newestAddressId;

    private List<WebElement> addressBox;
    private WebElement alias;

    @FindBy(css = "article.address")
    private List<WebElement> addressWidget;

    public YourAddressesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String getFirstAddressAsText() {
        addressBox = driver.findElements(By.xpath("//*[@id='address-" + newestAddressId + "']/div[1]/address"));
        WebElement address = addressBox.get(0);
        String[] lines = address.getText().split("\n");
        alias = driver.findElement(By.cssSelector("article[id='address-" + newestAddressId + "'] h4"));
        String aliasText = alias.getText();
        lines = addElementToArray(lines, aliasText);

        lines = Arrays.copyOf(lines, lines.length);
        return String.join("\n", lines);
    }

    private String[] addElementToArray(String[] arr, String element) {
        List<String> list = new ArrayList<>(Arrays.asList(arr));
        list.add(0, element);
        arr = list.toArray(arr);
        return arr;
    }

    public void getNewestAddress() {
        List<String> idList = new ArrayList<>();
        for (WebElement e : addressWidget) {
            String x = e.getAttribute("data-id-address");
            idList.add(x);
        }
        Collections.sort(idList, Collections.reverseOrder());
        newestAddressId = idList.get(0);
    }

    public void deleteNewestAddress() {
        driver.findElement(By.xpath("//*[@id='address-" + newestAddressId + "']/div[2]/a[2]")).click();
    }

    public boolean checkIfAddressIsDeleted() {
        try {
            driver.findElement(By.xpath("//*[@id='address-" + newestAddressId + "']"));
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
