package WeatherbitAPI.WeatherbitAPI.com.stepDefinitions;

import WeatherbitAPI.WeatherbitAPI.com.steps.CurrentWeatheLatLonAPIStep;
import io.cucumber.java.en.*;
import net.thucydides.core.annotations.Steps;

public class CurrentWeatherLatLonAPISD {

	@Steps
	CurrentWeatheLatLonAPIStep cwLatLonAPIStep;
	
	@Given("^The user reads config properties$")
	public void readConfigProperties() throws Throwable {
		cwLatLonAPIStep.readConfigProp();
	}

	@And("^The user set up the request for current weather api with (.*) and (.*) fields$")
	public void setupCurrentWeatherAPI(Double lat, Double lon) {
		cwLatLonAPIStep.setlatlonCurrentWeatherAPIRequest(lat,lon);
	}

	@And("^The user excute the current weather api$")
	public void executeCurrentWeatherAPI() {
		cwLatLonAPIStep.executeCurrentWeatherAPI();
	}

	@Then("^The user validate the current weather api response for (.*) and '(.*)'$")
	public void validateCurrentWeatherAPIResponse(Integer statusCode, String countryCode) {
		cwLatLonAPIStep.validateSuccessStatusCode(statusCode);
		cwLatLonAPIStep.vaidateCurrentWeatherAPIResponse(countryCode);
	}
	
	@And("^The user step up the request for current weather api for (.*) number from '(.*)' csv$")
	public void setupCurrentWeatherAPIForLatLonPassedFromCSV(int rowNum, String csvName) throws Throwable {
		cwLatLonAPIStep.getCSVFile(csvName);
		cwLatLonAPIStep.setupCurrentWeatherAPIForLatLonPassedFromCSV(csvName, rowNum);
	}
	
	@Then("^The user validate the current weather api response for (.*) and country code$")
	public void validateCurrentWeatherAPIResponseBasedOnPostalCodeCSV(Integer statusCode) {
		cwLatLonAPIStep.validateSuccessStatusCode(statusCode);
		cwLatLonAPIStep.validateCurrentWeatherAPIResponseBasedOnPostalCodeCSV();
	}
	
	@Then("^The user set, execute and validate request for current weather api for all rows from '(.*)' csv$")
	public void setExecuteValidateCWAPIFromPostalCodeCSV(String csvFileName) throws Throwable {
		cwLatLonAPIStep.getCSVFile(csvFileName);
		cwLatLonAPIStep.runAllDataFromPostalCodeCSV();
	}
	
	
}