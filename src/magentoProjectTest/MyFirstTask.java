package magentoProjectTest;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.swing.text.StyledEditorKit.BoldAction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class MyFirstTask {

	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://magento.softwaretestingboard.com/"; // variable
	Random Rand = new Random();
	String password = "MyPass_5443";
	String logoutLink = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String emailforSignUp = "";

	@BeforeTest
	public void mySetUp() {
		driver.manage().window().maximize();
		driver.get(myWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, enabled = true)
	public void createAnAccount() {
		// xpath
//		WebElement createAccountPage = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/customer/account/create/']"));
		// partialLinkText

//		WebElement createAccountPage = driver.findElement(By.partialLinkText("Account"));

		// linkText
//		WebElement CreateAccount = driver.findElement(By.linkText("Create an Account"));
		// cssSelctor
		WebElement CreateAccount = driver
				.findElement(By.cssSelector("header[class='page-header'] li:nth-child(3) a:nth-child(1)"));
		CreateAccount.click();
		// first names
		String[] firstNames = { "Alice", "Bob", "Charlie", "David", "Eva" };
		// last names
		String[] lastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis" };
		int randonIndexForFirstName = Rand.nextInt(firstNames.length); // inside nextint called bound
		int randonIndexForLastName = Rand.nextInt(lastNames.length);
		System.out.println(randonIndexForFirstName);
		System.out.println(randonIndexForLastName);

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailAddressInput = driver.findElement(By.id("email_address"));
		WebElement passInput = driver.findElement(By.id("password"));
		WebElement passComfirmInput = driver.findElement(By.id("password-confirmation"));
		WebElement signUpButton = driver.findElement(By.xpath(" //button[@title='Create an Account']"));

		String firstName = firstNames[randonIndexForFirstName];
		String lastName = lastNames[randonIndexForFirstName];
		String domian = "@gmail.com";
		int randomNamber = Rand.nextInt(3456666);

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		emailAddressInput.sendKeys(firstName + lastName + randomNamber + domian);
		passInput.sendKeys(password);
		passComfirmInput.sendKeys(password);
		signUpButton.click();
		emailforSignUp = firstName + lastName + randomNamber + domian;
		WebElement MassegeAsWebElement = driver.findElement(By.className("messages"));
		String TheActualMassage = MassegeAsWebElement.getText();
		String TheExptedMassage = "Thank you for registering with Main Website Store.";
        Assert.assertEquals(TheActualMassage,TheExptedMassage); //to check the test is pass or not , we can't use if statement becuase the if statment Used to implement the code, not to test the element 
	}

	@Test(priority = 2) // invocationCount: number re-do the test
	public void logout() {
		driver.get(logoutLink);
		WebElement LogoutMassege = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));
        String ActualMsg =  LogoutMassege.getText();
        String ExcpetedMsg = "You are signed out";
        Assert.assertEquals(ActualMsg, ExcpetedMsg);
        

	}

	@Test(priority = 3) // invocationCount: number re-do the test
	public void signIn() {
		WebElement signIn = driver.findElement(By.linkText("Sign In"));
		signIn.click();
		WebElement emailSignInInput = driver.findElement(By.id("email"));
		WebElement signInButton = driver.findElement(By.cssSelector(".action.login.primary"));

		emailSignInInput.sendKeys(emailforSignUp);
		WebElement passSignInInput = driver.findElement(By.id("pass"));
		passSignInInput.sendKeys(password);
		signInButton.click();
		String WelcomeMsg = driver.findElement(By.className("logged-in")).getText();
		Boolean AcualMsg = WelcomeMsg.contains("Welcome");
		Boolean ExcptedMsg = true ;
		
		Assert.assertEquals(AcualMsg, ExcptedMsg);

	}

	@Test(priority = 4) // invocationCount: number re-do the test
	public void addMenItem() throws InterruptedException {
		WebElement menSection = driver.findElement(By.cssSelector("a[id='ui-id-5'] span:nth-child(2)"));
		menSection.click();
		// System.out.println(driver.findElements(By.className("product-item")).size());
		WebElement productItemsConteriner = driver.findElement(By.className("product-items"));
		// System.out.println(productItemsConteriner.findElements(By.tagName("li")).size());
		List<WebElement> Allitems = productItemsConteriner.findElements(By.tagName("li")); // findElements:retrun list
																							// type webElement

		int totalNumberOfItems = Allitems.size();
		int randomItem = Rand.nextInt(totalNumberOfItems);
		Allitems.get(randomItem).click();
		WebElement theContinerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));

		// select any one for me i will select the firt one
        //.size : when array isn't static "dynamic", .lenghth:when array is static
		// System.out.println(theContinerOfSizes.findElements(By.className("swatch-option")).size());
		// System.out.println(theContinerOfSizes.findElements(By.tagName("div")).size());
		List<WebElement> allSizes =theContinerOfSizes.findElements(By.className("swatch-option"));
		int numberOfSizes = allSizes.size();
        int randomSize = Rand.nextInt(numberOfSizes);
        allSizes.get(randomSize).click();
		
        
        WebElement theContinerOfColor = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
       // "you can use also this instide of className : "List<WebElement> allColor =theContinerOfColor.findElements(By.tagName("div"));

		List<WebElement> allColor =theContinerOfColor.findElements(By.className("swatch-option"));
		int numberOfColor = allColor.size();
        int randomColor = Rand.nextInt(numberOfColor);
        allColor.get(randomColor).click();
        
        WebElement AddToCartButton = driver.findElement(By.id("product-addtocart-button"));
        AddToCartButton.click();
        WebElement massegeAdd = driver.findElement(By.className("message-success"));
        boolean ActualMsg = massegeAdd.getText().contains("You added");
        boolean ExpctedMsg = true;
        //        System.out.println(massegeAdd.getText().contains("You added"));
        Assert.assertEquals(ActualMsg, true); //to make sure the test is pass or not
	}

}
