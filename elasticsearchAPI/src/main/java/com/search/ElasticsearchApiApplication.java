package com.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
		
		Process process = null;
		BufferedReader br = null;
		process = Runtime.getRuntime().exec("curl -XGET http://112.216.66.194:9200/books/book/1");
		
		br = new BufferedReader(new InputStreamReader(process.getErrorStream(),"UTF-8"));
		
		String line = null;
		while ((line = br.readLine())!= null) {
			System.out.println(line);
		}

		br = null;
		br = new BufferedReader(new InputStreamReader(process.getInputStream()));

		System.out.println("\n ## RESULT: ");
		line = null;
		while ((line = br.readLine())!= null) {
			System.out.println(line);
		}
		
		process.waitFor();
	}

}