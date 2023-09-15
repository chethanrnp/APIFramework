package resuorces;

//enum is a special class in java which have collection of constant and method
public enum APIResources {

	addPlaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resuorce;

	// when u call the "APIResources.vaulue("addPlaceAPI")" the data
	// "/maps/api/place/add/json" will get stored in resource and it can be fetched
	// by "geteRsuorce" method
	APIResources(String resuorce) {
		this.resuorce = resuorce;
	}

	public String getResources() {
		return resuorce;
	}
}
