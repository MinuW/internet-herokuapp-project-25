package internet.herokuapp.selenium.base;
import internet.herokuapp.selenium.pages.HomePage;
import internet.herokuapp.selenium.util.BrowserFactory;
import internet.herokuapp.selenium.util.ConfigurationReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {
    WebDriver driver;
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
    }

    @Test
    public void validateHomePage(){
        HomePage homePage= new HomePage(driver);
        String title = homePage.getTitle();
        Assert.assertEquals(title, "Welcome to the-internet", "Error: You are not in the Home Page !");

    }
    @Test
    public void addRemoveElements() throws InterruptedException {
        driver.findElement(By.cssSelector("a[href='/add_remove_elements/']")).click();
        Thread.sleep(1000);
        Assert.assertEquals((driver.findElement(By.cssSelector("div[id='content'] h3"))).getText(),"Add/Remove Elements","Error: Navigation Failed");

    }
}
