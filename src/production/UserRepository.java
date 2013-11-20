package production;

import java.util.HashMap;
import java.util.List;

public interface UserRepository {

	void store(HashMap<String, String> user);
	List<HashMap<String, String>> retrieveUsers();

}
