package com.ecommerce.client.use;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.ecommerce.client.ClientRequest;
import com.ecommerce.client.ClientRequestDelegator;

public class ShoppingClient {

	public static void main(String args[]) {

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<ClientRequestDelegator> clientList = prepareClients();
		try {
			List<Future<String>> result = executor.invokeAll(clientList);
			processResponse(result);
			executor.awaitTermination(5, TimeUnit.SECONDS);
			executor.shutdown();
			System.out.print("All thread are processed!!");
		} catch (Exception e) {
		}
	}

	private static void processResponse(List<Future<String>> result) {
		for (Future<String> future : result) {
			try {
				System.out.println("Is request processed ?:" + future.isDone());
				System.out.println("response::" + future.get());
			} catch (CancellationException ce) {
				ce.printStackTrace();
			} catch (ExecutionException ee) {
				ee.printStackTrace();
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
			}
		}
	}

	private static List<ClientRequestDelegator> prepareClients() {
		List<ClientRequestDelegator> clientList = new ArrayList<>();
		clientList.add(new ClientRequestDelegator(new ClientRequest(1, "getAllOrders")));
		clientList.add(new ClientRequestDelegator(new ClientRequest(2, "2")));
		clientList.add(new ClientRequestDelegator(new ClientRequest(3, "getDetails")));
		clientList.add(new ClientRequestDelegator(new ClientRequest(1, "getAllOrders")));
		clientList.add(new ClientRequestDelegator(new ClientRequest(2, "2")));
		clientList.add(new ClientRequestDelegator(new ClientRequest(3, "getDetails")));
		return clientList;
	}
}