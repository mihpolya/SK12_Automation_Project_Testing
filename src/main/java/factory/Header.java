package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver webDriver;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy(id = "nav-link-profile")
    private WebElement profilePageLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement newPostLink;
    @FindBy(xpath = "//h3[text()='Post a picture to share with your awesome followers']")
    private WebElement newPostTitle;
    public Header(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver, this);
    }
    public void clickLogin(){
        loginLink.click();
    }
    public void clickNewPost(){
        newPostLink.click();
    }
    public void clickProfile(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(profilePageLink));
        profilePageLink.click();
    }

}
