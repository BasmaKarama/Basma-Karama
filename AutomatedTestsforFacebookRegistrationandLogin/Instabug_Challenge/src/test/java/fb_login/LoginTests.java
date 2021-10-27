package fb_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import pages.ReadExcelFile;

public class LoginTests {
        private WebDriver driver;
        @Test(dataProvider="testdata")
        public void loginTest(String email, String password) throws InterruptedException {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
            driver = new ChromeDriver();
            driver.get("https://www.facebook.com/");
            driver.manage().window().maximize();
            driver.findElement(By.name("email")).sendKeys(email);
            driver.findElement(By.name("pass")).sendKeys(password);
            driver.findElement(By.name("login")).click();
            Thread.sleep(5000);
            System.out.println("Register successful");
        }
        @AfterMethod
        void ProgramTermination() {
            driver.quit();
        }
        @DataProvider(name="testdata")
        public Object[][] testDataExample(){
            ReadExcelFile configuration = new ReadExcelFile("resources\\Data\\users_passwords.xlsx");
            int rows = configuration.getRowCount(0);
            Object[][]signin_credentials = new Object[rows][2];

            for(int i=0;i<rows;i++)
            {
                signin_credentials[i][0] = configuration.getData(0, i, 0);
                signin_credentials[i][1] = configuration.getData(0, i, 1);
            }
            return signin_credentials;
        }
    }
