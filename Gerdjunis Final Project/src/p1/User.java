package p1;

import java.io.File;
import java.io.Serializable;

public class User implements Comparable<User>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5862601430604178001L;
	private String first,last,email,password,phoneNumber,address;
	private double gpa;
	private int sat,income;
	private File essayFileName;
	private String id;
	public static int idCount = 0;
	private int ai;
	private boolean graded = false;
	private int decision = -1;
	private int typoScore;
	private int fletchScore;
	
	public User(String email,String password)
	{
		this.email = email;
		this.password = password;
			
		
		id = idCount++ + "";
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
	
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		if(gpa <=4.0 && gpa >0.0)
			this.gpa = gpa;
	}

	public int getSat() {
		return sat;
	}

	public void setSat(int sat) {
		if(sat>=400 && sat<=1600)
			this.sat = sat;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public File getEssayFile() {
		return essayFileName;
	}

	public void setEssayFile(File essayFileName) {
		this.essayFileName = essayFileName;
	}

	public String getId() {
		return id;
	}
	
	public void setAi(int ai)
	{
		this.ai = ai;
	}
	
	

	public int getAi() {
		return ai;
	}
	
	
	
	public boolean isGraded() {
		return graded;
	}

	public void setGraded(boolean graded) {
		this.graded = graded;
	}

	public int getDecision() {
		return decision;
	}

	public void setDecision(int decision) {
		this.decision = decision;
	}
	
	

	public int getTypoScore() {
		return typoScore;
	}

	public void setTypoScore(int typoScore) {
		this.typoScore = typoScore;
	}

	public int getFletchScore() {
		return fletchScore;
	}

	public void setFletchScore(int fletchScore) {
		this.fletchScore = fletchScore;
	}

	public boolean isComplete()
	{
		if(first!=null&&last!=null&&email!=null&&password!=null&&phoneNumber!=null&&address!=null&&gpa!=0&&sat!=0 && income!=0&&essayFileName!=null)
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.email.compareTo(o.email);
	}

	@Override
	public String toString() {
		return "User [first=" + first + ", last=" + last + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", address=" + address + ", gpa=" + gpa + ", sat=" + sat
				+ ", income=" + income + ", essayFileName=" + essayFileName + ", id=" + id + ", ai=" + ai + "]";
	}
	
	
}
