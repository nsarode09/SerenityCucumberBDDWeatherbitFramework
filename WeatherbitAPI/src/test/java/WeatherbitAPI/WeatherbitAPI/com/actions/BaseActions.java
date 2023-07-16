package WeatherbitAPI.WeatherbitAPI.com.actions;

import org.openqa.selenium.WebDriver;
import net.serenitybdd.core.pages.PageObject;

public class BaseActions extends PageObject {
	
	public static WebDriver driver;

	public BaseActions(WebDriver driver) {
		super(driver);
		BaseActions.driver = driver;
	}

	
}
