package com.search;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ElasticsearchApiApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ElasticsearchApiApplication.class, args);
		
		ElasticsearchApiApplication app = new ElasticsearchApiApplication();
		app.curlTest();
	}

	public void curlTest() throws Exception{
		
		String strUrl = "http://112.216.66.194:9200/books/book/2";
		URL url = new URL(strUrl);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setDoOutput(true);
	    conn.setDoInput(true);
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	    conn.setRequestProperty("Accept", "application/json");

	    JSONObject obj = new JSONObject();
	    obj.put("transaction_type", "01");
	    obj.put("amount", "101");
	    obj.put("cardholder_name", "PaulTest33");
	    obj.put("transarmor_token", "3000332");
	    obj.put("cc_expiry", "001611");
	    obj.put("credit_card_type", "Visa333");

	    String input = obj.toString();	    
	    System.out.println(input);

	    OutputStream os = conn.getOutputStream();
	    os.write(input.getBytes());
	    os.flush();
	    
	    System.out.println("Response Code : " + conn.getResponseCode() + ", " + conn.getResponseMessage());
	    if (!(conn.getResponseCode() == HttpURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpURLConnection.HTTP_OK)) {
	    	throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode() + conn.getResponseMessage());
	    }
	    
	    conn.disconnect();
	    os.close();
	}

}