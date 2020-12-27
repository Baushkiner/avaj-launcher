package src.aircraft;

import src.Coordinates;
import src.WeatherTower;
import src.file.CreateAndWriteFile;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower thisTower;

	public Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}
	
	public void registerTower(WeatherTower tower) {
		thisTower = tower;
		this.thisTower.registerAircraft(this);
		CreateAndWriteFile.writeFile(String.format("Tower says: Baloon#%s(%d) registered to weather tower.%n", name, id));
	}

	public void updateConditions() {
		int newLongitude = 0;
		int newLatitude = 0;
		int newHeight = 0;
		String weatherNow = WeatherTower.getWeather(coordinates);
		String text = "";

		if (weatherNow.equals("SUN")) {
			newHeight = coordinates.getHeight() + 4 > 100 ? 100 : coordinates.getHeight() + 4;
			newLongitude = coordinates.getLongitude() + 2;
			newLatitude = coordinates.getLatitude();
			text = "Keep your face to the sun and you will never see the shadows.";
		}
		else if (weatherNow.equals("RAIN")) {
			newHeight = coordinates.getHeight() - 5 <= 0 ? 0 : coordinates.getHeight() - 5;
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude();
			text = "Let the rain sing you a lullaby";
		}
		else if (weatherNow.equals("FOG")) {
			newHeight = coordinates.getHeight() - 3 <= 0 ? 0 : coordinates.getHeight() - 3;
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude();
			text = "Fog can cover anything, anything except itself!";
		}
		else if (weatherNow.equals("SNOW")) {
			newHeight = coordinates.getHeight() - 15 <= 0 ? 0 : coordinates.getHeight() - 15;
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude();
			text = "Every winter has its spring.";
		}

		if (newHeight > 0) {
			coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
			CreateAndWriteFile.writeFile(String.format("Helicopter#%s(%d): %s%n", name, id, text));
		}
		else {
			CreateAndWriteFile.writeFile(String.format("Tower says: Baloon#%s(%d) unregistered to weather tower. My Longitude " +
			"and Latitude are %d and %d, respectively.%n", name, id, coordinates.getLongitude(), coordinates.getLatitude()));
			this.thisTower.unregisterAircraft(this);
		}
	}
}
