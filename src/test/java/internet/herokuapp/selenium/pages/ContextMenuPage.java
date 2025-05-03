package internet.herokuapp.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContextMenuPage {
    WebDriver driver;
    private final By byTitle = By.xpath("//div[@id='content']//descendant::h3");
    Actions actions;

    public ContextMenuPage(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        //private final WebElement contextMenu = driver.findElement(By.id("hot-spot"));
        PageFactory.initElements(driver, this);
    }
    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    public void rightClickOnHotSpot(){
        //Right-click the button to display Context Menu
        //actions.contextClick(contextMenu).perform();
    }
}
