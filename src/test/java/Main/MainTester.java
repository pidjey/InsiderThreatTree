package Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataManipulators.UserTrees;
import dataStructures.*;
import nodeTypes.*;
import logReaders.*;



public class MainTester{
	public static void main(String[] args) {

		UserTrees trees = new UserTrees();
		
		UserReader userReader = new UserReader("/home/pj/Documents/IMD/LP2/FInalProject/planilhas/ldap.csv");
		userReader.loadUsersFromCsv( trees );
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime dateTimeBegin = LocalDateTime.parse("01/01/1970 00:00:00",  formatter);
		LocalDateTime dateTimeEnd = LocalDateTime.parse("01/12/2018 00:00:00",  formatter);
		ActivityReader reader = new ActivityReader("/home/pj/Documents/IMD/LP2/FInalProject/planilhas/device.csv", Activity.type.PENDRIVE );
		reader.loadActivitiesFromCsv(trees, dateTimeBegin, dateTimeEnd);
		
		System.out.println();
		
		
		
		
	}
	
}
