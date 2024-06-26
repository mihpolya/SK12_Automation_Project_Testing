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

                {"mihpolyaTest","Tyui123Tyuio", "5737", postPicture, caption}
        };
    }
   @Test(dataProvider = "getUser")
    public void testCreatePost(String username, String password, String userId, File postPicture, String caption) throws InterruptedException {
       WebDriver webDriver = super.getWebDriver();
       Header header = new Header(webDriver);
       LoginPage loginPage = new LoginPage(webDriver);
       ProfilePage profilePage = new ProfilePage(webDriver);
       PostPage postPage = new PostPage(webDriver);
       HomePage homePage = new HomePage(webDriver);

       loginPage.navigateTo();
       Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not login");

       loginPage.completeSignIn(username, password);
       Assert.assertTrue(homePage.isUrlLoaded(), "Current page is not home page");

       header.clickProfile();
       Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page is not profile page for "+ userId);

       int countBefore = profilePage.countTheElementsBeforePost();
       header.clickNewPost();
       Assert.assertTrue(postPage.isNewPostLoaded(), "The new post form is not loaded");

       postPage.uploadPicture(postPicture);
       Assert.assertTrue(postPage.isImageUploaded("robin.png"), "Image is not uploaded");
       Assert.assertEquals(postPage.uploadedImageText(), "robin.png", "Incorrect image is uploaded");

       postPage.typePostCaption(caption);
       postPage.clickCreatePost();

       int countAfter = profilePage.countTheElementsAfterPost();
       Assert.assertNotEquals(countBefore,countAfter, "No image is uploaded");

   }
}
