package internet.herokuapp.selenium.pages;

import internet.herokuapp.selenium.base.BaseTest;
import internet.herokuapp.selenium.util.Links;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    private final By byTitle = By.className("heading");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }


    public void clickOnExamples(String linkName){
        WebElement contentBlock = driver.findElement(By.xpath("//div[@id='content']"));
        String toClick = String.format("./descendant::a[text()='%s']", linkName);
        WebElement linkToClick = (contentBlock.findElement(By.xpath(toClick)));
        Links links = new Links();
        links.setName(linkName);
        linkToClick.click();

    }

}
