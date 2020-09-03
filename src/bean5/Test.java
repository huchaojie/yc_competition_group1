package bean5;

public class Test {
	public static void main(String[] args) {
		
	
		
		AppleBox ab = new AppleBox();

		Producer p = new Producer(ab);
		Consumer c = new Consumer(ab);

		Thread t = new Thread(p);
		t.start();

		Thread t2 = new Thread(c);
		t2.start();
	}
}

class Producer implements Runnable {
	AppleBox appleBox;

	public Producer(AppleBox appleBox) {
		this.appleBox = appleBox;
	}

	@Override
	public void run() {
		// 生产10个苹果存到appleBox中
		for (int i = 0; i < 10; i++) {
			Apple a = new Apple(i);
			appleBox.deposite(a);
			System.out.println(Thread.currentThread().getName() + "生产了:" + a);
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer implements Runnable {
	AppleBox appleBox;

	public Consumer(AppleBox appleBox) {
		this.appleBox = appleBox;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Apple a = appleBox.withdraw();
			System.out.println(Thread.currentThread().getName() + "消费了" + a);
			try {
				Thread.sleep((int) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class AppleBox {
	int index = 0;
	Apple[] apples = new Apple[5];

	public  void deposite(Apple apple) {
		// 判断apples是否满了，满 了，则不能再存
		if (index >= apples.length) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		apples[index] = apple;
		index++;
		// 没满，就存

	}

	public  Apple withdraw() {
		while (index == 0) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		Apple a = apples[index - 1];
		index--;
		return a;
	}

}

class Apple {
	int id;

	Apple(int id) {
		this.id = id;
	}

	public String toString() {
		return "apple " + id;
	}
}
