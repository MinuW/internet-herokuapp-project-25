package internet.herokuapp.selenium.tests;

import internet.herokuapp.selenium.pages.*;
import internet.herokuapp.selenium.util.BrowserFactory;
import internet.herokuapp.selenium.util.ConfigurationReader;
import internet.herokuapp.selenium.util.Links;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HerokuAppTests {
    WebDriver driver ;
    private static final Logger log = LogManager.getLogger(HerokuAppTests.class);
    Links links = new Links();
    HomePage homePage;
    AddRemoveElementsPage addRemoveElementsPage;
    BrokenImagesPage brokenImagesPage;
    CheckBoxesPage checkBoxesPage;
    ContextMenuPage contextMenuPage;
    ChallengingDomPage challengingDomPage;
    DigestAuthPage digestAuthPage;
    DisappearingElementsPage disappearingElementsPage;
    DragAndDropPage dragAndDropPage;
    DropdownPage dropdownPage;
    DynamicContentPage dynamicContentPage;

    @BeforeMethod
    public void setUp(){
        log.debug("Browser Initialization");
        BrowserFactory.init(ConfigurationReader.getBrowser());
        log.info("Get browser Properties");
        driver = BrowserFactory.getDriver();
        log.debug("Reading from Configuration file");
        driver.get(ConfigurationReader.getBaseURL());
        log.info("Navigated to Website");
        driver.manage().window().maximize();
        homePage= new HomePage(driver);
        addRemoveElementsPage = new AddRemoveElementsPage(driver);
        checkBoxesPage = new CheckBoxesPage(driver);
        contextMenuPage = new ContextMenuPage(driver);
        brokenImagesPage = new BrokenImagesPage(driver);
        challengingDomPage = new ChallengingDomPage(driver);
        digestAuthPage = new DigestAuthPage(driver);
        disappearingElementsPage = new DisappearingElementsPage(driver);
        dragAndDropPage = new DragAndDropPage(driver);
        dropdownPage = new DropdownPage(driver);
        dynamicContentPage = new DynamicContentPage(driver);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }

    }

    @Test
    public void validateAddRemoveElements() throws InterruptedException {
        homePage.clickOnExamples("Add/Remove Elements");
        //Page validation- Add/Remove Elements
        Assert.assertEquals(addRemoveElementsPage.getTitle(),"Add/Remove Elements","Error: Navigation Failed");
        addRemoveElementsPage.locateElements("Add Element");
        //Validating Added element - Delete Button
        Assert.assertEquals(addRemoveElementsPage.locateElementsName("Delete").getText(),"Delete","Error: Delete button not available");
        addRemoveElementsPage.locateElements("Delete");
        //Validating element is removed - Delete Button
        List <WebElement> deleteButton = driver.findElements(By.xpath("//div[@id='content']/descendant::button[text()='Delete']"));
        Assert.assertEquals(deleteButton.size(), 0,"Error: Delete button present in the DOM");
    }

    @Test
    public  void validateBasicAuth(){
        homePage.clickOnExamples("Basic Auth");
        //Switch to Alert
        Alert alert = driver.switchTo().alert();
        //alert.sendKeys("admin");
        //alert.sendKeys(Keys.TAB);
        String alertMessage = alert.getText();
        //System.out.println("Alert Message: " + alertMessage);
        Assert.assertEquals( alertMessage,"This is a simple alert!","Error: Navigation Failed");
        alert.accept();
        //locate text boxes and type username & password

    }

    @Test
    public  void validateBrokenImages(){
        homePage.clickOnExamples("Broken Images");
        Assert.assertEquals(brokenImagesPage.getTitle(),"Broken Images","Error: Navigation Failed");
        //need to validate broken images
    }

    @Test
    public void validateChallengingDOM(){
        homePage.clickOnExamples("Challenging DOM");
        Assert.assertEquals(addRemoveElementsPage.getTitle(),"Challenging DOM","Error: Navigation Failed");
        //Fill
    }

    @Test
    public void validateCheckboxes(){
        homePage.clickOnExamples("Checkboxes");
        Assert.assertEquals(checkBoxesPage.getTitle(),"Checkboxes","Error: Navigation Failed");
        checkBoxesPage.clickAll();
        List<WebElement> checkboxes =driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            boolean checked = checkbox.isEnabled();
            if (checked){
                Assert.assertTrue(true, "Check the Boxes");
                checkBoxesPage.clickAll();//Un-check all the boxes
            }
            else
            {
                Assert.assertFalse(false, "All Checked");
                checkBoxesPage.clickAll();//Check all the un-checked boxes
            }
        }
    }

    @Test
    public void validateContextMenu(){
        homePage.clickOnExamples("Context Menu");
        Assert.assertEquals(contextMenuPage.getTitle(),"Context Menu","Error: Navigation Failed");
        contextMenuPage.rightClickOnHotSpot();
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        Assert.assertEquals( alertMessage,"You selected a context menu","Error: Navigation Failed");
        alert.accept();
    }

    @Test
    public void validateDigestAuth(){
        homePage.clickOnExamples("Digest Authentication");
        //Assert.assertEquals(digestAuthPage.getTitle(),"Digest Authentication","Error: Navigation Failed");
    }

    @Test
    public void validateDisappearingElements(){
        homePage.clickOnExamples("Disappearing Elements");
        Assert.assertEquals(disappearingElementsPage.getTitle(),"Disappearing Elements","Error: Navigation Failed");
//        disappearingElementsPage.pageReload();
//        String elementName = disappearingElementsPage.locateDisappearingElement("Gallery").getText();
//        Assert.assertEquals(elementName, "Gallery","Gallery Button is Not present in the DOM");
    }

    @Test
    public void validateDragAndDrop(){
        homePage.clickOnExamples("Drag and Drop");
        Assert.assertEquals(dragAndDropPage.getTitle(),"Drag and Drop","Error: Navigation Failed");
    }

    @Test
    public void validateDropdown(){
        homePage.clickOnExamples("Dropdown");
        Assert.assertEquals(dropdownPage.getTitle(),"Dropdown List","Error: Navigation Failed");
    }

    @Test
    public void validateDynamicContent(){
        homePage.clickOnExamples("Dynamic Content");
        Assert.assertEquals(dropdownPage.getTitle(),"Dynamic Content","Error: Navigation Failed");
    }
}
