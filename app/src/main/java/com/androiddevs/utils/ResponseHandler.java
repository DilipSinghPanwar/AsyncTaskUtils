package com.androiddevs.utils;

public interface ResponseHandler {
	void handleResponse(String response);
	void handleError(String error);
}
