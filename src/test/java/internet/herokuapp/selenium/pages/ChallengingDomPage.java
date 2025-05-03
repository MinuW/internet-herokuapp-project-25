package internet.herokuapp.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ChallengingDomPage {

    public ChallengingDomPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
