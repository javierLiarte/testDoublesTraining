package tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static tests.Fixtures.aListOfRawUsers;

import java.util.HashMap;

import org.junit.Test;
import org.mockito.Mockito;

import production.Factory;
import production.User;

public class TestsUsing_Moq {
/*
 * AS THIS IS C# CODE, IT'S COMMENTED TO AVOID BUILD ERRORS
 * 
 * 
 * [Test]
 * public void another_user_service_retrieves_users_from_repo(){
 *    var service = Factory.createAnotherUserService();
 *    var repo = new Mock<UserRepository>();
 *    service.injectDependency(repo.Object);
 *    
 *    repo.SetUp(x => x.retrieveUsers()).Returns(aListOfRawUsers());
 *    
 *    var foundUser = service.findBy("test@test.com");
 *		
 *	  // using FluentAssertions
 *	  foundUser.hasEmail("test@test.com").Should().BeTrue();
 * }
 * 
 * 	[Test]
 *	public void another_user_service_stores_user() {
 *    var service = Factory.createAnotherUserService();
 *    var repo = new Mock<UserRepository>();
 *    service.injectDependency(repo.Object);
 *		
 *    service.addNewUser(new User().setEmail("test@test.com"));
 *		
 *    // Different levels of detail in the verification:
 *    
 *	  // A - Not very precise/coupled:
 *	  repo.Verify(x => x.store(It.IsAny<Dictionary>())); 
 *    
 *    // B - Very precise/coupled:
 *	  repo.Verify(
 *          x => x.store(
 *               It.Is<Dictionary>(d => d["email"] == "test@test.com")
 *          ), 
 *          Times.Once());
 *	}
 * 
 *
 */

}
