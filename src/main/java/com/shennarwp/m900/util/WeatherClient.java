package com.shennarwp.m900.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WeatherClient
{
	private final RestTemplate restTemplate;
	private final String APPID;

	public WeatherClient() {
		this.restTemplate = new RestTemplate();
		APPID = getAPI();
	}

	public String getAPI() {
		InputStream is = getClass().getClassLoader().getResourceAsStream("META-INF/resources/secret/openweathermap_api");
		assert is != null;
		try {
			return new BufferedReader(new InputStreamReader(is))
					.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getWeatherJSON() {
		String url = "https://api.openweathermap.org/data/2.5/weather?q=Saarbrücken,DE&appid="+ APPID + "&units=metric";
		return this.restTemplate.getForObject(url, String.class);
	}

	public List<String> getWeatherDetail() throws JSONException {
		JSONObject jsonObj = new JSONObject(getWeatherJSON());
		String temperature = jsonObj.getJSONObject("main")
									.get("temp")
									.toString();
		String weatherDescription = jsonObj.getJSONArray("weather")
											.getJSONObject(0)
											.get("description")
											.toString();
		List<String> weather = new ArrayList<>();

		String imageCode =  jsonObj.getJSONArray("weather")
									.getJSONObject(0)
									.get("icon")
									.toString();
		String imageURL = "http://openweathermap.org/img/wn/" + imageCode + "@2x.png";

		weather.add(temperature);
		weather.add(weatherDescription);
		weather.add(imageURL);

		return weather;
	}
}
