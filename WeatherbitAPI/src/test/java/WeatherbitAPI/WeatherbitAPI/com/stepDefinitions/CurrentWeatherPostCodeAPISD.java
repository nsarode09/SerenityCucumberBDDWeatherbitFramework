package WeatherbitAPI.WeatherbitAPI.com.stepDefinitions;

import WeatherbitAPI.WeatherbitAPI.com.steps.CurrentWeatherPostCodeAPIStep;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;

public class CurrentWeatherPostCodeAPISD {

	@Steps
	CurrentWeatherPostCodeAPIStep cwPostCodeAPIStep;
		
	
	@And("^The user set up the request for current weather api with '(.*)' fields$")
	public void setUpCWPostCodeAPI(String postCode) {
		cwPostCodeAPIStep.setUpCWPostCodeAPI(postCode);
	}
	
	@And("^The user excute the current weather api with postalCode$")
	public void executeCWPostalCodeAPI() {
		cwPostCodeAPIStep.executeCWPostalCodeAPI();
	}
	
	@Then("^The user validate the current weather api with postalCode param response for (.*)$")
	public void validateCurrentWeatherPostCodeAPIResponse(Integer statusCode) {
		cwPostCodeAPIStep.validateSuccessStatusCode(statusCode);
	}
	
	@And("^The user step up the request for current weather api for (.*) number from '(.*)' csv with postal code param$")
	public void setupCWPostCodeAPIWithCSV(int rowNum, String csvName) throws Throwable {
		cwPostCodeAPIStep.getCSVFile(csvName);
		cwPostCodeAPIStep.setupCWAPIForPostCodeFromCSV(csvName,rowNum);
	}
	
	@Then("^The user validate the current weather with postalCode param API response for countryCode and (.*)$")
		public void validateCWAPIPostalCodeResponseStatusAndCountryCode(Integer statusCode) {
			cwPostCodeAPIStep.validateSuccessStatusCode(statusCode);
			cwPostCodeAPIStep.validateCWAPIResponseBasedOnPostalCodeCSV();
		}	
}
