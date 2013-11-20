package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import production.AnotherUserService;
import production.Factory;
import production.User;
import production.UserRepository;
import production.UserService;

public class TestsUsing_CustomDoubles {

	class UserRepoDouble implements UserRepository{
		public HashMap<String, String> storedUser = null;
		
		@Override
		public void store(HashMap<String, String> user) {
			storedUser = user;
		}
		@Override
		public List<HashMap<String, String>> retrieveUsers() {
			HashMap<String, String> user = new HashMap<String,String>();
			user.put("email", "test@test.com");
			List<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
			users.add(user);
			return users;
		}	
	}

	@Test
	public void another_user_service_retrieves_users_from_repo() {
		AnotherUserService service = Factory.createAnotherUserService();
		service.injectUserRepo(new UserRepoDouble());
		
		User foundUser = service.findBy("test@test.com");
		
		assertTrue(foundUser.hasEmail("test@test.com"));
	}
	
	@Test
	public void stores_new_user_in_repo() {
		AnotherUserService service = Factory.createAnotherUserService();
		UserRepoDouble repo = new UserRepoDouble();
		service.injectUserRepo(repo);
		
		service.addNewUser(new User().setEmail("test@test.com"));
		
		assertThat(repo.storedUser.get("email"), is("test@test.com"));
	}
	
//	@Test
	public void user_service_retrieves_users_from_repo() {
		UserService service = Factory.createUserService();
		service.injectUserRepo(new UserRepoDouble());
		
		User foundUser = service.findBy("test@test.com");
		
		assertTrue(foundUser.hasEmail("test@test.com"));
	}
}
