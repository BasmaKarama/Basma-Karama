package fb_registration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ReadExcelFile;

public class RegistrationTests {

    private WebDriver driver;
    @Test(dataProvider="testdata")
    public void registrationTest(String firstname, String lastname, String email, String password, String day, String month, String year) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/signup");
        driver.manage().window().maximize();

        driver.findElement(By.name("firstname")).sendKeys(firstname);
        driver.findElement(By.name("lastname")).sendKeys(lastname);
        driver.findElement(By.name("reg_email__")).sendKeys(email);
        Thread.sleep(2000);
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys(email);
        driver.findElement(By.name("reg_passwd__")).sendKeys(password);
        Select s = new Select(driver.findElement(By.id("day")));
        Thread.sleep(1000);
        s.selectByValue(day);
        Select s1 = new Select(driver.findElement(By.id("month")));
        Thread.sleep(1000);
        s1.selectByValue(month);
        Select s2 = new Select(driver.findElement(By.id("year")));
        Thread.sleep(1000);
        s2.selectByValue(year);
        driver.findElement(By.xpath("//input[@value='1']")).click();
        driver.findElement(By.name("websubmit")).click();
    }
    @AfterMethod
    void ProgramTermination() {
        driver.quit();
    }
    @DataProvider(name="testdata")
    public Object[][] testData(){
        ReadExcelFile configuration = new ReadExcelFile("resources\\Data\\registration_data.xlsx");
        int rows = configuration.getRowCount(0);
        Object[][]signin_credentials = new Object[rows][7];

        for(int i=0;i<rows;i++)
        {
            signin_credentials[i][0] = configuration.getData(0, i, 0);
            signin_credentials[i][1] = configuration.getData(0, i, 1);
            signin_credentials[i][2] = configuration.getData(0, i, 2);
            signin_credentials[i][3] = configuration.getData(0, i, 3);
            signin_credentials[i][4] = configuration.getData(0, i, 4);
            signin_credentials[i][5] = configuration.getData(0, i, 5);
            signin_credentials[i][6] = configuration.getData(0, i, 6);

        }
        return signin_credentials;
    }
}

