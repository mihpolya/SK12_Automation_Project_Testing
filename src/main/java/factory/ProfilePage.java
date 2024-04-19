package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.io.*;
import java.lang.Thread;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/";
    private final WebDriver webDriver;
    @FindAll(@FindBy(xpath = "//*[@class='post-img']"))
    private List<WebElement> allPostedElements;
    @FindBy(xpath = "//*[@class='post-img']")
    private WebElement postElements;
    @FindBy(xpath = "//*[@class ='delete-ask']/a")
    private WebElement postElementDeleteButton;
    @FindBy(xpath = "//*[@class ='delete-confirm']/button[contains(text(),'Yes')]")
    private WebElement confirmDeleteButton;
    @FindBy(xpath = "//*[@class='fas fa-user-edit ng-star-inserted']")
    private WebElement editUserButton;
    @FindBy(xpath = "//*[@formcontrolname='publicInfo']")
    public WebElement publicInfoTextArea;
    @FindBy(xpath = "//*[@type='submit']")
    private  WebElement editUserSaveButton;
    @FindBy(xpath = "//*/p/text()")
    public WebElement profileText;
    public ProfilePage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver(), this);
    }

    public WebDriver webDriver()
    {
        return this.webDriver;
    }
    public void navigateTo(){
        this.webDriver.get(PAGE_URL);
    }
    public boolean isUrlLoaded(String userId){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlToBe(PAGE_URL + userId));
    }
    public boolean isUrlLoaded(){
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        return wait.until(ExpectedConditions.urlContains(PAGE_URL));
    }
    public void clickLastElement(){
        WebElement lastElement = allPostedElements.get(allPostedElements.size()-1);
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(lastElement));
        lastElement.click();
    }
    public void clickPostElementDeleteButton(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(postElementDeleteButton));
        postElementDeleteButton.click();
    }
    public void clickConfirmDeleteButton(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDeleteButton.click();
    }
    public void clickEditUserButton(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(editUserButton));
        editUserButton.click();
    }
    public void fillInPublicInfoTextArea(String text){

        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(publicInfoTextArea));
        publicInfoTextArea.sendKeys(text);
    }
    public void clickEditUserSaveButton(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(editUserSaveButton));
        editUserSaveButton.click();
    }
    public String profileText(String text) {
        return text;
    }
    public int countTheElementsBeforePost(){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(allPostedElements));
        return allPostedElements.size();
    }

    public int countTheElementsAfterPost() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(240));
        wait.until(ExpectedConditions.visibilityOfAllElements(allPostedElements));
        Thread.sleep(1000);
        return allPostedElements.size();
    }


}
