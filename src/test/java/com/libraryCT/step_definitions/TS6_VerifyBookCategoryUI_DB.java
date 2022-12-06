package com.libraryCT.step_definitions;

import com.libraryCT.pages.LoginPage;
import com.libraryCT.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TS6_VerifyBookCategoryUI_DB {

    LoginPage loginPage = new LoginPage();
    @Given("I login as a librarian")
    public void i_login_as_a_librarian() {
        loginPage.login("userType");

    }
    @When("I navigate to {string} page")
    public void i_navigate_to_page(String string) {


    }
    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {


    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {


    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {


    }
}
