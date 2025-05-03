package internet.herokuapp.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckBoxesPage {
    WebDriver driver;
    private final By byTitle = By.xpath("//div[@id='content']//descendant::h3");

    public CheckBoxesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAll(){
        List<WebElement> checkboxes =driver.findElements(By.cssSelector("input[type='checkbox']"));
        int size= checkboxes.size();

        for (WebElement checkbox : checkboxes) {
            checkbox.click();
        }
    }
    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }
}
