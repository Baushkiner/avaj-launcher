package src.aircraft;

import src.WeatherTower;

public interface Flyable {
	public void updateConditions();
	public void registerTower(WeatherTower tower);
}
