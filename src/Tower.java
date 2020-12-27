package src;

import src.aircraft.Flyable;
import java.util.ArrayList;
import java.util.List;

public class Tower {
	private List<Flyable> objects = new ArrayList<Flyable>();

	public void registerAircraft(Flyable objectFlyable) {
		objects.add(objectFlyable);
	}
	public void unregisterAircraft(Flyable objectFlyable) {
		objects.remove(objectFlyable);
	}
	protected void conditionsChanged() {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).updateConditions();
		}
	}
}
