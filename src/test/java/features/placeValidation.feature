Feature: Validating Place API's

  @AddPlace
  Scenario Outline: 
    : Verify if place is being Succesfully add using AddPlaceAPI

    Given Add Place Payload with "<name>" "<address>" "<language>"
    When user call "addPlaceAPI" with "post" http request
    Then The API call is success with status code 200
    And "status" in respose body is "OK"
    And "scope" in respose body is "APP"
    And verify "place_id" created maps to "<name>" using "getPlaceAPI"

    Examples: 
      | name    | address  | language |
      | MMHouse | MM Hills | Kan      |

  #| MHouse  | DSthala  | Kannada  |
  @DeletePlace
  Scenario: Verify if delete Place functionality is working
    Given DeletePlace Payload
    When user call "deletePlaceAPI" with "delete" http request
    Then The API call is success with status code 200
    And "status" in respose body is "OK"
