package resuorces;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification req;
	String baseUrl;

	public RequestSpecification requestSpecification() throws Throwable {
		// make satatic to run and store with diffrent data
		if (req == null) {

			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			// building Request SpecBuilder
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).log(LogDetail.BODY)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return req;
		} else {
			return req;
		}
	}

	public String getGlobalValue(String key) throws Throwable {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\resuorces\\global.properties");

		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);

	}
	
	public String getJsonPath(Response addPlaceResp,String key) {
		JsonPath js = new JsonPath(addPlaceResp.asString());
		String value = js.getString(key);
		return value;
	}
}
