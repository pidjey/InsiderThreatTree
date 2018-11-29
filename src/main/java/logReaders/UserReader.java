package logReaders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataManipulators.UserTrees;
import nodeTypes.User;
import logReaders.CsvReader;


public class UserReader extends CsvReader{

	public UserReader(String path) {
		super(path);
	}

	public Object stringsToClass(String[] data) {
		
		User novoUsuario = new User();
		novoUsuario.setEmployee_name(data[0]);
		novoUsuario.setUser_id(data[1]);
		novoUsuario.setDomain(data[2]);
		novoUsuario.setEmail(data[3]);
		novoUsuario.setRole(data[4]);
		
		return novoUsuario;
	}

	/*TODO: Verify if the file makes sense. throw exception otherwise*/
	/*Csv should have 5 colums for this to work. Unknown what happens otherwise*/
	public void loadUsersFromCsv(UserTrees tree)
	{
		String csvLine = getNextLine();
		
		/*Verifies if the line is a header. ignores it if that's the case*/
		Pattern p = Pattern.compile("/employee_name|user_id|domain|email|role/i");
		Matcher m = p.matcher( csvLine );
		if(!m.find()) {
			tree.createUserNode( (User) stringsToClass( parseCsvLine( csvLine ) ) );
		}
	
		csvLine = getNextLine();
		while(csvLine != null)
		{
			tree.createUserNode( (User) stringsToClass( parseCsvLine( csvLine ) ) );
			csvLine = getNextLine();
		}
		
	}
}
