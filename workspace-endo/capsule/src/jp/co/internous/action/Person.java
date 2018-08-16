package jp.co.internous.action;

public class Person {

	private String name=null;
	private int age=0;

	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}

	public String getName(){
		return this.name;
	}

	public int getAge(){
		return this.age;
	}

	public void setName(String name){
		this.name=name;
	}

	public void setAge(int age){
		this.age=age;
	}

}

