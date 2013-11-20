package production;

public class NoUser extends User {

	@Override
	public int id(){
		return -1;
	}

    @Override
    public boolean isValid(){
        return false;
    }
}
