package iSkilloTesting;
//import object.*;
import factory.*;
//import object.Header;
//import object.HomePage;
//import object.LoginPage;
//import object.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends TestObject{
    @DataProvider(name="getUser")
    public Object[][] getUsers(){
        return new Object[][]{
                //To pass the first data object the userId needs to be changed to 5737
                {"mihpolyaTest","Tyui123Tyuio","5737"},

        };
    }

    @Test(dataProvider = "getUser")
    public void loginTest(String username, String password, String userId){
        WebDriver webDriver = super.getWebDriver();
        Header header = new Header(webDriver);
        LoginPage loginPage = new LoginPage(webDriver);
        ProfilePage profilePage = new ProfilePage(webDriver);
        HomePage homePage = new HomePage(webDriver);

        homePage.navigateTo();
        Assert.assertTrue(homePage.isUrlLoaded(), "Home page is not loaded");

        header.clickLogin();
        Assert.assertTrue(loginPage.isUrlLoaded(), "Current page is not Login");

        loginPage.fillInUserName(username);
        loginPage.fillInPassword(password);
        loginPage.checkRememberMe();
        Assert.assertTrue(loginPage.isCheckedRememberMe(), "Remember me checkbox is not checked.");

        loginPage.clickSignIn();

        header.clickProfile();

        Assert.assertTrue(profilePage.isUrlLoaded(), "Current page is not profile page");
        Assert.assertTrue(profilePage.isUrlLoaded(userId), "Current page in not profile page for " + userId + " user");



    }
}
