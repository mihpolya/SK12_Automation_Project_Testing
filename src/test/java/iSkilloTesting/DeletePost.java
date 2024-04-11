package iSkilloTesting;

import factory.Header;
import factory.LoginPage;
import factory.PostPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class DeletePost extends TestObject{
    WebDriver webDriver = super.getWebDriver();
    Header header = new Header(webDriver);
    LoginPage loginPage = new LoginPage(webDriver);
    PostPage createPost = new PostPage(webDriver);
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
     public void loginAndCreateThePost(String username, String password, File postPicture, String caption){
        createPost.quickPost(username,password,postPicture,caption);
    }
    @Test(dependsOnMethods="loginAndCreateThePost")
    public void deletePost(){


    }
}
