package threadSocket2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Main {

	public static void main(String[] args) throws IOException {
		// ソケット生成
		Socket socket = new Socket("www.ricoh.co.jp", 80);

		Sub subthread = new Sub();
		subthread.start();
		subthread.setSocket(socket);

		// 入力のためのReaderを生成
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		// read処理
		while (true) {
			// 改行までの一列を読み込む
			String line = reader.readLine();
			// ¥qが打ち込まれたら終了
//			if (line.equals("¥¥q")) {
//				break;
//			}
			System.out.println("server>" + line);
		}
//		socket.close();

	}

}
