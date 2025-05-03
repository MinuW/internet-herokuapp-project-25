package internet.herokuapp.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BrokenImagesPage {
    WebDriver driver;
    private final By byTitle = By.xpath("//div[@id='content']//descendant::h3");
    private  List< WebElement > imageList;
    Integer brokenImageCount = 0;

    public BrokenImagesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getImageList() {
        return imageList;
    }

    public void setImageList(List<WebElement> imageList) {
        this.imageList = imageList;
    }
    //Get Page Title
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }


}
