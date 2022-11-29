package com.libraryCT.step_definitions;

import com.libraryCT.utilities.ConfigurationReader;
import com.libraryCT.utilities.DB_Util;
import com.libraryCT.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before
    public void setUp(){
        System.out.println("This is coming from BEFORE");
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Driver.getDriver().get(ConfigurationReader.getProperty("library_url"));
    }


    @After
    public void tearDown(Scenario scenario){
        System.out.println("This is coming from AFTER");
        if(scenario.isFailed()){
           final byte [] screenshots =((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
           scenario.attach(screenshots, "image/png", "screenshot");
        }

        Driver.closeDriver();
    }

    @Before("@db")
    public void setupDB(){
        DB_Util.createConnection();
        System.out.println(" Connecting to Database");
    }
    @After("@db")
    public void destroyDB(){
        DB_Util.destroy();
        System.out.println("Closing connection...");
    }
    /*
    when we run different feature , do we need to change tagName from hooks class? from before and after?
   - if we have @db tag over feature file/scenario
     */

}
