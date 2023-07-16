package WeatherbitAPI.WeatherbitAPI.com.steps;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import WeatherbitAPI.WeatherbitAPI.com.actions.Utilities;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CurrentWeatherPostCodeAPIStep{
	
	private RequestSpecification requestSpecs;
	private Response response;
	private String csvFile = "";
	private Map<String, String> rowDetails;
	Utilities utl;

	@Step
	public void setUpCWPostCodeAPI(String postCode) {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.addHeader("Accept", "application/json");
		Map<String,String> param = new HashMap<String,String>();
		param.put("postal_code", postCode);
		param.put("key", Utilities.configFile.getProperty("API_Key"));
		requestSpecBuilder.addFormParams(param);
		requestSpecs = requestSpecBuilder.build();
	}
	
	@Step
	public void executeCWPostalCodeAPI() {
		response = SerenityRest.given().spec(requestSpecs).get(Utilities.configFile.getProperty("Base_url"));
	}
	
	@Step
	public void validateSuccessStatusCode(Integer statusCode) {
		if(response != null) {
			//validate statusCode
			Integer actualStatusCode = response.then().extract().statusCode();
			System.out.print("Actual reponse status code is " + actualStatusCode);
			if(actualStatusCode != statusCode) {
				Assert.assertTrue("Current Weather API Response is 200", true);
			}else { 
				Assert.assertTrue("Current Weather API Response is not equal to 200", false);
			}
			
			Serenity.recordReportData().withTitle("Validated Current Weather API response status code").andContents("STATU_CODE = " + actualStatusCode);
			
		}
	}
	
	@Step
	public void getCSVFile(String csvName) {
		csvFile = csvName;
		csvFile = System.getProperty("user.dir") + "/src/test/resources/TestFolder/" + csvFile;
	}
	
	@Step
	public void setupCWAPIForPostCodeFromCSV(String csvName,int rowNum) throws Throwable {
		rowDetails = utl.getRowDeatilsFromCSVRow(csvFile,rowNum);
		switch(csvName.toLowerCase()) {
		case "postal_codes.csv": {
			if(rowDetails.containsKey("postal_code")) {
				setUpCWPostCodeAPI(rowDetails.get("postal_code"));
			}else {
				Assert.assertTrue("lat and lon values are not present in postalCode CSV", false);
			}
			break;
		}
		default: 
			System.out.println("Data not passed through CSV");
			break;
		}
			
	}
	
	@Step
	public void validateCWAPIResponseBasedOnPostalCodeCSV() {
		if(response != null) {
			String actualCountryCode = new JsonPath(response.asString()).get("data.country_code").toString().substring(1,3);
			if(actualCountryCode.equalsIgnoreCase(rowDetails.get("country_code"))) {
				Assert.assertTrue("Country Code from API response is verified with expected", true);
			} else {Assert.assertTrue("Country Code from API response is not verified with expected", false);}

			Serenity.recordReportData().withTitle("Validated Current Weather API response country code based on postalCode")
			.andContents("COUNTRY_CODE = " + actualCountryCode);

			Serenity.recordReportData().withTitle("Get complete response of Current Weather API")
			.andContents("COMPLETE_RESPONSE = " + new JsonPath(response.asString()).get("data").toString());
		}
	}
		
}
