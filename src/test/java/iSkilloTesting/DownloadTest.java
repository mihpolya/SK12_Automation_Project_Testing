package iSkilloTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class DownloadTest extends TestObject {
    @Test
    public void testDownload() throws InterruptedException {
        WebDriver driver = super.getWebDriver();
        driver.get("https://demoqa.com/upload-download");
        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        //can put a verification here if the button is present or that it is clickable
        downloadButton.click();
        //Verification
        String fileName = "sampleFile.jpeg";
        File file = new File(DOWNLOAD_DIR.concat(fileName));
        Assert.assertTrue(isFileDownloaded(file), "The file is not downloaded");
    }

    private boolean isFileDownloaded(File file) throws InterruptedException {
        int waitTime = 20;
        int counter = 0;

        while (counter < waitTime) {
            if (file.exists()) {
                return true;
            }
            //this waits for one second. No conditions just waits for a second. Not a good practice in the long term
            //we have 20 seconds in which to wait for the file to download
            Thread.sleep(1000);
            counter++;
        }
        return false;
    }
}
