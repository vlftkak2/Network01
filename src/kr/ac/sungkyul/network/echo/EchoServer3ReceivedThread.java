package kr.ac.sungkyul.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer3ReceivedThread extends Thread {
	private Socket socket;
	public EchoServer3ReceivedThread(){
		
	}

	public EchoServer3ReceivedThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {

		// 4. 연결 성공
		InetSocketAddress remoteAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remoteHostAddress = remoteAddress.getAddress().getHostAddress();
		int remoteHostPort = remoteAddress.getPort();
		// System.out.println("[server] 연결 성공 from"+
		// remoteAddress.getAddress().getHostAddress()+" : "
		// +remoteAddress.getPort()); //ip와 port정보
		consoleLog(" 연결 from"+  remoteHostAddress + ":" + remoteHostPort);

		//System.out.println("[server] 연결 성공 from " + remoteHostAddress + ":" + remoteHostPort);

		try {

			// 5.IOStream
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			PrintWriter pr = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);

			while (true) {

				// 6. 데이터 읽기
				String data = br.readLine();
				if (data == null) {
				
					consoleLog(" close by client : ");

					return;
				}
				// byte[] buffer = new byte[256];
				// int readbytes = is.read(buffer); // blocked //읽은만큼의 byte수를
				// // 반환
				// if (readbytes <= -1) { // 클라이언트가 연결을 끊었다(정상종료)
				// System.out.println("[server] closed by clients");
				// return;
				// }
				//
				// String data = new String(buffer, 0, readbytes, "utf-8");
				if ("quit".equals(data)) {
					break;
				}

		
				consoleLog(" received : "+data);


				// 7. 데이터 쓰기
				// os.write(data.getBytes("utf-8")); // utf8엔코딩을 바이트로 끄집어 내겠다.
				pr.println(data);
			}

			// if(socket!=null && socket.isClosed()==false){
			// socket.close();
		} catch (SocketException e) {
			System.out.println("[server] 비정상적으로 클라이언트가 연결을 끊었습니다.");
			consoleLog("비정상적으로 클라이언트가 끊겼습니다."+e);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 8. 소켓 닫기
			try {
				if (socket != null && socket.isClosed() == false) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	private void consoleLog(String message){
		System.out.println("echo Server thread#"+getId()+"]"+message);
	}
}
