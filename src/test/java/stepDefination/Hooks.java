package stepDefination;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		// to run only when placeID is null only
		// if it is static call only with class name
		MainSteps m = new MainSteps();
		if (MainSteps.placId == null) {

			m.add_place_payload_with("MMHouse", "MM Hills", "Kan");
			m.user_call_with_http_request("addPlaceAPI", "addPlaceAPI");
			m.verify_created_maps_to_using("place_id", "MMHouse", "getPlaceAPI");
		}
	}
}
