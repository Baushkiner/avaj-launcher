package src.aircraft;

import src.Coordinates;
import src.WeatherTower;
import src.file.CreateAndWriteFile;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower thisTower;

	public Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void registerTower(WeatherTower tower) {
		thisTower = tower;
		this.thisTower.registerAircraft(this);
		CreateAndWriteFile.writeFile(String.format("Tower says: Helicopter#%s(%d) registered to weather tower.%n", name, id));
	}

	public void updateConditions() {
		int newLongitude = 0;
		int newLatitude = 0;
		int newHeight = 0;
		String weatherNow = WeatherTower.getWeather(coordinates);
		String text = "";

		if (weatherNow.equals("SUN")) {
			newHeight = coordinates.getHeight() + 2 > 100 ? 100 : coordinates.getHeight() + 2;
			newLongitude = coordinates.getLongitude() + 10;
			newLatitude = coordinates.getLatitude();
			text = "But tomorrow may rain, so I will follow the sun.";
		}
		else if (weatherNow.equals("RAIN")) {
			newHeight = coordinates.getHeight();
			newLongitude = coordinates.getLongitude() + 5;
			newLatitude = coordinates.getLatitude();
			text = "Don't threaten me with love, baby. Let's just go walking in the rain";
		}
		else if (weatherNow.equals("FOG")) {
			newHeight = coordinates.getHeight();
			newLongitude = coordinates.getLongitude() + 1;
			newLatitude = coordinates.getLatitude();
			text = "Out of the fog of mental illness came enlightenment.";
		}
		else if (weatherNow.equals("SNOW")) {
			newHeight = coordinates.getHeight() - 12 <= 0 ? 0 : coordinates.getHeight() - 12;
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude();
			text = "Nothing burns like the cold.";
		}

		if (newHeight > 0) {
			coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
			CreateAndWriteFile.writeFile(String.format("Helicopter#%s(%d): %s%n", name, id, text));
		}
		else {
			CreateAndWriteFile.writeFile(String.format("Tower says: Helicopter#%s(%d) unregistered to weather tower. My Longitude " +
			"and Latitude are %d and %d, respectively.%n", name, id, coordinates.getLongitude(), coordinates.getLatitude()));
			this.thisTower.unregisterAircraft(this);
		}
	}
}
