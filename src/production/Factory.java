package production;

public class Factory {

	public static UserService createUserService(){
		return new UserService(
				new UserRepoFilesystem());
	}
	
	public static AnotherUserService createAnotherUserService(){
		return new AnotherUserService(
				new UserRepoFilesystem());
	}	
}
