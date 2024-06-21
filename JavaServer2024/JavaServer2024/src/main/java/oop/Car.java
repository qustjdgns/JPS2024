package oop;

public abstract class Car {

	public int speed;
	public int adult;
	public int child;
	
	final public void speedUp()
	{
		speed = speed+1;
	}
	
	public int people()
	{
		return adult + child;
	}
	
	abstract public void move();
	
}
