package com.selenium;

import java.io.File;
import java.time.Duration;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Opencart_project2 {

    public static void main(String[] args) {
        // Initialize WebDriver
        WebDriver driver = null;

        try {
            // Set up ChromeDriver
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            // Navigate to the website
            driver.get("https://awesomeqa.com/ui/");

            // Search for "iPhone"
            WebElement searchBox = driver.findElement(By.xpath("//input[@name='search']"));
            searchBox.sendKeys("iphone");

            WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-default btn-lg']"));
            searchButton.click();

            // Sort results by "Price (Low > High)" and add to cart
            WebElement sortDropdown = driver.findElement(By.id("input-sort"));
            Select sortOptions = new Select(sortDropdown);
            sortOptions.selectByVisibleText("Price (Low > High)");

            WebElement addToCartButton = driver.findElement(By.xpath("//button[@onclick=\"cart.add('40', '1');\"]"));
            addToCartButton.click();

            // Navigate to "Cameras" and sort by "Model (Z - A)"
            WebElement camerasLink = driver.findElement(By.xpath("//a[text()='Cameras']"));
            camerasLink.click();

            WebElement cameraSortDropdown = driver.findElement(By.id("input-sort"));
            Select cameraSortOptions = new Select(cameraSortDropdown);
            cameraSortOptions.selectByVisibleText("Model (Z - A)");

            WebElement cameraAddToCartButton = driver.findElement(By.xpath("//button[@onclick=\"cart.add('31', '1');\"]"));
            cameraAddToCartButton.click();

            // Hover over "Desktops" and click "Show All Desktops"
            Actions actions = new Actions(driver);
            WebElement desktopsMenu = driver.findElement(By.xpath("//a[text()='Desktops']"));
            actions.moveToElement(desktopsMenu).perform();

            WebElement showAllDesktopsLink = driver.findElement(By.xpath("//a[text()='Show All Desktops']"));
            showAllDesktopsLink.click();

            // Handle multiple windows
            String parentWindowHandle = driver.getWindowHandle();
            System.out.println("Parent Window ID: " + parentWindowHandle);

//            // Wait until a second window opens
//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//
//            // Switch to the child window
//            Set<String> windowHandles = driver.getWindowHandles();
//            for (String windowHandle : windowHandles) {
//                if (!windowHandle.equals(parentWindowHandle)) {
//                    driver.switchTo().window(windowHandle);
//                    System.out.println("Switched to child window: " + windowHandle);
//                    break;
//                }
//            }

            // Check out cart
            WebElement cartButton = driver.findElement(By.xpath("//button[@data-loading-text=\"Loading...\"]"));
            cartButton.click();
            
//            checkout 
            WebElement checkout = driver.findElement(By.xpath("//a[@href=\"https://awesomeqa.com/ui/index.php?route=checkout/checkout\"]"));
            checkout.click();
            
//            Screen shot
   		 Screenshot take = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
   		 ImageIO.write(take.getImage(),"PNG",new File(System.getProperty("user.dir") + "\\Screenshots\\Opencart.png"));

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace(); // Print exception details for debugging
        } finally {
            // Ensure driver cleanup
            if (driver != null) {
//                driver.quit();
            }
        }
    }
}
