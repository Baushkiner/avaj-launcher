package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import src.aircraft.AircraftFactory;
import src.file.CreateAndWriteFile;

public class Simulator {
	private static int simulationCyclesCount;
	private static BufferedReader buffRead;
	private static WeatherTower tower;

	public static class MyBufferException extends Exception {
		private static final long serialVersionUID = 1L;

		public MyBufferException(String errorMessage) {
			super(errorMessage);
		}
	}	

	private static void initSimulation(File file) throws MyBufferException {
		try {
			buffRead = new BufferedReader(new FileReader(file));
			simulationCyclesCount = Integer.parseInt(buffRead.readLine());
			loadAnyAircrafts();
		} catch(Exception e) {
			if (buffRead == null)
				throw new MyBufferException("Error with Buffer");
			else
				System.out.println("An error occurred. Probably problems with arguments in the file.");
		}
	}

	public static void loadAnyAircrafts() {
		try {
			String[] text2parse;
			String currentLine;
			tower = new WeatherTower();
			while ((currentLine = buffRead.readLine()) != null) {
				text2parse = currentLine.split("\\s+");
				AircraftFactory.newAircraft(text2parse[0], text2parse[1], Integer.parseInt(text2parse[2]),
					Integer.parseInt(text2parse[3]), Integer.parseInt(text2parse[4])).registerTower(tower);
			}
			buffRead.close();
		}
		catch(Exception e) {
			System.out.println("An error occurred. Probably problems with arguments in the file.");
		}
	}

	public static void main(String[] args) {
		try {
			CreateAndWriteFile.createFile();
			initSimulation(new File(args[0]));
			int x = simulationCyclesCount;			
			while (simulationCyclesCount > 0) {
				CreateAndWriteFile.writeFile(String.format("New Simulation: %d%n", x - simulationCyclesCount + 1));
				tower.changeWeather();
				simulationCyclesCount--;
			}
		} catch (Exception e) {
			System.out.println("An error occurred with number of args.");
		}
	}
}