package sampleTest;

import Utils.ExcelUtils;
import config.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.*;
import java.util.Map;

public class SampleTest {

    ExcelUtils excel = new ExcelUtils("sample.xlsx");

    WebDriver driver;

    @Test
    public void sampleTest() throws InterruptedException {

        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

        By by = By.xpath("//textarea[@aria-label=\"Search\"]");

        System.out.println("Jenkins");
        System.out.println("pogo");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));

        WebElement element = driver.findElement(by);

        element.sendKeys("Ajay" + Keys.ENTER);
        Thread.sleep(5000);


    }

    int dp[] = new int[100];
    public int fibonacci(int n){

        if(dp[n] != 0){
            return dp[n];
        }

        if(n == 0){
            dp[n] = 0;
            return 0;
        }
        if(n == 1){
            dp[n] = 1;
            return 1;
        }

        dp[n] = fibonacci(n-1) + fibonacci(n-2);
        return dp[n];


    }

    @AfterTest
    void clear(){
        driver.quit();
    }
}
