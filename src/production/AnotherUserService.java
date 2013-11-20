package production;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnotherUserService {
	UserRepository userRepo;
	
	public AnotherUserService(UserRepository repo){
		userRepo = repo;
	}
	
	public void injectUserRepo(UserRepository repo){
		userRepo = repo;
	}
	
	/////////// READ:
	
	protected List<User> parseFrom(List<HashMap<String,String>> rawUsers){
		List<User> result = new ArrayList<User>();
		for (HashMap<String, String> rawUser: rawUsers)
			result.add(new User().setEmail(rawUser.get("email")));
		return result;
	}
	
	public User findBy(String email){
		List<User> users = parseFrom(userRepo.retrieveUsers());
		for(User user: users)
			if (user.hasEmail(email))
				return user;
		return new NoUser();
	}
	
	
	/////////// WRITE:	
		
	public void addNewUser(User user){
		user.setRegistrationDate(System.currentTimeMillis());
		userRepo.store(asRaw(user));
	}
	
	private HashMap<String, String> asRaw(User user) {
		HashMap<String, String> rawUser = new HashMap<String,String>();
		rawUser.put("email", user.email);
		rawUser.put("registrationDate", String.valueOf(user.registrationDate));
		return rawUser;
	}

}
