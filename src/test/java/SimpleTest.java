import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

public class SimpleTest {

    private static final String USERNAME = "v.tarlettskiy@gmail.com";
    private static final String PASSWORD = "232323qQ";

    WebDriver driver;

    @BeforeClass
    public void initialize() {


//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--disable-popup-blocking");
//        options.addArguments("--disable-notifications");
//        WebDriver driver = new ChromeDriver(options);
//        setWebDriver(driver);

      //Configuration.browser = "chrome";
        Configuration.baseUrl = "https://app.qase.io";
        Configuration.startMaximized = true;
        Configuration.reportsFolder = "target/reports";
        Configuration.timeout = 10000;
        Configuration.webdriverLogsEnabled = true;
        open("/login");
        driver = getWebDriver();

//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertEquals(1, 2);
//        softAssert.assertEquals("q", "e");
//        softAssert.assertAll();

    }

    @Test
    public void test() {
        
        $("#inputEmail").shouldBe(enabled).setValue(USERNAME);
        $("#inputPassword").shouldBe(enabled).setValue(PASSWORD);
        $("#btnLogin").shouldBe(enabled).click();
        $(By.id("createButton")).shouldBe(visible);

        $$(".defect-title")
                .shouldHave(size(2))
                .shouldHave(texts("www.sharelane.com_VadimTarletskiy", "example"))
                .get(0).click();
        sleep(2000);
        driver().actions().sendKeys($("[name=title]"), "asdf")
                .build().perform();
        sleep(5000);

    }

    @AfterClass
    public void tearDown() {

    }



}
