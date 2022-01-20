package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogInLogOut
{
    public static void main( String[] args ) throws InterruptedException {

        // Примечание про If-Else в коде: Иногда тест падает при входе на сайт, так как окно "Подтвердите город"
        // появляется не на переднем плане посередине, а в другом месте в левом верхнем углу.
        // Может отобразиться два разных элемента.
        // В первом варианте я написала для случая, когда переднее окно подтверждения отображается верно и часто.
        // Во втором варинате - когда это окно не всплывает и город подтверждать не нужно.
        // Что забавно, ошибка с отображениям окна редкая, и после написания условий она больше не выходит.
        // Поэтому правильность работы условий я проверить не могу :D
        // В других скриптах это условие прописывать пока не стала. Так как если так делать, то лучше прописывать метод для части скрипта.

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
       WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();

       webDriver.get("https://doctorhead.ru/");

       webDriver.manage().window().setSize(new Dimension(1500, 1000));
       webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


       //Thread.sleep(500000);

        if (webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).isDisplayed()) {
            //          Для дальнейшей корректной работы необходимо подтвердить город:
            webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();

            webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();


////          Для дальнейшей корректной работы необходимо подтвердить город:
//       webDriver.findElement(By.xpath("//div[contains(@class, 'modal-location-question')]//button[contains(@class, 'location-question-confirm-btn')]")).click();
//
//       webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        By authLocator = By.xpath("//div[contains(@class, 'modal-wr is-open')]");
        new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(authLocator));
        WebElement authForm = webDriver.findElement(authLocator);

        authForm.findElement(By.name("USER_LOGIN")).sendKeys("test_202334@mail.ru");
        authForm.findElement(By.name("USER_PASSWORD")).sendKeys("123456789n");
        authForm.findElement(By.name("Login")).click();

       //new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")));
       new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='header-menu__item_login-img lozad-loaded']")));

        webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

        //  Решила использвать нахождение по ссылке, так как текст может измениться (маловероятно, но все же).
        //  А на практике если стоит выбор между ссылкой и текстом, какое из зол практичней выбирать?

        webDriver.findElement(By.xpath("//div[contains(@class, 'lk-nav')]//a[@href='/personal/?logout=yes']")).click();

        new WebDriverWait(webDriver,15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")));

//Thread.sleep(10000);
        webDriver.quit(); }

        else {

            By authLocator = By.xpath("//div[contains(@class, 'modal-wr is-open')]");
            new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(authLocator));
            WebElement authForm = webDriver.findElement(authLocator);

            authForm.findElement(By.name("USER_LOGIN")).sendKeys("test_202334@mail.ru");
            authForm.findElement(By.name("USER_PASSWORD")).sendKeys("123456789n");
            authForm.findElement(By.name("Login")).click();

            //new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")));
            new WebDriverWait(webDriver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@class='header-menu__item_login-img lozad-loaded']")));

            webDriver.findElement(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")).click();

            //  Решила использвать нахождение по ссылке, так как текст может измениться (маловероятно, но все же).
            //  А на практике если стоит выбор между ссылкой и текстом, какое из зол практичней выбирать?

            webDriver.findElement(By.xpath("//div[contains(@class, 'lk-nav')]//a[@href='/personal/?logout=yes']")).click();

            new WebDriverWait(webDriver,15).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-menu')]//a[contains(@class, 'header-menu__item_login')]")));

//Thread.sleep(10000);
            webDriver.quit();
        }


//        Это запасной вариант.
//        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-wr is-open')]//input[@name='USER_LOGIN']")).sendKeys("test_202334@mail.ru");
//        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-wr is-open')]//input[@name='USER_PASSWORD']")).sendKeys("123456789n");
//        webDriver.findElement(By.xpath("//div[contains(@class, 'modal-wr is-open')]//button[@name='Login']")).click();
    }
}
