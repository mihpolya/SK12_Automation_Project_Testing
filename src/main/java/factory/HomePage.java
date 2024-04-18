package factory;

import dev.failsafe.internal.util.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    public static final String HOME_URL = "http://training.skillo-bg.com:4200/posts/all";
    private final WebDriver webDriver;
    String myId = "/users/5737";
    private WebElement userToFollow;
    @FindAll(@FindBy(xpath = "//a[@class='post-user']"))
    private List<WebElement> loadedUsersOnThePage;
    @FindBy(xpath = "//*[@class='col-6 follow-buttons']/button")
    private WebElement followButton;
    public HomePage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
    public void navigateTo(){
        this.webDriver.get(HOME_URL);
    }

    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(HOME_URL));
    }
    public void checkTheUserId(){
        for(WebElement user : loadedUsersOnThePage){
            if(!user.getAttribute("href").equals(myId)){
//                clickFollowButton();
//                userToFollow = user;
                break;
            }
            scrollDown();
            checkTheUserId();
        }
    }
    public void clickFollowButton(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(followButton));
        followButton.click();
    }
    public void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,7750)", "");
    }
    public boolean isUserFollowed(){
        if (followButton.getText().equals("Followed")){
            return false;
        }
        return true;
    }

}
