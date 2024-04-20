package iSkilloTesting;


import factory.PostPage;
import factory.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;

public class DeletePost extends TestObject{

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        File postPicture = new File("src\\test\\resources\\upload\\robin.png");
        String caption = "Testing upload file";
        return new Object[][]{

                {"mihpolyaTest","Tyui123Tyuio", "5737", postPicture, caption}
        };
    }
    @Test(dataProvider = "getUser")
    //login and create a post in quick steps as precondition for the Delete test
     public void  deletePost(String username, String password, String userId,File postPicture, String caption) throws InterruptedException {

        WebDriver webDriver = super.getWebDriver();
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage createPost = new PostPage(webDriver);

        createPost.quickPost(username,password,userId,postPicture,caption);
        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");

        int countBefore = profilePage.countTheElementsBeforePost();
        profilePage.clickLastElement();
        Assert.assertTrue(profilePage.isElementDetailedViewOpened(), "The image detailed view is not opened");

        profilePage.clickPostElementDeleteButton();
        profilePage.clickConfirmDeleteButton();
        int countAfter = profilePage.countTheElementsAfterPost();
        Assert.assertNotEquals(countBefore,countAfter, "No image is uploaded");
    }

}
