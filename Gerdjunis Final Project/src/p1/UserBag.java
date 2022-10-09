package p1;
import java.io.Serializable;
import java.util.TreeSet;

public class UserBag implements Serializable {

	@Override
	public String toString() {
		return "UserBag [tree=" + tree + "]";
	}

	private TreeSet<User> tree;
	
	public UserBag()
	{
		tree = new TreeSet<>();
	}
	
	public void insert(User user)
	{
		tree.add(user);
	}
	
	public User search(String email)
	{
		User temp = new User(email," ");
		if(tree.contains(temp))
		{
			
			return tree.tailSet(temp).first();
		}
		return null;
	}
	
	public int getSize()
	{
		return tree.size();
	}
	
}
