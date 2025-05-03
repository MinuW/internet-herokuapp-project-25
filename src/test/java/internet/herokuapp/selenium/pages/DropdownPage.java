package internet.herokuapp.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class DropdownPage {
    WebDriver driver;
    private final By byTitle = By.xpath("//div[@id='content']//descendant::h3");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }
}
