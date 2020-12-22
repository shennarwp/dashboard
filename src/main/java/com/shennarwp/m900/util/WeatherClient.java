package com.shennarwp.m900.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is used to get weather information from openweathermap.org
 */
public class WeatherClient
{
	/* API key, located at /src/main/resources/META-INF/resources/secret/openweathermap_api */
	private final String APPID;
	/* location should be "Cityname,CountryCode" e.g. "Saarbrücken,DE" */
	private final String location;
	private final RestTemplate restTemplate;
	static Logger logger = LogManager.getLogger(WeatherClient.class);

	/* constructor, initialize the template and load the API key */
	public WeatherClient() {
		this.restTemplate = new RestTemplate();
		this.APPID = getAPI();
		this.location = getLocation();
	}

	/**
	 * this method return the current location for the weather
	 * TODO: get actual location of the browser instead
	 */
	private String getLocation() {
		return "Saarbrücken,DE";
	}

	/* load the API key from the resource folder */
	private String getAPI() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("META-INF/resources/secret/openweathermap_api");
		assert is != null;
		try {
			/* read the first line */
			String API = new BufferedReader(new InputStreamReader(is)).readLine();
			logger.log(Level.INFO, "API key read");
			return API;
		} catch (IOException e) {
			logger.log(Level.ERROR, "Error while reading API key, IOException: " + e);
		}
		return null;
	}

	/**
	 * perform GET to openweathermap API
	 * @return current weather data as JSON
	 */
	private String getWeatherJSON() {
		String url = "https://api.openweathermap.org/data/2.5/weather?q="+ location + "&appid="+ APPID + "&units=metric";
		try {
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			logger.log(Level.INFO, "Successful to get weather data at " + location);
			return response.getBody();

		} catch (RestClientException re) {
			logger.log(Level.ERROR, "Failed to get weather data at " + location + " Error: " + re);

		}
		// TODO: throws exception
		return null;
	}

	/**
	 * extract information from weather data JSON
	 * @return list of weather information:
	 * 		0th element in the list is the temperature
	 * 		1st element is the description
	 * 		2nd element is the image url code (for icon)
	 */
	public List<String> getWeatherDetail() {
		String jsonWeather = getWeatherJSON();
		assert jsonWeather != null;

		JSONObject jsonObj = new JSONObject(jsonWeather);
		String temperature = jsonObj.getJSONObject("main")
									.get("temp")
									.toString();
		String weatherDescription = jsonObj.getJSONArray("weather")
											.getJSONObject(0)
											.get("description")
											.toString();

		String imageCode =  jsonObj.getJSONArray("weather")
									.getJSONObject(0)
									.get("icon")
									.toString();
		String imageURL = "http://openweathermap.org/img/wn/" + imageCode + "@2x.png";

		List<String> weather = new ArrayList<>();
		weather.add(temperature);
		weather.add(weatherDescription);
		weather.add(imageURL);

		return weather;
	}
}
