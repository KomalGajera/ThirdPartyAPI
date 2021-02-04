package com.demo.usefulldemo.controller;
import java.util.Map;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public interface GameInterface {

	default HttpResponse<JsonNode> getMethodCallSync(String url, Map<String, String> headers,Map<String, Object> parameters) throws UnirestException {
		
		return Unirest.get(url).headers(headers).queryString(parameters).asJson();
	}
	
}
