import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    WebDriver driver;
    final String URL = "https://irr.ru/";

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void titleTest(){
        driver.get(URL);
        Assert.assertEquals(driver.getTitle(), "Из рук в руки - доска частных бесплатных объявлений в Москве");
    }

    @Test
    public void InputNameOfProductTest() throws InterruptedException {
        driver.get(URL);
        // ищем поле вода
        var nameOfProduct = driver
                .findElement(By.xpath("//input[@data-qa='search__input']"));
        Assert.assertNotNull(nameOfProduct);
        // записываем значение в поле ввода
        nameOfProduct.sendKeys("мебель");
        Thread.sleep(1000);
    }

    @Test
    public void ChooseCategoryTest() throws InterruptedException {
        driver.get(URL);
        var selectize = driver
                .findElement(By.xpath("//div[@class='selectize-control single plugin-remove_button']"));
        Assert.assertNotNull(selectize);
        selectize.click();
        var dataSelectables = selectize
                .findElements(By.xpath("//div[@data-selectable]"));
        Assert.assertNotNull(dataSelectables.get(5));
        dataSelectables.get(5).click();
        Thread.sleep(1000);
    }



    @After
    public void quit(){
        driver.close();
    }
}
