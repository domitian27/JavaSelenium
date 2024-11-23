import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * In acest test vom:
 * - accesa google.
 * - refuza cookie-uri
 * - cauta pe google prin input box "Java"
 * - afisa in consola numarul de rezultate de pe pagina!
 */
public class firstRun {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new EdgeDriver();  // ChromeDriver initialization
        driver.manage().window().maximize();
    }

    @Test
    public void TestPrintNumberOfResultsOnFirstPageWhenSearchingForJava() throws InterruptedException {
        final String googleSearchQuery = "Java";
        final String searchResultsCssSelector = "h3";

        WebElement googleSearchInput;
        List<WebElement> searchResults;

        driver.get("http://www.google.com"); // open http://www.google.com
        WebElement rejectCookies = driver.findElement(By.id("W0wltc"));
        rejectCookies.click();

        googleSearchInput = driver.findElement(By.name("q"));
        googleSearchInput.sendKeys(googleSearchQuery); // enter "Java" in the search field
        googleSearchInput.submit(); // send the form

        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForResults.until(ExpectedConditions.elementToBeClickable(By.cssSelector(searchResultsCssSelector))); // waiting for results visibility

        searchResults = driver.findElements(By.cssSelector(searchResultsCssSelector)); // assigning results to the searchResults list

        System.out.println("Numărul rezultatelor de pe pagină sunt: " + searchResults.size()); // printing the result in the console
    }

//    @After
//    public void tearDown() {
//        driver.quit(); // close browser
//    }

    // my test

    @Test
    public void TestPrintNumberOfResultsOnFirstPageWhenSearchingForGuides() throws InterruptedException {
        final String searchQuery = "Guides";
        final String searchResultsCssSelector = "h3";

        WebElement searchInput;
        List<WebElement> searchResults;
        driver.get("https://keqingmains.com/"); // open https://keqingmains.com/
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement formConsent = driver.findElement(By.xpath("//p[@class='fc-button-label']"));
        waitForResults.until(ExpectedConditions.visibilityOf(formConsent));
        formConsent.click();
        waitForResults.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.bg-dark-purple.text-start.text-white.custom-btn.w-100.mb-1")));
        Thread.sleep(3000);
        WebElement characterGuidesLinkElement = driver.findElement(By.xpath(("//a[@href='#search']")));
        characterGuidesLinkElement.click();
        Thread.sleep(2000);
        waitForResults.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='text-white']")));
        String textElementCautat = driver.findElement(By.xpath("//p[@class='text-white']")).getText();
        assertEquals("Click on filters to change guide type", textElementCautat);
    }
}




