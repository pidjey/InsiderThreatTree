package logReaders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataManipulators.UserTrees;
import dataStructures.Node;
import nodeTypes.Activity;
import nodeTypes.User;

public class ActivityReader extends CsvReader{
	
	private Activity.type activityType;

	public ActivityReader(String fileName, Activity.type activityType) {
		super(fileName);
		this.activityType = activityType;
		
	}
	
	@Override
	public Object stringsToClass(String[] data) {
		// TODO Auto-generated method stub
		Activity novaAtividade = new Activity();
		novaAtividade.setId(data[0]);
		
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();
		LocalDateTime dateTime = null;
		try {
			dateTime = LocalDateTime.parse(data[1],  formatter);
        } catch (DateTimeParseException e) {
        	return null;
        }
		
		
		novaAtividade.setDate(dateTime);
		novaAtividade.setUserIdentifier(data[2]);
		novaAtividade.setPc(data[3]);
		
		//novaAtividade.set
		if(activityType == Activity.type.HTTP)
		{
			novaAtividade.setUrl(data[4]);
		}else {
			if(data[4].equals("Connect"))
			{
				novaAtividade.setActive(true);
			}else
			{
				if(data[4].equals("Disconnect")){
					novaAtividade.setActive(false);
				}
			}
		}
		
		return novaAtividade;
	}
	
	/*
	 * Has bad time complexity. n^2, if both users and the mean amount of pc they use increase 
	 * but it's unlikely that the mean amount of pcs used is high enough to cause performance problems.
	 * although it should be improved later to get constant time complexity. 
	 */
	public void loadActivitiesFromCsv(UserTrees tree, LocalDateTime timePeriodBegin, LocalDateTime timePeriodEnd) {
		
		Activity atividade;
		Node<Object> userNode, timeNode, pcNode;
		String csvLine = getNextLine();
		/*Verifies if the line is a header. ignores it if that's the case*/
		Pattern p = Pattern.compile("/id|date|user|pc|activity/i");
		Matcher m = p.matcher( csvLine );
		if(!m.find()) {
			atividade = (Activity) stringsToClass( parseCsvLine( csvLine ) );
			
			userNode = tree.getUserNode( atividade.getUserIdentifier() );
			timeNode = userNode.getChild(0);
			pcNode = loadPcIntoTime(timeNode, atividade.getPc());
			loadActivityIntoPc(pcNode, atividade);	
			if(atividade.getDate().isAfter(timePeriodBegin) && atividade.getDate().isBefore(timePeriodEnd))
			{
				timeNode = userNode.getChild(1);
				pcNode = loadPcIntoTime(timeNode, atividade.getPc());
				loadActivityIntoPc(pcNode, atividade);
			}
		}
		csvLine = getNextLine();
		while(csvLine != null)
		{
			atividade = (Activity) stringsToClass( parseCsvLine( csvLine ) );
			if(atividade==null)
			{
				csvLine = getNextLine();
				continue;
			}
			
			userNode = tree.getUserNode( atividade.getUserIdentifier() );
			if(userNode == null) {
				return;
			}
			timeNode = userNode.getChild(0);
			pcNode = loadPcIntoTime(timeNode, atividade.getPc());
			loadActivityIntoPc(pcNode, atividade);	
			if(timePeriodBegin == null || timePeriodEnd == null) 
			{
				continue;
			}
			if(atividade.getDate().isAfter(timePeriodBegin) && atividade.getDate().isBefore(timePeriodEnd))
			{
				timeNode = userNode.getChild(1);
				pcNode = loadPcIntoTime(timeNode, atividade.getPc());
				loadActivityIntoPc(pcNode, atividade);
			}
			csvLine = getNextLine();
		}
		
	}
	
	private Node<Object> loadActivityIntoPc(Node<Object> pcNode, Activity atividade ) {
		
		return pcNode.addChild(atividade);
		
	}
	//checks if the pc of the created activity was already added to the tree
	//gets its node if that's the case and add the activity, otherwise create a new pc node and add activity to it.
	//potential error case: .getData() != pc but .getData()'s string is similar to pc.
	private Node<Object> loadPcIntoTime(Node<Object> timeNode, String pc) {
		List<Node<Object>> children = timeNode.getChildren();
		for(int i = 0; i < children.size(); i++)
		{
			if( (String) children.get(i).getData() == pc )
			{
				return timeNode.getChild(i);
			}
		}
		return timeNode.addChild(pc);
	}
	
	// retrieved from https://stackoverflow.com/questions/32204953/validating-java-8-dates
	public static boolean isDateValid(String input) {
	    DateTimeFormatter[] formatters = {
	            new DateTimeFormatterBuilder().appendPattern("yyyy")
	                    .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
	                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
	                    .toFormatter(),
	            new DateTimeFormatterBuilder().appendPattern("yyyy-MM")
	                    .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
	                    .toFormatter(),
	            new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
	                    .parseStrict().toFormatter() };
	    for(DateTimeFormatter formatter : formatters) {
	        try {
	            LocalDate.parse(input, formatter);
	            return true;
	        } catch (DateTimeParseException e) {
	        }
	    }
	    return false;
	}
}


