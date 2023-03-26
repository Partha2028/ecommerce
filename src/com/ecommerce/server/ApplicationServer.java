package com.ecommerce.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ApplicationServer {

	private ServerSocket serverSocket;

	public void start(int port, int noOfClients) {
		try {
			serverSocket = new ServerSocket(port);
			int clientCount = 0;
			while (clientCount <= noOfClients) {
				new ClientHandler(serverSocket.accept()).start();
				++clientCount;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ApplicationServer server = new ApplicationServer();
		server.start(7474, 10);
	}
}