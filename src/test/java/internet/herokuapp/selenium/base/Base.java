package internet.herokuapp.selenium.base;
import internet.herokuapp.selenium.util.BrowserFactory;
import internet.herokuapp.selenium.util.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Base {
    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        BrowserFactory.init(ConfigurationReader.getBrowser());
        driver = BrowserFactory.getDriver();
        driver.get(ConfigurationReader.getBaseURL());
        driver.manage().window().maximize();

    }
    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }
}
