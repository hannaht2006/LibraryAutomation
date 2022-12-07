package com.libraryCT.step_definitions;

import com.libraryCT.pages.BasePage;
import com.libraryCT.pages.BookPage;
import com.libraryCT.pages.DashBoardPage;
import com.libraryCT.pages.LoginPage;
import com.libraryCT.utilities.BrowserUtil;
import com.libraryCT.utilities.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class TS6_VerifyBookCategoryUI_DB extends BasePage{


    BookPage bookPage = new BookPage();
    LoginPage loginPage = new LoginPage();

    List<String> bookUICategory;
    List<String> dbCategory;



    @Given("I login as a {string}")
    public void i_login_as_a(String userType) {
        loginPage.login(userType);
    }

    @When("I navigate to Books page")
    public void iNavigateToBooksPage() {
        books.click();
    }
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {


        bookUICategory =BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        bookUICategory.remove(0);
        System.out.println("bookUICategory = " + bookUICategory);


    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {
       DB_Util.createConnection();
        DB_Util.runQuery("select  name from book_categories");
        dbCategory = DB_Util.getColumnDataAsList(1);
        System.out.println("dbCategory = " + dbCategory);


    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
        Assert.assertEquals(bookUICategory,dbCategory);


    }


}
