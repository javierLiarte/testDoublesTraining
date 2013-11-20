package production;

import java.util.HashMap;
import java.util.List;

public class User {
	String name, email;
	long registrationDate = 0;
	long MILLISECS_IN_A_DAY = 1000 * 24 * 60 * 60;
	
	public int id(){
		return 0;
	}
	
	public String name(){
		return name;
	}
	
	public User setName(String name){
		this.name = name;
		return this;
	}
	
	public User setEmail(String email){
		this.email = email;
		return this;
	}
	
	public void setRegistrationDate(long milliseconds){
		registrationDate = milliseconds;
	}
	
	public boolean wasRegisteredToday(){
		return System.currentTimeMillis() - registrationDate <= MILLISECS_IN_A_DAY;
	}
	
	public boolean isValid(){
		return id() > 0;
	}
	
	public boolean hasEmail(String email) {
		return this.email.equals(email);
	}

	public static List<User> from(List<HashMap<String,String>> rawUsers) {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<String, String> asRaw() {
		// TODO Auto-generated method stub
		return null;
	}

	public static List<User> loadAll(UserRepository userRepo) {
		return from(userRepo.retrieveUsers());
	}

	public void store(UserRepository userRepo) {
		userRepo.store(this.asRaw());
	}

}
