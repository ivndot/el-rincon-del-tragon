package mx.ipn.upiicsa.segsw.el_rincon_del_tragon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Guillermo E. Martinez Barriga
 *
 */

public class SecurityUtility {
	
	private static final String PASSWORD_RULES = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.!#$%&_])[A-Za-z\\d.!#$%&_]{5,20}$";
	
	public static boolean isPasswordStrong(String password)
	{
		return password.matches(PASSWORD_RULES);
	}
	
	public static boolean validateEmail(String email) {
		String regex = "^[\\w+_.#$%]+@[\\w+._]+$"; //expresion regular \w --> [a-zA-Z0-9]
	    Pattern pattern = Pattern.compile(regex); // se compila la expresion regular
	    Matcher matcher = pattern.matcher(email); // se buscar la expresion regular en el email
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	    	//el email esta bien
	    	System.out.println("Email bien");
	    	return true;
	    }else {
	    	//el email tiene caracteres incorrectos
	    	System.out.println("Email mal");
	    	return false;
	    }
	}
	
	public static boolean validatePassword(String password) {
		//String regex = "^(?=[\\w.!#$%&_]*[\\w])(?=[\\w.!#$%&_]*[.!#$%&_]*)[\\w.!#$%&_]{5,20}$";  //expresion regular \w --> [a-zA-Z0-9] {5,20} --> 5 a 20 caracteres
		String regex = "^(?=.*[a-z]*)(?=.*[A-Z]*)(?=.*\\d*)(?=.*[.!#$%&_]*)[A-Za-z\\d.!#$%&_]{5,20}$";
		Pattern pattern = Pattern.compile(regex); // se compila la expresion regular
	    Matcher matcher = pattern.matcher(password); // se buscar la expresion regular en el password
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	    	//el password esta bien
	    	System.out.println("Password bien");
	    	return true;
	    }else {
	    	//el password tiene caracteres incorrectos
	    	System.out.println("Password mal");
	    	return false;
	    }
	}
	
	public static boolean validateNames(String name) {
		String regex = "[a-zA-Z]{2,20}$";
		Pattern pattern = Pattern.compile(regex); // se compila la expresion regular
	    Matcher matcher = pattern.matcher(name); // se buscar la expresion regular en el password
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	    	//el name esta bien
	    	System.out.println("Password bien");
	    	return true;
	    }else {
	    	//el name tiene caracteres incorrectos
	    	System.out.println("Password mal");
	    	return false;
	    }
	}
	
	public static boolean validateInputSearch(String criterio) {
		// si no se envia criterio
		if(criterio == null || criterio.isEmpty()) {
			// se dice que el input es correcto
			return true;
			
		}else {
			// se valida el input ya que tiene por lo menos un caracter
			//String regex = "^(?=[\\w.!#$%&_\\s]*[\\w])(?=[\\w.!#$%&_\\s]*[.!#$%&_\\s]*)(?=[\\s]*)[\\w.!#$%&_\\s]{0,50}$";  //expresion regular \w --> [a-zA-Z0-9] \s --> espacio en blanco {5,20} --> 5 a 20 caracteres
			String regex = "^(?=.*[a-z]*)(?=.*[A-Z]*)(?=.*\\d*)(?=.*[.!#$%&_\\s]*)[A-Za-z\\d.!#$%&_\\s]{0,50}$";
			Pattern pattern = Pattern.compile(regex); // se compila la expresion regular
		    Matcher matcher = pattern.matcher(criterio); // se buscar la expresion regular en el password
		    boolean matchFound = matcher.find();
		    if(matchFound) {
		    	//el criterio esta bien
		    	return true;
		    }else {
		    	//el criterio tiene caracteres incorrectos
		    	return false;
		    }
		}
		
	}
	
	public static boolean validateId(String id) {
		String regex = "^[0-9]$+";  //expresion regular [0-9]+ --> por lo menos un caracter numerico
	    Pattern pattern = Pattern.compile(regex); // se compila la expresion regular
	    Matcher matcher = pattern.matcher(id); // se buscar la expresion regular en el password
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	    	//el id esta bien
	    	return true;
	    }else {
	    	//el id tiene caracteres incorrectos
	    	return false;
	    }
	}
	
	public static boolean validateRating(String rating) {
		String regex = "^[1-5]$";  //expresion regular [0-5]+ --> por lo menos un caracter numerico
	    Pattern pattern = Pattern.compile(regex); // se compila la expresion regular
	    Matcher matcher = pattern.matcher(rating); // se buscar la expresion regular en el password
	    boolean matchFound = matcher.find();
	    if(matchFound) {
	    	//el rating esta bien
	    	return true;
	    }else {
	    	//el rating tiene caracteres incorrectos
	    	return false;
	    }
	}
	
}
