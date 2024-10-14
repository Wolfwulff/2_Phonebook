package com.phonebook;

import com.phonebook.model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if (app.getUserHelper().isSignOutButtonPresent()) {
            app.getUserHelper().logout();
        }
    }

    @Test
    public void createAccountPositiveTest1() {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().fillInRegistrationForm(new User() //COMBINED THE EMAIL AND PASSWORD METHOD
                .setEmail("user_admin_new242@gmail.com")
                .setPassword("Password@1"));
        app.getUserHelper().clickRegistrationButton();
        Assert.assertTrue(app.getUserHelper().isSignOutButtonPresent()); //After successful user registration, click Sign Out
    }


    @Test
    public void createAccountPositiveTest2() {
        app.getUserHelper().register("user_admin_new2421@gmail.com", "Password@1");
    }

    @Test
    public void createAccountLoginPositiveTest() {
        app.getUserHelper().register("user_admin_new242@gmail.com", "Password@1");
        app.getUserHelper().logout();
        app.getUserHelper().login("user_admin_new242@gmail.com", "Password@1");
    }

    @Test
    public void createAccountNegativeTest() {
        SoftAssert softAssert = new SoftAssert();
        app.getUserHelper().clickLoginLink();
        //fillInRegistrationForm(new User("user_admin_new242@gmail.com", "Password1@"));
        app.getUserHelper().fillInRegistrationForm(new User()
                .setEmail("user_admin_new242@gmail.com")
                .setPassword("Password@1"));
        app.getUserHelper().clickRegistrationButton();
        //Assert.assertFalse(isSignOutButtonPresent());
        //Assert.assertTrue(isAlertPresent());
        //Assert.assertTrue(isError409Present());
      /*
         * In Java, SoftAssert is a class provided by the TestNG library that is used to perform "soft"
         * soft assertions in tests. Unlike "hard" assertions, which immediately interrupt
         * test execution in case of error, soft assertions allow you to continue executing the test even if one of the assertions fails.
         * Purpose: SoftAssert is used to test multiple conditions within a single test without interrupting its execution on the first failure
         */
        softAssert.assertTrue(app.getUserHelper().isAlertPresent());
        softAssert.assertTrue(app.getUserHelper().isError409Present());
        softAssert.assertAll();
        /*
         * Purpose: assertAll() is used to check all assertions made with SoftAssert at the end of the test.
         * If one or more assertions fail, assertAll() will throw an exception and the test will be marked as failed
         */
    }
    @AfterMethod
    public void postCondition(){
        try {
            app.getUserHelper().logout();
        } catch (Exception e) {
            // throw new RuntimeException(e);
        }
    }

}

