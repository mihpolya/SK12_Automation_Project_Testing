package iSkilloTesting;

import factory.Header;
import factory.LoginPage;
import factory.PostPage;
import factory.ProfilePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class DeletePost extends TestObject{
//    ChromeDriver webDriver;
//    @BeforeMethod(alwaysRun = true)
//            public void beforeTests(){
//
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().clearDriverCache().setup();
//        webDriver = new ChromeDriver();
//        webDriver.manage().window().maximize();
//    }

    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        File postPicture = new File("src\\test\\resources\\upload\\robin.png");
        String caption = "Testing upload file";
        return new Object[][]{

                {"mihpolyaTest","Tyui123Tyuio", postPicture, caption}
        };
    }
    @Test(dataProvider = "getUser")
    //login and create a post in quick steps as precondition for the Delete test
     public void loginAndCreateThePost(String username, String password, File postPicture, String caption){

        WebDriver webDriver = super.getWebDriver();
//        WebDriver webDriver = new WebDriver();
//        Header header = new Header(webDriver);
//        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        PostPage createPost = new PostPage(webDriver);

        createPost.quickPost(username,password,postPicture,caption);
        profilePage.clickLastElement();
        profilePage.clickPostElementDeleteButton();
        profilePage.clickConfirmDeleteButton();
        //all operations should happen in this @Test
    }
//    @Test(dependsOnMethods="loginAndCreateThePost")
//    public void deletePost(){
////        WebDriver webDriver = super.getWebDriver();
//        ProfilePage profilePage = new ProfilePage(webDriver);
//        profilePage.navigateTo();
//        Assert.assertTrue(profilePage.isPostAvailable(), "There is no post available to delete");
//
//    }
}
