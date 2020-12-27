package src;

public class WeatherProvider {
	private static WeatherProvider weatherProvider;
	private static String[] weather = {"RAIN", "SUN", "FOG", "SNOW"};

	private WeatherProvider() {}

	public static WeatherProvider getProvider() {
		return WeatherProvider.weatherProvider;
	}
	
	public static String getCurrentWeather(Coordinates coordinates) {
		int value = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight() + 1;
		return weather[value % 4];
	}
}
