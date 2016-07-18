package kr.ac.sungkyul.network.echo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class echoServer {
	private final static int SEVER_PORT = 1500;

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

			// 3. accept 클라이언트로 부터 연결(요청) 대기
			Socket socket = serverSocket.accept(); // blocking (밑으로 내려가지않고 기다리고
													// 있는 것, 연결되면 다음 라인으로 빠져나옴)

			// 4. 연결 성공
			InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
			int remoteHostPort = remoteAddress.getPort();
			// System.out.println("[server] 연결 성공 from"+
			// remoteAddress.getAddress().getHostAddress()+" : "
			// +remoteAddress.getPort()); //ip와 port정보
			System.out.println("[server] 연결 성공 from " + remoteHostAddress + ":" + remoteHostPort);

			try {

				// 5.IOStream
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {

					// 6. 데이터 읽기
					byte[] buffer = new byte[256];
					int readbytes = is.read(buffer); // blocked //읽은만큼의 byte수를
														// 반환
					if (readbytes <= -1) { // 클라이언트가 연결을 끊었다(정상종료)
						System.out.println("[server] closed by clients");
						return;
					}

					String data = new String(buffer, 0, readbytes, "utf-8");
					if ("quit".equals(data)) {
						break;
					}

					System.out.println("[server] received :" + data);

					// 7. 데이터 쓰기
					os.write(data.getBytes("utf-8")); // utf8엔코딩을 바이트로 끄집어 내겠다.
				}

				// if(socket!=null && socket.isClosed()==false){
				// socket.close();
			} catch (SocketException e) {
				System.out.println("[server] 비정상적으로 클라이언트가 연결을 끊었습니다.");

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 8. 소켓 닫기
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}

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
