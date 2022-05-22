package com.academy.techcenture.end2end;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static com.academy.techcenture.end2end.CommonUtils.*;
import java.time.Duration;


public class UserRegistrationEnd2End {

    private final static String APP_URL = "http://automationpractice.com/";

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Beginning registration end2end flow");

        //Configure the webdriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // visit the page
        driver.get(APP_URL);

        String title = driver.getTitle();

        if (title.equals("My Store")){
            System.out.println("Title is correct");
        }else{
            System.out.println("Title is NOT correct");
        }

        WebElement signInLink  = driver.findElement(By.linkText("Sign in"));

        if (signInLink.isDisplayed()){
            System.out.println("Clicking sign in link, because it exists");
            signInLink.click();
        }else{
            System.out.println("Sign in link does not exist on the page");
        }

        String emailAddress = randomEmail();
        System.out.println("Random email was generated: "  +emailAddress);

        WebElement newUserEmailInput = driver.findElement(By.id("email_create"));
        newUserEmailInput.clear();

        newUserEmailInput.sendKeys(emailAddress);

        WebElement createAccountBtn = driver.findElement(By.id("SubmitCreate"));

        if (createAccountBtn.isEnabled()){
            System.out.println("Create Account button is clicked");
            createAccountBtn.click();
        }else{
            System.out.println("Create Account button is not enabled");
        }

         title = driver.getTitle();

        if (title.equals("Login - My Store")){
            System.out.println("Title is correct");
        }else{
            System.out.println("Title is NOT correct");
        }

        WebElement mainHeader = driver.findElement(By.className("page-subheading"));

        if (mainHeader.getText().equals("CREATE AN ACCOUNT")){
            System.out.println("Main header is correct");
        }else{
            System.out.println("Main header is NOT correct");
        }

        int randomGender = randomNumber(1,2);

        WebElement genderRadioBtn = driver.findElement(By.xpath("//input[@id='id_gender"+randomGender+"']"));

        //click mr or mrs randomly
        genderRadioBtn.click();


        int atSign = emailAddress.indexOf("@");
        String[] fullName = emailAddress.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0,1).toUpperCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0,1).toUpperCase() + fullName[0].substring(1);
        String password = lastName + "123" + firstName.charAt(0) + "!"; //Hoppe123L!

        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInputRegistration = driver.findElement(By.id("email"));
        String emailValueRegistration = emailInputRegistration.getAttribute("value");

        //compare the randomly generated emial with populated email on the rgister page
        if (emailValueRegistration.equals(emailAddress)){
            System.out.println("Email ids are correct");
        }else{
            System.out.println("Email ids do NOT match");
        }


        WebElement passwdInput = driver.findElement(By.id("passwd"));
        passwdInput.sendKeys(password);


        String dob = randomDOB18OrAbove(); //1997-06-06
        String[] split = dob.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);

        WebElement days = driver.findElement(By.id("days"));
        WebElement months = driver.findElement(By.id("months"));
        WebElement years = driver.findElement(By.id("years"));

        Select select = new Select( days );
        select.selectByValue(String.valueOf(day));

        select = new Select( months );
        select.selectByValue(String.valueOf(month));

        select = new Select( years );
        select.selectByValue(String.valueOf(year));


        WebElement newsletter = driver.findElement(By.id("newsletter"));
        WebElement specialOffers = driver.findElement(By.id("optin"));

        if (!newsletter.isSelected()){
            System.out.println("Checked newsletter checkbox");
            newsletter.click();
        }else{
            System.out.println("Newsletter is already checked");
        }
        if (!specialOffers.isSelected()){
            System.out.println("Checked special offers checkbox");
            specialOffers.click();
        }else{
            System.out.println("Checked special offers is already checked");
        }


        WebElement addrsFirstName = driver.findElement(By.id("firstname"));
        String addrsFirstNameValue = addrsFirstName.getAttribute("value");
        if (addrsFirstNameValue.equals(firstName)){
            System.out.println("First name is correct in address first name input field");
        }else{
            System.out.println("First name is NOT correct in address first name input field");
        }


        WebElement addrsLastName = driver.findElement(By.id("lastname"));
        String addrsLastNameNameValue = addrsLastName.getAttribute("value");
        if (addrsLastNameNameValue.equals(lastName)){
            System.out.println("Last name is correct in address first name input field");
        }else{
            System.out.println("Last name is NOT correct in address first name input field");
        }

        //company
        WebElement company = driver.findElement(By.id("company"));
        String randomCompanyName = randomCompanyName();
        company.sendKeys( randomCompanyName);

        //address
        WebElement address1 = driver.findElement(By.id("address1"));
        String randomStrAddr = randomStrAddress();
        address1.sendKeys(randomStrAddr);


        WebElement city = driver.findElement(By.id("city"));
        String randomCity = randomCity();

        city.sendKeys(randomCity);

        WebElement stateDropDown = driver.findElement(By.id("id_state"));
        select = new Select(stateDropDown);
        String randomState = randomState();
        select.selectByVisibleText(randomState);


        //zip
        WebElement zipCodeInput = driver.findElement(By.id("postcode"));
        String randomZipCode = randomZipCode();
            zipCodeInput.sendKeys(randomZipCode);

        WebElement countryDropDown = driver.findElement(By.id("id_country"));
        select = new Select(countryDropDown);
        WebElement selectedCountry = select.getFirstSelectedOption();
        String selecteCountryTxt = selectedCountry.getText();
        if (selecteCountryTxt.equals("United States")){
            System.out.println("United States is selected correctly");
        }else{
            System.out.println("United States is not selected");
        }


        WebElement addionalInfo = driver.findElement(By.id("other"));
        String radomInfo = generateRandomString(50);

        addionalInfo.sendKeys(radomInfo);

        WebElement phoneNumber = driver.findElement(By.id("phone_mobile"));

        String phoneNumberTxt = randomPhoneNumber();
        phoneNumber.sendKeys(phoneNumberTxt);


        WebElement aliasAddres = driver.findElement(By.id("alias"));
        aliasAddres.sendKeys(randomStrAddr);


        WebElement registerBtn = driver.findElement(By.id("submitAccount"));

        if (registerBtn.isEnabled()){
            registerBtn.click();
        }


        System.out.println("NEW USER DETAILS: ");
        System.out.println("email: " + emailAddress);
        System.out.println("paswd: " + password);

        WebElement pageHeading = driver.findElement(By.className("page-heading"));
      if (pageHeading.isDisplayed()){
          System.out.println("Page heading is there!!!");
      }else{
          System.out.println("Page heading is not there");
      }

        WebElement userAccountName = driver.findElement(By.xpath("//div[@class='header_user_info']/a/span"));

      if (userAccountName.isDisplayed()){
          System.out.println("User Account name is displayed");
      }else{
          System.out.println("User Account name is not displayed");
      }

    //xpath for lists //div[contains(@class,'addresses-lists')]//li


        WebElement signOutLink = driver.findElement(By.linkText("Sign out"));

        signOutLink.click();

        driver.get(APP_URL);

        driver.findElement(By.xpath("//a[@class='login']")).click();

        driver.findElement(By.id("email")).sendKeys(emailAddress);
        driver.findElement(By.id("passwd")).sendKeys(password);

        driver.findElement(By.id("SubmitLogin")).click();

        Thread.sleep(2000);
        userAccountName =  driver.findElement(By.xpath("//div[@class='header_user_info']/a/span"));

        if (userAccountName.isDisplayed()){
            System.out.println("We were able to sign in with new credentials");
        }else{
            System.out.println("New Creds are not working");
        }

        //xpath for lists //div[contains(@class,'addresses-lists')]//li
        driver.findElement(By.linkText("Sign out")).click();
        System.out.println("Successfully signed out!!!!");

        Thread.sleep(5000);

        driver.quit();
        System.out.println("End of User registration ned2end flow");
    }
}










