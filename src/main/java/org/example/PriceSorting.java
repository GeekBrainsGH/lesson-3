package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PriceSorting {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();

        webDriver.get("https://doctorhead.ru/");

        webDriver.manage().window().setSize(new Dimension(1500, 1000));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


//          Для дальнейшей корректной работы необходимо подтвердить город:
        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

        new Actions(webDriver)
                .moveToElement(webDriver.findElement(By.xpath("//div[contains(@class, 'header-nav')]//a[@href='/catalog/personal-audio/']")))
                .build()
                .perform();

        new WebDriverWait(webDriver,15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-catalog-section')]//div[text()='Полноразмерные наушники']")));

        webDriver.findElement(By.xpath("//div[contains(@class, 'header-catalog-section')]//div[text()='Полноразмерные наушники']")).click();
        webDriver.findElement(By.xpath("//div[@class='drop-down-headline']")).click();
        webDriver.findElement(By.xpath("//span[contains(@onclick, 'price&order=desc')]")).click();

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@id='catalog-list']"));

        System.out.println("Товар с наивысшей стоимостью: " + products.get(0).findElement(By.xpath("//a[contains(@class, 'product-title')]")).getText());
        System.out.println("Ожидаемый товар: Sennheiser Orpheus HE-1");

    }
}
