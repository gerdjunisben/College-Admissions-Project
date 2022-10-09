package p1;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Comparator;
import java.util.PriorityQueue;

public class WaitingList implements Serializable {
	PriorityQueue<User> heap;
	
	public WaitingList()
	{
		SerialComparator comparator = (user1,user2) -> Integer.compare(user2.getAi(),user1.getAi());
		heap  = new PriorityQueue<>(comparator);
	}
	
	public void insert(User user)
	{
		heap.add(user);
	}
	
	public User remove()
	{
		return heap.remove();
	}
	
	public int position(String email)
	{
		User[] users = heap.toArray(new User[0]);
		
		for(int x = 0;x<users.length;x++)
		{
			if(users[x].getEmail().equals(email))
			{
				return x;
			}
		}
		return -1;
	}
	

	public void displayAll()
	{
		User[] temp = heap.toArray(new User[0]);
		for(User u:temp)
		{
			System.out.println(u);
		}
	}
}
