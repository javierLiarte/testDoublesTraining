package tests;

//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.*;
//import mockit.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import java.util.HashMap;
//import org.junit.Test;
//import org.mockito.ArgumentCaptor;
import production.AnotherUserService;
import production.Factory;
import production.User;
import production.UserRepository;


public class TestsUsing_jMockit_Isolation {
	
	final long currentTime = 123456L;
	
	// This is a combination of mockito and jMockit
/*	
	@Test
	public void it_saves_registration_time() {
		AnotherUserService service = Factory.createAnotherUserService();
		UserRepository repo = mock(UserRepository.class); // mockito
		service.injectUserRepo(repo);
		
		simulateCurrentTime();
				
		service.addNewUser(new User());
		
		verifyThatNewUserHasTheCurrentDate(repo);
	}

	public void simulateCurrentTime() {
		 new Expectations() { // jMockito magic
			 @Mocked("currentTimeMillis") System mockedSystem;
			 { 
			     System.currentTimeMillis(); 
	             result = currentTime; 
	        }}; 
	}

	public void verifyThatNewUserHasTheCurrentDate(UserRepository repo) {
		ArgumentCaptor<HashMap> argument = capturedArgument(repo);
		String storedRegistrationDate = argument.getValue().get("registrationDate").toString();
		String expectedStoredDate = String.valueOf(currentTime);
		assertThat(storedRegistrationDate, is(expectedStoredDate));
	}

	public ArgumentCaptor<HashMap> capturedArgument(UserRepository repo) {
		// mockito
		ArgumentCaptor<HashMap> argument = ArgumentCaptor.forClass(HashMap.class);
		verify(repo).store(argument.capture());
		return argument;
	}
*/
}
