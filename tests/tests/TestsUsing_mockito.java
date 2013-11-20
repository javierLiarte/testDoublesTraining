package tests;

import static org.junit.Assert.*;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import production.AnotherUserService;
import production.Factory;
import production.User;
import production.UserRepository;
import static org.mockito.Mockito.*;
import static tests.Fixtures.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestsUsing_mockito {

	AnotherUserService service;
	UserRepository repo;
	
	@Before
	public void setUp(){
		service = Factory.createAnotherUserService();
		repo = mock(UserRepository.class);
		service.injectUserRepo(repo);
	}
	
	@Test
	public void another_user_service_retrieves_users_from_repo() {
		when(repo.retrieveUsers()).thenReturn(aListOfRawUsers());
				
		User foundUser = service.findBy("test@test.com");
		
		assertThat(foundUser.hasEmail("test@test.com"), is(true));
	}

	@Test
	public void another_user_service_stores_user() {
		service.addNewUser(new User().setEmail("test@test.com"));
		
		verify(repo).store(Mockito.any(HashMap.class));
	}	
}
