package p1;

import java.io.Serializable;
import java.util.Comparator;

public interface SerialComparator extends Serializable,Comparator<User>{
	public int compare(User u1,User u2);
	
}
