import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutomateGoogleEmailSending {
    WebDriver driver;

    @Before
    public void Setup() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
        FirefoxOptions fops = new FirefoxOptions();
        fops.addArguments("--headed");
        driver = new FirefoxDriver(fops);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void AutomatingGoogleEmailSending() throws InterruptedException {
        //1st account
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&hl=en");
        driver.findElement(By.id("identifierId")).sendKeys("elomeloha@gmail.com");
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("Ukilele@9340!");
        List<WebElement> list = driver.findElements(By.cssSelector("button"));
        list.get(1).click();
        Thread.sleep(7000);

        //sending email
        driver.findElement(By.xpath("//div[@class='T-I T-I-KE L3']")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("to")).sendKeys("durdantoha@gmail.com");
        driver.findElement(By.cssSelector("[role='textbox']")).click();
        driver.findElement(By.cssSelector("[role='textbox']")).sendKeys("Hello");
        driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
        Thread.sleep(5000);

        //sign out
        driver.findElement(By.xpath("//img[@class='gb_Ca gbii']")).click();
        Thread.sleep(1000);
        driver.findElement(By.linkText("Sign out")).click();
        Thread.sleep(3000);

        //2nd account login
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin&hl=en");
        driver.findElement(By.id("identifierId")).sendKeys("durdantoha@gmail.com");
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("Ukilele@9340!");
        driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
        Thread.sleep(7000);

        //email assertion
        driver.findElement(By.xpath("/html[1]/body[1]/div[7]/div[3]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[8]/div[1]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/div[1]/div[1]/span[1]")).click();
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath("//div[contains(text(),'Hello')]")).getText();
        Assert.assertTrue(text.contains("Hello"));
    }

    @After
    public void FinishTest() {
        driver.close();
    }
}
