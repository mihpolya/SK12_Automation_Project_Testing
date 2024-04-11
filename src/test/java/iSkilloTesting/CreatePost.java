package iSkilloTesting;

import factory.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class CreatePost extends TestObject{
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
            File postPicture = new File("src\\test\\resources\\upload\\robin.png");
            String caption = "Testing upload file";
        return new Object[][]{
                //To pass the first data object the userId needs to be changed to 5508
                {"mihpolyaTest","Tyui123Tyuio", "5737", postPicture, caption}
        };
    }

   @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPicture, String caption){
       WebDriver webDriver = super.getWebDriver();
       Header header = new Header(webDriver);
       LoginPage loginPage = new LoginPage(webDriver);
       ProfilePage profilePage = new ProfilePage(webDriver);
       PostPage postPage = new PostPage(webDriver);

       loginPage.navigateTo();
       Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login");

       loginPage.fillInUserName(username);
       loginPage.fillInPassword(password);
       loginPage.checkRememberMe();
       Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

       loginPage.clickSignIn();
       header.clickProfile();
       Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page for "+ userId);

       header.clickNewPost();
       Assert.assertTrue(postPage.isNewPostLoaded(), "The new post form is not loaded");

       postPage.uploadPicture(postPicture);
       Assert.assertTrue(postPage.isImageUploaded("robin.png"), "Image is not uploaded");
       Assert.assertEquals(postPage.uploadedImageText(), "robin.png", "Incorrect image is uploaded");

       postPage.typePostCaption(caption);
       postPage.clickCreatePost();
   }
}
