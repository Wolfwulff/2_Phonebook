package com.phonebook;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {

    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        if (!app.getUserHelper().isSignOutButtonPresent()) {
            app.getUserHelper().login("user_admin_new242@gmail.com", "Password@1");
        }
    }

    @Test(invocationCount = 1, priority = 1)
    public void addContactPositiveTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

    @Test(priority = 5)
    public void addContactPositiveWODescriptionTest() {
        app.getContactHelper().addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
    }

}


