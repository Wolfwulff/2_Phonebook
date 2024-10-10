package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            app.getUserHelper().logout();
        }
        app.driver.get("https://telranedu.web.app/login");
    }

    @Test
    public void loginExistedUserPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        //fillInRegistrationForm(new User("user_admin_new242@gmail.com", "Password@1"));
        app.getUserHelper().fillInRegistrationForm(new User().setEmail("user_admin_new242@gmail.com").setPassword("Password@1"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent());
    }

    @Test
    public void loginExistedUserPositiveTest2(ITestContext context) {
        String email = "user_admin_new242@gmail.com";
        String password = "Password@1";
        context.setAttribute("email",email);
        context.setAttribute("password",password);
        app.getUserHelper().login(email, password);
    }

    @Test
    public void loginNegativeWOEmailTest() {
        app.getUserHelper().fillInRegistrationForm(new User()
                //  .setEmail("user_admin_new242@gmail.com")
                .setPassword("Password@1"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertEquals(app.getUserHelper().alertTextPresent(), "Wrong email or password");
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
    }

    @AfterMethod(enabled = true)
    public void postConditions() {
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }
}