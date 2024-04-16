# SK12_Automation_Project
This final project of the automation testing course in Skillo Academy includes:
 - TestNG и Selenium WebDriver technologies
 - 5 different testing scenarious for  http://training.skillo-bg.com:4200/posts/all
 - the project must contain a  testng.xml file
 - all tests should be executable through mvn clean test command in Chrome browser
 - if a test failure occurs, there must be a screenshot saved in a directory of the project


Test scenarios:

1. Create a new profile\
   1.1. Go to home page\
   1.2. Find Login link button and click on it\
   1.3. Get redirected to login page \
   1.4. Find Register link button and click on it\
   1.5. Get redirected to sign up page \
   1.6. Find WebElement Username field and fill it in\
   1.7. Find WebElement email field and fill it in\
   1.8. Find WebElement Password field and fill it in\
   1.9. Find WebElement Confirm password and fill it with the same password\
   1.10. Find the Sign-in button and click on it\
   1.11. Get redirected to home page \
   1.12 Check if there is a WebElement New post in the Header\
   1.13. Close the browser\
   
2. Login with existing valid credentials\
   //preconditions: have valid credentials\
   2.1. Open  home page \
   2.2. Find WebElement Login link button and click on it\
   2.3. Get redirected to login page \
   2.4. Find WebElement Username or email and fill it in\
   2.5. Find WebElement Password and fill it in\
   2.6. Find WebElement Sign-in button and click on it\
   2.7. Get redirected to home page \
   2.8. Check if there is a WebElement New post in the Header\
   2.9. Close the browser
   
   
5. Login in and create a post
   
6. Login and delete a post
   
7. Login and follow on another user
