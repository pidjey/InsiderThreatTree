package dataManipulators;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataStructures.Node;
import logReaders.UserReader;

import java.util.HashMap;

import nodeTypes.User;


/*Class responsible for creating the trees with format required*/
public class InsiderThreatTrees {
	
	private Map<String, Node<Object>> usersMap;
	
	public InsiderThreatTrees(){
		usersMap = new HashMap<String, Node<Object>>();
	}
	
	/*Create user node and put in a dictionary for better searching*/
	public void createUserNode(User user) {
		Node<Object> userNode = new Node<Object>(user);
		usersMap.put(user.getUser_id(), userNode);
	}
	
	/* Get user node from dictionary*/
	public Node<Object> getUserNode(String user_id)
	{
		return usersMap.get(user_id);
	}
	
	
	/*TODO: Verify if the file makes sense. throw exception otherwise*/
	/* Csv should have 5 colums for this to work. Unknown what happens otherwise */
	public void loadUsersFromCsv(String file)
	{
		UserReader userReader = new UserReader(file);
		String csvLine = userReader.getNextLine();
		
		Pattern p = Pattern.compile("/employee_name|user_id|domain|email|role/i");
		Matcher m = p.matcher( csvLine );
		if(!m.find()) {
			createUserNode( (User) userReader.stringsToClass( userReader.parseCsvLine( csvLine ) ) );
		}
		
		while(csvLine != null)
		{
			createUserNode( (User) userReader.stringsToClass( userReader.parseCsvLine( csvLine ) ) );
			csvLine = userReader.getNextLine();
		}
		
	}
	
	
	
	
}
