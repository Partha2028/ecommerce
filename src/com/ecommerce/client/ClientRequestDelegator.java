package com.ecommerce.client;

import java.util.concurrent.Callable;

public class ClientRequestDelegator implements Callable<String> {

	public ClientRequestDelegator(ClientRequest request) {
		super();
		this.request = request;
	}

	private ClientRequest request;

	@Override
	public String call() throws Exception {
		ClientUtility client = new ClientUtility();
		client.startConnection("127.0.0.1", 7474);
		return client.sendPayLoad(request);

	}

}
