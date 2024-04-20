package iSkilloTesting;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class FollowTest extends TestObject{
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                {"mihpolyaTest","Tyui123Tyuio", "5737"}
        };
    }
    @Test(dataProvider = "getUser")
    public void testFollowUser(String username, String password, String userId){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        HomePage homePage = new HomePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login");

        loginPage.completeSignIn(username, password);
        Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not home page");

        homePage.checkTheUserId();
        homePage.clickFollowButton();
        Assert.assertTrue(homePage.isUserFollowed(), "The user is not followed");
    }
}
