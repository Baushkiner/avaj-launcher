package src.aircraft;

import src.Coordinates;
import src.WeatherTower;
import src.file.CreateAndWriteFile;

public class JetPlane extends Aircraft implements Flyable {
	private WeatherTower thisTower;

	public JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}
	
	public void registerTower(WeatherTower tower) {
		thisTower = tower;
		this.thisTower.registerAircraft(this);
		CreateAndWriteFile.writeFile(String.format("Tower says: JetPlane#%s(%d) registered to weather tower.%n", name, id));
	}

	public void updateConditions() {
		int newLongitude = 0;
		int newLatitude = 0;
		int newHeight = 0;
		String weatherNow = WeatherTower.getWeather(coordinates);
		String text = "";

		if (weatherNow.equals("SUN")) {
			newHeight = coordinates.getHeight() + 2 > 100 ? 100 : coordinates.getHeight() + 2;
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude() + 10;
			text = "If you're not barefoot, then you're overdressed.";
		}
		else if (weatherNow.equals("RAIN")) {
			newHeight = coordinates.getHeight();
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude() + 5;
			text = "I've always found the rain very calming";
		}
		else if (weatherNow.equals("FOG")) {
			newHeight = coordinates.getHeight();
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude() + 1;
			text = "Beauty can be found in a fog of uncertainty.";
		}
		else if (weatherNow.equals("SNOW")) {
			newHeight = coordinates.getHeight() - 7 <= 0 ? 0 : coordinates.getHeight() - 7;
			newLongitude = coordinates.getLongitude();
			newLatitude = coordinates.getLatitude();
			text = "Winter forms our character and brings out our best.";
		}

		if (newHeight > 0) {
			coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
			CreateAndWriteFile.writeFile(String.format("Helicopter#%s(%d): %s%n", name, id, text));
		}
		else {
			CreateAndWriteFile.writeFile(String.format("Tower says: JetPlane#%s(%d) unregistered to weather tower. My Longitude " +
			"and Latitude are %d and %d, respectively.%n", name, id, coordinates.getLongitude(), coordinates.getLatitude()));
			this.thisTower.unregisterAircraft(this);
		}
	}
 }
