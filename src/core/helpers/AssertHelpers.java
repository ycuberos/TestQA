package core.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class AssertHelpers {

    WaitHelpers wait = new WaitHelpers();

    public void assertEquals(WebDriver driver, By element, String expectedText){
        try{
            wait.waitClickeable(driver, element);
            String actualText = driver.findElement(element).getText();
            Assert.assertEquals(actualText, expectedText);
        }catch(Exception e){
            System.out.println("AssertHelpers " + e);

        }
    }
    public boolean assertNotPresent(WebDriver driver, By element){
        try {
            driver.findElement(element);
            System.out.println("Element Present");
            return false;

        } catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return true;
        }
    }
    public boolean assertExist(WebDriver driver, By element){
        boolean flag = false;
        try {
            flag = driver.findElements(element).size() != 0;
            return flag;
        } catch (NoSuchElementException e) {
            System.out.println("Element absent");
            return flag;
        }
    }
}
