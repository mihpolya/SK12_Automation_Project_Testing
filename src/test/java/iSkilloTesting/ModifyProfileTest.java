package iSkilloTesting;

import factory.Header;
import factory.LoginPage;
import factory.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class ModifyProfileTest extends TestObject{
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        String text = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...";
        return new Object[][]{

                {"mihpolyaTest","Tyui123Tyuio", "5737", text}
        };
    }
    @Test(dataProvider = "getUser")
    public void testModifyPublicInfo(String username, String password, String userId, String text) {
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);

        loginPage.navigateTo();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login");

        loginPage.completeSignIn(username, password);
        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page for "+ userId);
        profilePage.clickEditUserButton();
        profilePage.fillInPublicInfoTextArea(text);
        profilePage.clickEditUserSaveButton();
        Assert.assertEquals(profilePage.profileText(text), "Public info does not match the entered text");


    }
}
