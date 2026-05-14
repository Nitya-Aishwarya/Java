package com.practice;



abstract class College{
//	abstract void department();
	abstract void collegeName();
	void department() {
		System.out.println("Cse department");
	}
}


class Jntuk extends College{

	@Override
	void collegeName() {
		// TODO Auto-generated method stub
		System.out.println("Jntuk");
	}

	
}

class JntuN extends College{

	@Override
	void collegeName() {
		// TODO Auto-generated method stub
		System.out.println("JntuN");
	}

	
}

public class Abstraction {
	public static void main(String[] args) {
		College jntuk=new Jntuk();
		jntuk.collegeName();
		jntuk.department();
	}
}
