package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.powermock.api.mockito.PowerMockito.*;
import static org.mockito.Mockito.verify;

import production.AnotherUserService;
import production.Factory;
import production.User;
import production.UserRepository;
import production.UserService;
import static tests.Fixtures.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest( { User.class })
public class TestsUsing_PowerMock_Isolation {
	
	
	@Test
	public void it_retrieves_users_from_repo() {
		UserService service = Factory.createUserService();
		stubOutTheStaticMethod_from_onClass_User();
		
		User foundUser = service.findBy("test@test.com");
		
		assertTrue(foundUser.hasEmail("test@test.com"));
	}

	public void stubOutTheStaticMethod_from_onClass_User() {
		mockStatic(User.class);
		when(User.from(Mockito.anyList())).thenReturn(aListOfUsers());
	}

	public List<User> aListOfUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User().setEmail("test@test.com"));
		return users;
	}
}
