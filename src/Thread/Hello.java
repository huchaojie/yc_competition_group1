package Thread;

import java.util.Date;
public class Hello{
	public static void main(String[] args) {
		Simulator simulator = new Simulator();
		simulator.playSound(new Cat());
		simulator.playSound(new Dog());
		System.out.println("软件1801杨艳飞  " + new Date());
	}
}
abstract class Animal {
	abstract void cry();
	abstract String getAnimalName();
}
class Cat extends Animal {
	@Override
	void cry() {
		System.out.println("喵喵喵");
	}
	@Override
	String getAnimalName() {
		return "猫";
	}
}
class Dog extends Animal {
	@Override
	void cry() {
		System.out.println("汪汪汪");
	}
	@Override
	String getAnimalName() {
		return "狗";
	}
}
class Simulator {
	public void playSound(Animal animal) {
		String name = animal.getAnimalName();
		System.out.print(name + ":");
		animal.cry();
	}
}
