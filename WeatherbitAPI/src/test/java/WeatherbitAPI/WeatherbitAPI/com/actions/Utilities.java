package WeatherbitAPI.WeatherbitAPI.com.actions;

import java.io.FileReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import au.com.bytecode.opencsv.CSVReader;


public class Utilities extends BaseActions {

	public static Properties configFile;
	CSVReader reader;
	List<String[]> testDetails;
	private String[] headers = null;


	public Utilities(WebDriver driver) {
		super(driver);
	}

	public void readConfig() throws Exception {
		configFile = new Properties();
		String configPath = System.getProperty("user.dir") + "/src/test/resources/config.properties";
		FileReader configFileReader = new FileReader(configPath);
		configFile.load(configFileReader);
	}

	public void readCSV(String csvFile) throws Throwable {
		reader = new CSVReader(new FileReader(csvFile));
		testDetails = reader.readAll();
		if(testDetails != Collections.EMPTY_LIST) {			
			for(String[] h : testDetails ) {
				headers = h;
				break;
			}
		}
	}

	public Map<String, String> getRowDeatilsFromCSVRow(String csvFile, int rowNum) throws Throwable{
		readCSV(csvFile);
		Map<String, String> dataAsKeyValue = new HashMap<String, String>();
		for(int i = 0; i < headers.length ; i++) {
			dataAsKeyValue.put(headers[i].toString(), testDetails.get(rowNum)[i].toString());
		}
		return dataAsKeyValue;
	}
}

