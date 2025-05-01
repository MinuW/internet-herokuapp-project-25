package internet.herokuapp.selenium.tests;

import internet.herokuapp.selenium.base.BaseTest;
import internet.herokuapp.selenium.pages.AddRemoveElementsPage;
import internet.herokuapp.selenium.pages.HomePage;
import internet.herokuapp.selenium.util.BrowserFactory;
import internet.herokuapp.selenium.util.ConfigurationReader;
import internet.herokuapp.selenium.util.Links;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HerokuAppTests {
    WebDriver driver ;
    private static final Logger log = LogManager.getLogger(HerokuAppTests.class);
    Links links = new Links();
    HomePage homePage;
    AddRemoveElementsPage addRemoveElementsPage;

    public HerokuAppTests() {

    }

    @BeforeMethod
    public void setUp(){
        log.debug("Browser Initialization");
        BrowserFactory.init(ConfigurationReader.getBrowser());
        log.info("Get browser Properties");
        driver = BrowserFactory.getDriver();
        log.debug("Reading from Configuration file");
        driver.get(ConfigurationReader.getBaseURL());
        log.info("Navigated to Website");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        addRemoveElementsPage = new AddRemoveElementsPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }

    @Test
    public void addRemoveElements() throws InterruptedException {
        homePage.clickOnExamples("Add/Remove Elements");
        Assert.assertEquals(addRemoveElementsPage.getTitle(),"Add/Remove Elements","Error: Navigation Failed");
        addRemoveElementsPage.locateElements("Add Element");
        Assert.assertEquals(addRemoveElementsPage.locateElementsName("Delete").getText(),"Delete","Error: Delete button not available");
        addRemoveElementsPage.locateElements("Delete");
    }

}
