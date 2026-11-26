package com.leonardo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // GitHub Actions define la variable de entorno CI=true.
        // Ahí no hay pantalla, así que Chrome corre en modo headless (sin ventana).
        boolean enCI = System.getenv("CI") != null;
        if (enCI) {
            options.addArguments("--headless=new", "--window-size=1920,1080",
                                 "--no-sandbox", "--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);   // Selenium descarga solo el driver de Chrome
        if (!enCI) {
            driver.manage().window().maximize();
        }
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
