package com.libraryCT.step_definitions;

import com.libraryCT.pages.BookPage;
import com.libraryCT.pages.DashBoardPage;
import com.libraryCT.pages.LoginPage;
import com.libraryCT.utilities.BrowserUtil;
import com.libraryCT.utilities.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.Map;

public class Ts6_125 {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();
    BookPage bookPage=new BookPage();


    @Given("I login as a librarian")
    public void i_login_as_a_librarian() {
        loginPage.login("librarian");




    }
    @Given("I navigate to {string} page")
    public void i_navigate_to_page(String moduleName) {
        dashBoardPage.navigateModule(moduleName);



    }
    @When("I open book {string}")
    public void i_open_book(String bookName) {
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();


    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database(String bookName) {
        BrowserUtil.waitFor(4);
        System.out.println("-----Assertion step-----");
        System.out.println(bookPage.bookName.getText());
        System.out.println("getAttribute(value)--> "+bookPage.bookName.getAttribute("value"));
        System.out.println("bookPage.bookName.getAttribute(\"innerHTMl\") = " + bookPage.bookName.getAttribute("outerHTML"));



        // get data from UI
        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN=bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");

        // get data from Database
        String query="select name,isbn,author,description,year from books\n" +
                "where name='"+bookName+"'";

        DB_Util.runQuery(query);
        //store information
        Map<String, String> bookInfo = DB_Util.getRowMap(1);
        System.out.println("---- DATA FROM DATABASE ---- ");
        String expectedBookName = bookInfo.get("name");
        System.out.println(expectedBookName);
        String expectedAuthorName = bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");
        String expectedDesc = bookInfo.get("description");


        // compare
        Assert.assertEquals(expectedBookName,actualBookName);
        Assert.assertEquals(expectedAuthorName,actualAuthorName);
        Assert.assertEquals(expectedISBN,actualISBN);
        Assert.assertEquals(expectedYear,actualYear);
        Assert.assertEquals(expectedDesc,actualDesc);


    }
}
