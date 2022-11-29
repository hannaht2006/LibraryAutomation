package com.libraryCT.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "@target/rerun.txt",
        glue = "com/libraryCT/step_definitions"

)
public class FailedTestRunner {
}
//this runner class will be running only failed scenarios