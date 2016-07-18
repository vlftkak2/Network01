package kr.ac.sungkyul.network.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		
		try {
			InetAddress inetAddress =InetAddress.getLocalHost(); //local호스트를 객체로 받아서 리턴해줘라
			String hostname=inetAddress.getHostName();
			String hostAddress=inetAddress.getHostAddress();
			byte[] address=inetAddress.getAddress();
			
			System.out.println("host name="+hostname);
			System.out.println("host Address="+hostAddress);
			
			for(int i=0; i<address.length; i++){
				System.out.print(address[i]&0x000000ff);
				if(i<address.length){
					System.out.print(".");
				}
			}

	
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
	}

}
