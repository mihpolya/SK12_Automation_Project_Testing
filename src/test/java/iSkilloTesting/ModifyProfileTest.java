package iSkilloTesting;

import factory.Header;
import factory.LoginPage;
import factory.PostPage;
import factory.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class ModifyProfileTest extends TestObject{
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        File postPicture = new File("src\\test\\resources\\upload\\robin.png");
        String caption = "Testing upload file";
        return new Object[][]{

                {"mihpolyaTest","Tyui123Tyuio", "5737", "this is a very long test text to occupy two rows of text field"}
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
        profilePage.editUserButton.click();
        profilePage.fillInPublicInfoTextArea(text);

        //find save button and click
        //check if the public info is changed

    }
}
