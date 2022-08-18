package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.*;
//import cucumber.api.junit.Cucumber;
//import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class )
@CucumberOptions 
(
	features = {".//Features/Login.feature"},   // 
	glue = "stepDefinitions",
	dryRun = false,
	monochrome = true,
	plugin = { "pretty", "html:target/Login Test Report1.html" }
		)
public class testRun {

}
