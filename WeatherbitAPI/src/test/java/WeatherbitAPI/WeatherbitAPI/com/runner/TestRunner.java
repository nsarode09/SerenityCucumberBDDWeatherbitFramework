package WeatherbitAPI.WeatherbitAPI.com.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = "src\\test\\resources\\feature",
		glue = "WeatherbitAPI.WeatherbitAPI.com.stepDefinitions",
		tags = "@tag1",
		plugin = {"pretty"}
		)

public class TestRunner {
	
}

