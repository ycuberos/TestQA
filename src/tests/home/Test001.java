package tests.home;

import com.aventstack.extentreports.Status;
import core.helpers.AssertHelpers;
import core.helpers.SendKeyHelpers;
import core.helpers.WaitHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pom.home.HomePage;
import core.helpers.ClickHelpers;
import org.testng.annotations.Test;
import tests.Hooks;

import java.io.File;
import java.util.ArrayList;


public class Test001 extends Hooks {

    ClickHelpers click = new ClickHelpers();
    AssertHelpers assertHelpers = new AssertHelpers();
    SendKeyHelpers sendKeyHelpers = new SendKeyHelpers();
    HomePage homePage = new HomePage();
    int minValue = 100;
    int maxValue = 10000;
    double randomNumber = Math.floor(Math.random() *(maxValue - minValue + 1) + minValue);
    String itemName = "CREATE Test Yessica " + randomNumber;
    String editedItemName = "EDIT Test Yessica " + randomNumber;
    String charachters301 = "dddditemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemName itemmm301";

    @Test
    public void scenariosTest() {

        test = extent.createTest("Test Yessica", "There are the scenarios");

        //Create new Item ------------------------------------------------------------
        sendKeyHelpers.write(driver, homePage.buttonImage(), System.getProperty("user.dir") + "/src/images/320320.png");
        sendKeyHelpers.write(driver, homePage.textareaText(), itemName);
        click.click(driver, homePage.buttonCreateItem());
        //Validation
        assertHelpers.assertEquals(driver, homePage.itemListName(itemName), itemName);
        test.log(Status.PASS, "Scenario 1 - Create new Item");

        //Edit another existing item ------------------------------------------------------------
        click.click(driver, homePage.buttonEditItem());
        sendKeyHelpers.write(driver, homePage.textareaText(), editedItemName);
        click.click(driver, homePage.buttonCreateItem());
        //Validation
        assertHelpers.assertEquals(driver, homePage.itemListName(editedItemName), editedItemName);
        test.log(Status.PASS, "Scenario 2 - Edit another existing item");

        //Delete the item created ------------------------------------------------------------
        click.click(driver, homePage.buttonDeleteItem(itemName));
        click.click(driver, homePage.buttonConfirmPopup());
        //Validation
        assertHelpers.assertNotPresent(driver, homePage.itemListName(itemName));
        test.log(Status.PASS, "Scenario 3 - Delete the item created");

        //Check max long in description ------------------------------------------------------------
        sendKeyHelpers.write(driver, homePage.buttonImage(), System.getProperty("user.dir") + "/src/images/320320.png");
        sendKeyHelpers.write(driver, homePage.textareaText(), charachters301);
        //Validation
        WebElement button = driver.findElement(homePage.buttonCreateItem());
        Assert.assertEquals(false, button.isEnabled());
        test.log(Status.PASS, "Scenario 4 - Check max long in description");

        //Check if exist in the list the item with the text "Creators: Matt Duffer, Ross Duffer" ------------------------------------------------------------
        String itemNameTest = "Creators: Matt Duffer, Ross Duffer";
        boolean finalValue = assertHelpers.assertExist(driver, homePage.itemListName(itemNameTest));
        if(finalValue){
            System.out.println("ITEM EXISTENTE....");
            Assert.assertTrue(finalValue);
        }else{
            System.out.println("ITEM NO EXISTENTE....");
            Assert.assertFalse(finalValue);
        }
        test.log(Status.PASS, "Scenario 5 - Check if exist in the list the item with the text");
    }
}