package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddToCart {

    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.get("https://doctorhead.ru/");
        webDriver.manage().window().setSize(new Dimension(1500, 1000));
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //Предусловие. Авторизация


//          Для дальнейшей корректной работы необходимо подтвердить город:
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")));
        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();
        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        By authLocator = By.xpath("//div[contains(@class, 'modal-wr is-open')]");
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(authLocator));
        WebElement authForm = webDriver.findElement(authLocator);

        authForm.findElement(By.name("USER_LOGIN")).sendKeys("test_202334@mail.ru");
        authForm.findElement(By.name("USER_PASSWORD")).sendKeys("123456789n");
        authForm.findElement(By.name("Login")).click();
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='header-menu__item_login-img lozad-loaded']")));


        // Основной скрипт

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='top-search-line']")));
        webDriver.findElement(By.xpath("//input[@id='top-search-line']")).sendKeys("Sony WH-1000XM4 Black");

        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='search-block-product-list']"));
        products.get(0).findElement(By.xpath("//a[contains(@class, 'search-product__title')]")).click();
        webDriver.findElement(By.xpath("//button[@id='add-to-cart']")).click();

        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='go_to_cart']")));
        webDriver.findElement(By.xpath("//div[@id='go_to_cart']//button[contains(@class, 'button button_primary')]")).click();
        webDriver.findElement(By.xpath("//tr[contains(@class, 'js-basket-elem')]//button[contains(@class, 'button-remove')]")).click();


        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();
        webDriver.findElement(By.xpath("//div[contains(@class, 'lk-nav')]//a[@href='/personal/?logout=yes']")).click();

        new WebDriverWait(webDriver,15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")));

        webDriver.quit();




    }
}
