package threadSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainThread {

	public static void main(String[] args) throws IOException {
		// ソケットを生成＆待機
		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress("localhost", 8080));
		Socket socket = server.accept();

		SubThread subthread = new SubThread();
		subthread.setSocket(socket);//runをstartさせる前にソケットを渡す
		subthread.start();

		// 入力のためのReaderを生成
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// read処理
		while (true) {
			// 改行までの一列を読み込む
			String line = reader.readLine();
			// ¥qが打ち込まれたら終了
			if (line.equals("¥¥q")) {
				break;
			}
			System.out.println("client>" + line);
		}
		socket.close();
		server.close();

	}

}
