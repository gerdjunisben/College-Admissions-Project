package p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class Utilities {
	public static Scorer score;
	public static WaitingList queue;
	public static UserBag bag;
	public static User currentUser;
	
	public static HashSet<String> restoreDictionary()
	{
		File file = new File("backups/Dictionary.dat");
		HashSet<String> dictionary;
		if(!file.exists())
		{
			dictionary = new HashSet<String>(150000);
			try
			{
				FileReader r = new FileReader("Dictionary");
				BufferedReader br = new BufferedReader(r);
				String s= br.readLine();
				while(s != null)
				{
					
					dictionary.add(s);
					s = br.readLine();
				}
				br.close();
				r.close();
				backUpDictionary(dictionary);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return dictionary;
		}
		else
		{
			try
			{
				
				FileInputStream fis = new FileInputStream("backups/Dictionary.dat");
				ObjectInputStream oip = new ObjectInputStream(fis);
			
				dictionary = (HashSet<String>)(oip.readObject());
			
				oip.close();
				return dictionary;
			
			
			}
			catch(Exception e)
			{
				
				e.printStackTrace();
				return null;
			}
		}
	}
	
	private static void backUpDictionary(HashSet<String> dictionary)
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("backups/Dictionary.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dictionary);
			
			oos.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void backUpBag()
	{
		try
		{
			FileOutputStream fos = new FileOutputStream("backups/UserBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bag);
			
			oos.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void backUpList()
	{
		try
		{
			
			FileOutputStream fos = new FileOutputStream("backups/WaitingList.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(queue);
			
			oos.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void restoreUserBag()
	{
		File file = new File("backups/UserBag.dat");
		if(!file.exists())
		{
			bag = new UserBag();
			return;
		}
		
		try
		{
			
			FileInputStream fis = new FileInputStream("backups/UserBag.dat");
			ObjectInputStream oip = new ObjectInputStream(fis);
			
			bag = (UserBag)(oip.readObject());
			
			oip.close();
			User.idCount = bag.getSize();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void restoreWaitingList()
	{
		File file = new File("backups/WaitingList.dat");
		if(!file.exists())
		{
			queue = new WaitingList();
			return;
		}
		try
		{
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oip = new ObjectInputStream(fis);
			
			queue = (WaitingList)(oip.readObject());
			
			oip.close();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
