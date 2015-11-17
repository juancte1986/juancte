package ar.edu.uces.progweb2.agenda.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CookieHelper {
	
	public void createCookie(String user, String password, HttpServletResponse response){
		Cookie cookie = new Cookie("cookieCalendar", user +" "+password); 
		cookie.setMaxAge(31536000);
		cookie.setPath("/");
	}
	
	public void deleteCookie(HttpServletResponse response){
		Cookie cookie = new Cookie("cookieCalendar", null); 
		cookie.setMaxAge(31536000);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public String getPassword(String value){
		String [] args = value.split(" ");  
		return args[1];
	}
	
	public String getUser(String value){
		String [] args = value.split(" ");  
		return args[2];
	}
}
