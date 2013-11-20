package tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fixtures {
	public static List<HashMap<String, String>> aListOfRawUsers() {
		HashMap<String, String> user = new HashMap<String, String>();
		user.put("email",  "test@test.com");
		List<HashMap<String, String>> users = new ArrayList<HashMap<String, String>>();
		users.add(user);
		return users;
	}

	public static List<HashMap<String, String>> aListOfRecentUsers() {
		List<HashMap<String, String>> users = Fixtures.aListOfRawUsers();
		users.get(0).put("registrationDate", 
				String.valueOf(System.currentTimeMillis()));
		return users;
	}
}
