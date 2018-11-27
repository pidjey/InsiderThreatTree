package Main;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataManipulators.InsiderThreatTrees;
import dataStructures.*;
import nodeTypes.*;
import logReaders.*;



public class MainTester{
	public static void main(String[] args) {

		
		InsiderThreatTrees a = new InsiderThreatTrees();
		a.loadUsersFromCsv("/home/pj/Documents/IMD/LP2/FInalProject/planilhas/ldap.csv");
		System.out.println( ((User)a.getUserNode("CDF0585").getData()).getEmail() ); 
			
		
		
		
	}
	
}
