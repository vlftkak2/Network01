package kr.ac.sungkyul.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				System.out.print(">>");
				String host = scanner.nextLine();
				InetAddress[] inetAddress = InetAddress.getAllByName(host); // 호스트에 대한 정보가 담겨있다
              //InetAddress[] inetAddress = InetAddress.getAllByName("www.naver.com"); // 호스트에 대한 정보가 담겨있다

				if ("quit".equals(host)) {
					break;
				}
				for (InetAddress inetAddres : inetAddress) {
					System.out.println(inetAddres.getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println("unKnown host");
				// e.printStackTrace();
			}
		}
		scanner.close();


	}

}
