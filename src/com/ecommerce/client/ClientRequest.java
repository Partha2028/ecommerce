package com.ecommerce.client;

public class ClientRequest {
	private int action;
	private String data;

	public ClientRequest(int action, String data) {
		this.action = action;
		this.data = data;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
