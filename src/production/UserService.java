package production;

import java.util.List;

public class UserService {
	UserRepository userRepo;
	
	public UserService(UserRepository repo){
		userRepo = repo;
	}
	
	public void injectUserRepo(UserRepository repo){
		userRepo = repo;
	}
	
	/////////// READ:
	
	public User findBy(String email){
		List<User> users = User.from(userRepo.retrieveUsers());
		for(User user: users)
			if (user.hasEmail(email))
				return user;
		return new NoUser();
	}
	
	/*
	 * Alternative implementation to findBy
	 */
	public User findByAgain(String email){
		List<User> users = User.loadAll(userRepo);
		for(User user: users)
			if (user.hasEmail(email))
				return user;
		return new NoUser();
	}	
	
	/////////// WRITE:	
		
	public void addNewUser(User user){
		userRepo.store(user.asRaw());
	}
	
	/*
	 * Alternative implemenation to addNewUser
	 */
	public void addNewUserAgain(User user){
		user.store(userRepo);
	}

}
