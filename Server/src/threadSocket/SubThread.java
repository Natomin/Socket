package threadSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SubThread extends Thread {
	private Socket threadSocket;
	private int closeFlag = 0;

	public void setSocket(Socket soc) {
		threadSocket = soc;
	}

	public void  setFlag(int flag){
		closeFlag = flag;
	}

	public void run() {
		// 出力のためのWriterを生成
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(threadSocket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

		// write処理
		while (true) {
			String input = null;
			try {
				input = keyboard.readLine();
				System.out.println("Input>" + input);
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.println(input);
			if (input.equals("¥¥q")) {
				break;
			}
		}
		try {
			threadSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
