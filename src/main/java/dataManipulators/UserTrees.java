package dataManipulators;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataStructures.Node;
import logReaders.UserReader;

import java.util.HashMap;

import nodeTypes.User;


/*Class responsible for creating the trees with format required*/
public class UserTrees {

	private Map<String, Node<Object>> usersMap;
	
	public UserTrees(){
		usersMap = new HashMap<String, Node<Object>>();
	}
	
	/*Create user node and put in a dictionary for better searching*/
	public void createUserNode(User user) {
		Node<Object> userNode = new Node<Object>(user);
		//adds two children
		//position 0 represents all activities
		//position 1 represents activities within chosen time
		userNode.addChild(null, 0);
		userNode.addChild(null, 1);
		String[] domain = user.getDomain().split("[.]");
		usersMap.put( (domain[0].toUpperCase() + "/" + user.getUser_id()), userNode);
	}
	// /home/pj/Documents/IMD/LP2/FInalProject/planilhas/ldap.csv
	/* Get user node from dictionary*/
	public Node<Object> getUserNode(String userIdentifier)
	{
		return usersMap.get(userIdentifier);
	}
	

	
	
	
	
}
