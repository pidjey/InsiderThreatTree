package logReaders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dataManipulators.InsiderThreatTrees;
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
}
