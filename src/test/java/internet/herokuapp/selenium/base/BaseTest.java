package internet.herokuapp.selenium.base;
import internet.herokuapp.selenium.pages.AddRemoveElementsPage;
import internet.herokuapp.selenium.pages.HomePage;
import internet.herokuapp.selenium.util.BrowserFactory;
import internet.herokuapp.selenium.util.ConfigurationReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class BaseTest {
    WebDriver driver;
    HomePage homePage;
    AddRemoveElementsPage addRemoveElementsPage;
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    public BaseTest() {
    }

    @BeforeMethod
    public void setUp(){
        this.initialSetUp();
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }

    public void initialSetUp() {
        log.debug("Browser Initialization");
        BrowserFactory.init(ConfigurationReader.getBrowser());
        log.info("Get browser Properties");
        driver = BrowserFactory.getDriver();
        log.debug("Reading from Configuration file");
        driver.get(ConfigurationReader.getBaseURL());
        log.info("Navigated to Website");
        driver.manage().window().maximize();
        homePage= new HomePage(driver);
        addRemoveElementsPage = new AddRemoveElementsPage(driver);
    }

    @Test
    public void validateHomePage(){
        String title = homePage.getTitle();
        Assert.assertEquals(title, "Welcome to the-internet", "Error: You are not in the Home Page !");

    }
    @Test
    public void addRemoveElements() throws InterruptedException {
        driver.findElement(By.cssSelector("a[href='/add_remove_elements/']")).click();
        Thread.sleep(1000);
        Assert.assertEquals((driver.findElement(By.cssSelector("div[id='content'] h3"))).getText(),"Add/Remove Elements","Error: Navigation Failed");

    }

    @Test
    public void validateAddRemoveElements() throws InterruptedException {
        homePage.clickOnExamples("Add/Remove Elements");
        //Page validation- Add/Remove Elements
        Assert.assertEquals(addRemoveElementsPage.getTitle(),"Add/Remove Elements","Error: Navigation Failed");
        addRemoveElementsPage.locateElements("Add Element");
        //Validating Added element - Delete Button
        Assert.assertEquals(addRemoveElementsPage.locateElementsName("Delete").getText(),"Delete","Error: Delete button not available");
        addRemoveElementsPage.locateElements("Delete");
        //Validating element is removed - Delete Button
        List<WebElement> deleteButton = driver.findElements(By.xpath("//div[@id='content']/descendant::button[text()='Delete']"));
        Assert.assertEquals(deleteButton.size(), 0,"Error: Delete button present in the DOM");
    }
}
