package WeatherbitAPI.WeatherbitAPI.com.steps;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import WeatherbitAPI.WeatherbitAPI.com.actions.Utilities;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class CurrentWeatheLatLonAPIStep{

	private RequestSpecification requestSpecs;
	private Response response;
	private String csvFile = "";
	private Map<String, String> rowDetails;
	Utilities utl;
	
	@Step
	public void readConfigProp() throws Throwable {
		utl.readConfig();
	}
 
	@Step
	public void executeCurrentWeatherAPI() {
		response = SerenityRest.given().spec(requestSpecs).get(Utilities.configFile.getProperty("Base_url"));
	}

	@Step
	public void setlatlonCurrentWeatherAPIRequest(Object lat, Object lon) {
		RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		requestSpecBuilder.addHeader("Accept", "application/json");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("lat", lat);
		param.put("lon", lon);
		param.put("key", Utilities.configFile.getProperty("API_Key"));
		Map<String,String> newParam =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			newParam.put(entry.getKey(),  (String) entry.getValue().toString());
		}
		requestSpecBuilder.addFormParams(newParam);
		requestSpecs = requestSpecBuilder.build();
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
	public void vaidateCurrentWeatherAPIResponse(String countryCode) {
		if(response != null) {	
			String actualCountryCode = new JsonPath(response.asString()).get("data.country_code").toString().substring(1,3);
			if(actualCountryCode.equalsIgnoreCase(countryCode)) {
				Assert.assertTrue("Country Code from API response is verified with expected", true);
			} else {Assert.assertTrue("Country Code from API response is not verified with expected", false);}

			Serenity.recordReportData().withTitle("Validated Current Weather API response country code based on lat and lon combination")
			.andContents("COUNTRY_CODE = " + actualCountryCode);

			Serenity.recordReportData().withTitle("Get complete response of Current Weather API")
			.andContents("COMPLETE_RESPONSE = " + new JsonPath(response.asString()).get("data").toString());

		}else {
			Assert.assertTrue("Current Weather API Response is null", false);
		}
	}

	@Step
	public void setupCurrentWeatherAPIForLatLonPassedFromCSV(String csvName,int rowNum) throws Throwable {
		rowDetails = utl.getRowDeatilsFromCSVRow(csvFile,rowNum);
		switch(csvName.toLowerCase()) {
		case "postal_codes.csv": {
			if(rowDetails.containsKey("approximate_centroid_lat") && rowDetails.containsKey("approximate_centroid_lon")) {
				setlatlonCurrentWeatherAPIRequest(rowDetails.get("approximate_centroid_lat"),rowDetails.get("approximate_centroid_lon"));
			}else {
				Assert.assertTrue("lat and lon values are not present in postalCode CSV", false);
			}
			break;
		}
		case "cities_all.csv":{
			if(rowDetails.containsKey("lat") && rowDetails.containsKey("lon")) {
				setlatlonCurrentWeatherAPIRequest(rowDetails.get("lat"),rowDetails.get("lon"));
			}else {
				Assert.assertTrue("lat and lon values are not present in cities_all CSV", false);
			}
			break;
		}
		default: 
			System.out.println("Data not passed through CSV");
			break;
		}
			
	}
	
	@Step
	public void validateCurrentWeatherAPIResponseBasedOnPostalCodeCSV() {
		if(response != null) {
			String actualCountryCode = new JsonPath(response.asString()).get("data.country_code").toString().substring(1,3);
			if(actualCountryCode.equalsIgnoreCase(rowDetails.get("country_code"))) {
				Assert.assertTrue("Country Code from API response is verified with expected", true);
			} else {Assert.assertTrue("Country Code from API response is not verified with expected", false);}

			Serenity.recordReportData().withTitle("Validated Current Weather API response country code based on lat and lon combination")
			.andContents("COUNTRY_CODE = " + actualCountryCode);

			Serenity.recordReportData().withTitle("Get complete response of Current Weather API")
			.andContents("COMPLETE_RESPONSE = " + new JsonPath(response.asString()).get("data").toString());
		}
	}
	
	@Step
	public void getCSVFile(String csvName) {
		csvFile = csvName;
		csvFile = System.getProperty("user.dir") + "/src/test/resources/TestFolder/" + csvFile;
	}
	
	
}

