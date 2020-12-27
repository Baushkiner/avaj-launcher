package src.file;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreateAndWriteFile {
	public static void createFile() {
		try {
			PrintWriter myWriter = new PrintWriter("simulation.txt");
			myWriter.print("");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
		}
	}

	public static void writeFile(String addToFile) {
		try {
		FileWriter myWriter = new FileWriter("simulation.txt", true);
		myWriter.write(addToFile);
		myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
		}
	}
}
