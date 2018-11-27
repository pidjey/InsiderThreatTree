package logReaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public abstract class CsvReader {
	
	private File file;
	BufferedReader reader;
	public CsvReader(String fileName) {
		
		try {
			file = new File(fileName);
			reader = new BufferedReader(new FileReader(file));
		}catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	
	public String getNextLine()
	{
		String str = null;
		try {
			 str = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public String[] parseCsvLine(String line) {
		/*Maybe can get performance down*/
		return line.split(",");
	}
	
	public abstract Object stringsToClass(String[] data);
	
	
}
