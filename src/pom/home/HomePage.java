package pom.home;

import org.openqa.selenium.By;

public class HomePage {

    public By buttonImage(){
        return By.id("inputImage");
    }
    public By buttonEditItem(){
        return By.xpath("//ul[contains(@ui-sortable, 'Options')]/li[1]//button[text()='Edit']");
    }
    public By buttonDeleteItem(String value){
        return By.xpath("//ul[contains(@ui-sortable, 'Options')]/li[1]//p[text()='"+value+"']/../..//button[text()='Delete']");
    }
    public By textareaText(){
        return By.xpath("//textarea[contains(@ng-model, 'editedItem.text')]");
    }
    public By buttonCreateItem(){
        return By.xpath("//button[contains(@ng-click, 'createItem')]");
    }
    public By itemListName(String value){
        return By.xpath("//p[text()='"+value+"']");
    }
    public By buttonConfirmPopup(){
        return By.xpath("//button[text()='Yes, delete it!']");
    }




}
