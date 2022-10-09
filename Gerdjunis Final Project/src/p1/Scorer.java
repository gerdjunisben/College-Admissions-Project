package p1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Scorer {
	private int gpaScore;
	private int fletchScore;
	private int typoScore;
	private int incomeScore;
	private int satScore;
	private int ai;
	
	public int scoreUser(User user)
	{//0 accept,1 denied,2 waiting,-1 scoring unneeded
		if(user.isGraded()==false)
		{
			user.setGraded(true);
			gpaScore =	(int)( 2*(user.getGpa()/0.1));
			fletchScore = (int)(2*(getFletchScore(user.getEssayFile())/10));
			typoScore  = (int)(getTyposPerWords(user.getEssayFile())/100);
			incomeScore = user.getIncome()/10000;
			satScore = user.getSat()/100;
			ai = gpaScore - fletchScore - typoScore + incomeScore + satScore;
			user.setAi(ai);
			user.setTypoScore(typoScore);
			user.setFletchScore(fletchScore);
			Utilities.backUpBag();
			String fileName = "decisions/" + user.getLast() + "." + user.getFirst() + ".decision.txt";
			if(gpaScore>=70 && fletchScore<120  && typoScore<3 && incomeScore >= 10 && satScore>=12)
			{
				writeToFile(fileName,0,user.getEmail());
				user.setDecision(0);
				return 0;
			}
			else if(gpaScore<50 && fletchScore>140 && typoScore > 10 && incomeScore <1 && satScore<8)
			{
				writeToFile(fileName,1,user.getEmail());
				user.setDecision(1);
				return 1;
			}
			else
			{
				Utilities.queue.insert(user);
				Utilities.backUpList();
				user.setDecision(2);
			
				writeToFile(fileName,2,user.getEmail());
				return 2;
			}
		}
		else
		{
			if(user.getDecision()==2)
			{
				gpaScore =	(int)( 2*(user.getGpa()/0.1));
				fletchScore = user.getFletchScore();
				typoScore  = user.getTypoScore();
				incomeScore = user.getIncome()/10000;
				satScore = user.getSat()/100;
				ai = user.getAi();
				String fileName = "decisions/" + user.getLast() + "." + user.getFirst() + ".decision.txt";
				writeToFile(fileName,2,user.getEmail());
				return 2;
			}	
			return -1;
		}
		
		
		
		
	}
	
	private LinkedList<String> read(File file)
	{
		LinkedList<String> list = new LinkedList<>();
		try
		{
			FileReader r = new FileReader(file);
			BufferedReader br = new BufferedReader(r);
			String s= br.readLine();
			while(s != null)
			{
				String[] split = s.trim().split(" ");
				for(int i = 0;i<split.length;i++)
				{
					list.add(split[i]);
				}
				s = br.readLine();
			}
			br.close();
			r.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	private double getTyposPerWords(File essayFile)
	{
		HashSet<String> dictionary = Utilities.restoreDictionary();
		
		LinkedList<String> essay = read(essayFile);
		Iterator<String> itr = essay.iterator();
		int typos = 0;
		String word;
		while(itr.hasNext())
		{
			word = itr.next();
			if(!dictionary.contains(word))
				typos++;
		}
		return typos;
		
	}

	private double getFletchScore(File file)
	{
		LinkedList<String> essay = read(file);
		int totalWords = essay.size();
		int totalSentences = 0;
		int totalSyllables = 0;
		
		
		Pattern p = Pattern.compile("[aeiouy]+[^$e(,.:;!?)]");
		Matcher m ;
		String word;
		Iterator<String> itr = essay.iterator();
		while(itr.hasNext())
		{
			word = itr.next();
			if(word.indexOf('.')!=-1)
			{
				totalSentences++;
			}
			m = p.matcher(word);
			while(m.find())
			{
				totalSyllables++;
			}
			
		}
		
		return 206.835 - 1.015*(totalWords/totalSentences) - 84.6*(totalSyllables/totalWords);
	}
	
	
	private void writeToFile(String fileName,int status,String email)
	{
		try
		{
			PrintWriter pw = new PrintWriter(fileName);
			
			pw.println(generateTable(status,email));
			pw.close();
		}
		catch(Exception c)
		{
			c.printStackTrace();
		}
		
		
	}
	
	private String generateTable(int status,String email)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Decision || Gpa || Fletch || Typo || Income || SAT  || Ai Score\n");
		
		if(status == 0)
			sb.append("Accepted");
		else if(status == 1)
			sb.append("Denied  ");
		else
			sb.append("Waiting  ");
		
		if(gpaScore>=70)
			sb.append("||Great||");
		else if(gpaScore<50)
			sb.append("||Poor ||");
		else
			sb.append("||Good ||");
		
		if(fletchScore<120)
			sb.append("  Great ||");
		else if(fletchScore>140)
			sb.append("  Poor  ||");
		else
			sb.append("  Good  ||");
		
		if(typoScore<3)
			sb.append("Great ||");
		else if(typoScore>10)
			sb.append(" Poor ||");
		else
			sb.append(" Good ||");
		
		if(incomeScore>=10)
			sb.append(" Great   ||");
		else if(incomeScore<1)
			sb.append("  Poor   ||");
		else
			sb.append("  Good   ||");
		
		if(satScore>=12)
			sb.append("Great||");
		else if(satScore<8)
			sb.append("Poor ||");
		else
			sb.append("Good ||");
		if(status == 2)
			sb.append(String.format("\nPlace:%4s||%8s||%9s||%9s||%11s||%7s||%4s" , (Utilities.queue.position(email) +1), gpaScore , -fletchScore , -typoScore , incomeScore , satScore, ai));
		else
			sb.append(String.format("\n               ||%7s||%9s||%9s||%10s||%7s||%4s" , gpaScore , -fletchScore , -typoScore , incomeScore , satScore, ai));
		
			
		return sb.toString();
	}
}
