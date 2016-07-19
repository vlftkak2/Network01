package kr.ac.sungkyul.thread;

public class ThreadExample {

	public static void main(String[] args) {
		
		DigitThread thread1=new DigitThread();
		AlphabetThread thread2=new AlphabetThread();
		AlphabetThread thread3=new AlphabetThread();
		Thread thread4=new Thread(new UpperCaseAlphabetRunnableimpl());
		
		
		thread1.start(); //쓰레드가 잠깐 쉬는동안에 for문이 들어간다.
		thread2.start();
		thread3.start();
		thread4.start();


		for (int i = 'a'; i < 'z'; i++) {
			System.out.print((char) i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
