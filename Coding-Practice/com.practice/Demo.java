package com.practice;

class School{
	private String schoolName;
	
	
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void getSchoolName() {
		System.out.println("School name is:"+schoolName);
	}
}


class SriPrakash extends School{
	
	public void print() {
		System.out.println("Sriprakash School");
	}
}
public class Demo {
	
	
	public static void main(String[] args) {
		School school=new SriPrakash();
		school.getSchoolName();
//		SriPrakash sriPrakash=(SriPrakash) new School();
//		sriPrakash.print();
		int a = 10;

		System.out.println(a++);
		System.out.println(a);
		a++;
		System.out.println(a);
		a--;
		System.out.println(a);
	}
}
