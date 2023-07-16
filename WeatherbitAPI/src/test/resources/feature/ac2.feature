#Author: Nira Sarode

Feature: Get current weather data for multiple places in the world based on postalCode

  @tag3
  Scenario Outline: TC_01_Verify current weather API with valid postalCode
    Given The user reads config properties
    And The user set up the request for current weather api with '<postalCode>' fields 
    And The user excute the current weather api with postalCode
    Then The user validate the current weather api with postalCode param response for <statusCode>
   
   Examples:
   | postalCode  | statusCode   | countryCode |
   | AD200       | 200          | AD          |
   | 4126        | 200          | AR          |
   | 2925-726    | 200          | PT          |
   | 353124"     | 200          | RU          |
   
    
  @tag4
  Scenario Outline: TC_02_Verify current weather API with postalCode data from csv file
  Given The user reads config properties
  And The user step up the request for current weather api for <CSVRow> number from '<CSVFileName>' csv with postal code param
  And The user excute the current weather api with postalCode
  Then The user validate the current weather with postalCode param API response for countryCode and <statusCode>
  
  Examples:
  |CSVFileName       | CSVRow  | statusCode   |
  | postal_codes.csv | 17      | 200          | 
  | postal_codes.csv | 109     | 200          |
  | postal_codes.csv | 66      | 200          |