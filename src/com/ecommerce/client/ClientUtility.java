package com.ecommerce.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientUtility extends Thread {
	private Socket clientSocket;
	private DataOutputStream out;
	private BufferedReader in;
	DataOutputStream output;

	public void startConnection(String ip, int port) {
		try {
			clientSocket = new Socket(ip, port);
			out = new DataOutputStream(clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String sendPayLoad(ClientRequest request) {

		String resp = null;
		try {
			out.writeInt(request.getAction());
			out.writeInt(request.getData().length());
			out.write(request.getData().getBytes(StandardCharsets.UTF_8));
			resp = in.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return resp;

	}
}