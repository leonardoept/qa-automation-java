package com.leonardo.tests;

import java.time.Duration;
import com.leonardo.base.BaseTest;
import com.leonardo.pages.LoginPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginExitoso() {
        new LoginPage(driver).login("standard_user", "secret_sauce");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @DataProvider(name = "loginsFallidos")
    public Object[][] loginsFallidos() {
        return new Object[][] {
            {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
            {"standard_user", "clave_mala", "Epic sadface: Username and password do not match any user in this service"},
            {"", "secret_sauce", "Epic sadface: Username is required"},
            {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "loginsFallidos")
    public void loginFallido(String usuario, String password, String mensajeEsperado) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(usuario, password);
        Assert.assertEquals(loginPage.getErrorMessage(), mensajeEsperado);
    }
}