package internet.herokuapp.selenium.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DisappearingElementsPage {
    WebDriver driver;
    private final By byTitle = By.xpath("//div[@id='content']//descendant::h3");
    private By byElementPath;

    public DisappearingElementsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }
    
    public WebElement locateDisappearingElement(String buttonName){
        WebElement contentBlock = driver.findElement(By.xpath("//div[@id='content']"));
        String paramText = String.format("./descendant::a[text()='%s']",buttonName);
        WebElement fullXpath = contentBlock.findElement(By.xpath(paramText));
        return contentBlock.findElement(By.xpath(paramText));
    }

    public void pageReload(){
        byElementPath= By.xpath("//div[@id='content']/descendant::a[text()='Gallery']");
        //WebElement newElem = driver.findElement(By.xpath("//div[@id='content']/descendant::a[text()='Gallery']"));
        while(!driver.findElement(byElementPath).isDisplayed()){
            driver.navigate().refresh();//refresh the page until Gallery button appears
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElementPath));
        }
    }
}

