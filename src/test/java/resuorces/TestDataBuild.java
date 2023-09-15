package resuorces;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String addr, String lang) {
		// Creating the body using the pojo class
		Location location = new Location(-38.383494, 33.427362);
		ArrayList<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		// Creating the object of AddPlace Pojo class
		AddPlace addPalce = new AddPlace(location, 50, name, "(+91) 983 893 3937", addr, types, "http://google.com",
				lang);

		return addPalce;

	}

	public String deletePlacePayload(String placeID) {
		return "{\r\n" + "    \"place_id\":\"" + placeID + "\"\r\n" + "}\r\n" + "";
	}
}
