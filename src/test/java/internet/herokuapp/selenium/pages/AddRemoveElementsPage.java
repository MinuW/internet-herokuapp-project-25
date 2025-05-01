package internet.herokuapp.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddRemoveElementsPage {
    private final WebDriver driver;
    private final By byTitle = By.xpath("//div[@id='content']//descendant::h3");

    public AddRemoveElementsPage(WebDriver driver) {
        this.driver = driver;
    }
    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }
    //Locate buttons Dynamically
    public void locateElements(String buttonName){
        WebElement contentBlock = driver.findElement(By.xpath("//div[@id='content']"));
        String paramText = String.format("./descendant::button[text()='%s']",buttonName);
        WebElement fullXpath = contentBlock.findElement(By.xpath(paramText));
        fullXpath.click();
    }
    public WebElement locateElementsName(String buttonName){
        WebElement contentBlock = driver.findElement(By.xpath("//div[@id='content']"));
        String paramText = String.format("./descendant::button[text()='%s']",buttonName);
        return contentBlock.findElement(By.xpath(paramText));
    }

}
