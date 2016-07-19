package kr.ac.sungkyul.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
/**
 * 다중처리 Echo Server
 * @author 형민
 *
 */
public class echoServer3 {
	private final static int SEVER_PORT = 1300;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩 (서버의 아이피주소와 포트를!)
			InetAddress inetAddress = InetAddress.getLocalHost(); // 동적으로 세팅하기
			String serverAddress = inetAddress.getHostAddress();
			InetSocketAddress inetSocketAddress = new InetSocketAddress(serverAddress, SEVER_PORT);

			serverSocket.bind(inetSocketAddress); // 바인딩 시키기
			System.out.println("[서버] bind:" + serverAddress + " : " + SEVER_PORT);

			while(true){
			// 3. accept 클라이언트로 부터 연결(요청) 대기
			Socket socket = serverSocket.accept(); // blocking (밑으로 내려가지않고 기다리고 // 있는 것, 연결되면 다음 라인으로 빠져나옴)
			EchoServer3ReceivedThread thread= new EchoServer3ReceivedThread(socket);
			thread.start();
			}
   
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				// 9. 서버 소켓 닫기
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
