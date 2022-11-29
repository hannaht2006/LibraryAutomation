package com.libraryCT.step_definitions;

import com.libraryCT.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class Ts6_122 {
    List<String> allIdFromUsers;
    List<String> allUniqueIdFromUsers;
    List<String> allColumName;

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
    //get all IDs from users
        DB_Util.runQuery("select ID from users");
         allIdFromUsers = DB_Util.getColumnDataAsList(1);

   //get all unique IDs from users
        DB_Util.runQuery("select distinct ID from users");
        allUniqueIdFromUsers = DB_Util.getColumnDataAsList(1);

    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
//compare
        Assert.assertEquals(allUniqueIdFromUsers,allIdFromUsers);
    }


    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

     DB_Util.runQuery("select * from users\n" +
        "where row_count()= 1");
        allColumName=DB_Util.getAllColumnNamesAsList();
    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedCategoryList) {
   Assert.assertEquals(expectedCategoryList, allColumName);

    }
}
