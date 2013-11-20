package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;

import production.AnotherUserService;
import production.Factory;
import production.User;
import production.UserRepository;
import static tests.Fixtures.*;

public class TestsUsing_jMock {
	AnotherUserService service;
	
	@Rule public JUnitRuleMockery context = new JUnitRuleMockery();
	
	@Before
	public void setUp(){
		service = Factory.createAnotherUserService();
	}
	
	@Test
	public void another_user_service_retrieves_users_from_repo() {
		configureRetrievalSimulation();
		
		User foundUser = service.findBy("test@test.com");
		
		assertTrue(foundUser.hasEmail("test@test.com"));
	}

	@Test
	public void another_user_service_stores_user() {
		setRepositoryExpectations();
		
		service.addNewUser(new User().setEmail("test@test.com"));	
	}

	public void configureRetrievalSimulation() {
		final UserRepository repo = context.mock(UserRepository.class);
		context.checking(new Expectations(){{
			allowing(repo).retrieveUsers(); 
			will(returnValue(aListOfRawUsers()));
		}});
		service.injectUserRepo(repo);
	}
	
	public void setRepositoryExpectations() {
		final UserRepository repo = context.mock(UserRepository.class);
		context.checking(new Expectations(){{
			oneOf(repo).store(with(any(HashMap.class))); 
		}});
		service.injectUserRepo(repo);
	}
}
