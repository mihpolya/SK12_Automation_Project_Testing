package factory;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4200/users/register";
    private final WebDriver webDriver;

    public SignUpPage(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(webDriver(), this);
    }
    public WebDriver webDriver()
    {
        return this.webDriver;
    }


    private String generateRandomEmail(int minLengthInclusive, int maxLengthInclusive) {
        return generateRandomAlphabeticString(minLengthInclusive, maxLengthInclusive) + "@gmail.com";
    }

    private String generateRandomAlphabeticString(int minLengthInclusive, int maxLengthInclusive) {
        return RandomStringUtils.randomAlphanumeric(minLengthInclusive, maxLengthInclusive);
    }
}
