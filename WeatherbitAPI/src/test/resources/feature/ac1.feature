#Author: Nira Sarode

@tag:CurrentWeatherLATLONAPI
Feature: Get current weather data for multiple places in the world based on lat and lon

  @tag1
  Scenario Outline: TC_01_Verify current weather API with valid lat lon data
    Given The user reads config properties
    And The user set up the request for current weather api with <lat> and <lon> fields 
    And The user excute the current weather api
    Then The user validate the current weather api response for <statusCode> and '<countryCode>'
   
   Examples:
   | lat      | lon     | statusCode   | countryCode |
   | 42.5333  | 1.6333  | 200          | AD          |
   | -26.4367 | -65.97  | 200          | AR          |
   | 41.612   | -8.6997 | 200          | PT          |
    
  @tag2
  Scenario Outline: TC_02_Verify current weather API with lat and lon data from csv file
  Given The user reads config properties
  And The user step up the request for current weather api for <CSVRow> number from '<CSVFileName>' csv
  And The user excute the current weather api
  Then The user validate the current weather api response for <statusCode> and country code
  
  Examples:
  |CSVFileName       | CSVRow  | statusCode   |
  | postal_codes.csv | 1       | 200          | 
  | postal_codes.csv | 100     | 200          |
  | postal_codes.csv | 48      | 200          |
  | cities_all.csv   | 10      | 200          | 
  | cities_all.csv   | 2       | 200          |
  | cities_all.csv   | 19      | 200          |

  
  
    



    
