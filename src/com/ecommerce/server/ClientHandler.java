package com.ecommerce.server;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.OrderServiceImpl;

public class ClientHandler extends Thread {
	private Socket clientSocket;
	private PrintWriter out;
	DataInputStream in;
	private OrderService service = new OrderServiceImpl();

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
	}

	public void run() {
		try {
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
			handleClientRequet();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handleClientRequet() throws IOException {
		int action = in.readInt();
		int length = in.readInt();
		if (action == 1) {
			getAllOrderRequest(length);
		} else if (action == 2) {
			getOrderDetails(length);
		} else if (action == 3) {
			placeOrder();
		}
	}

	private void placeOrder() {
//write business logic
		out.println("Order placed!!");
	}

	private void getAllOrderRequest(int length) throws IOException {
		byte[] messageByte = new byte[length];
		boolean end = false;
		StringBuilder dataString = new StringBuilder(length);
		int totalBytesRead = 0;
		while (!end) {
			int currentBytesRead = in.read(messageByte);
			totalBytesRead = currentBytesRead + totalBytesRead;
			if (totalBytesRead <= length) {
				dataString.append(new String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
			} else {
				dataString.append(
						new String(messageByte, 0, length - totalBytesRead + currentBytesRead, StandardCharsets.UTF_8));
			}
			System.out.println("Data read:" + dataString.toString());

			if (dataString.length() >= length) {
				end = true;
			}
		}
		List<Order> orders = service.getAllOrders();
		out.println(orders);

	}

	public void getOrderDetails(int length) {

		OrderService service = new OrderServiceImpl();
		byte[] messageByte = new byte[length];
		boolean end = false;
		StringBuilder dataString = new StringBuilder(length);
		int totalBytesRead = 0;
		while (!end) {
			int currentBytesRead;
			try {
				currentBytesRead = in.read(messageByte);
				totalBytesRead = currentBytesRead + totalBytesRead;
				if (totalBytesRead <= length) {
					dataString.append(new String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
				} else {
					dataString.append(new String(messageByte, 0, length - totalBytesRead + currentBytesRead,
							StandardCharsets.UTF_8));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (dataString.length() >= length) {
				end = true;
			}
		}
		try {
			out.println(service.getOrderDetails(Long.parseLong(dataString.toString())));
		} catch (NumberFormatException e) {
			out.println("Provide an valid Numberic Order id");
		}
	}
}