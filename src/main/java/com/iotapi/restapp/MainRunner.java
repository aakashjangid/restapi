package com.iotapi.restapp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;

public class MainRunner {

	/*public static void main(String[] args) throws InterruptedException {
		System.out.println(executeathread());
	
	}

	
	public static String executeathread(){
		
		Executors.newCachedThreadPool().execute(new Runnable() {
		    @Override
		    public void run() {
		        try {
					Thread.sleep(10000);
					System.out.println("-----------");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		return "let him sleep while i work";
	}
	*/
	
	public void sendPostRequest(String requestUrl) {
	    try {
	        URL url = new URL(requestUrl);
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

	        connection.setDoInput(true);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("POST");
//	        connection.setRequestProperty("Accept", "application/json");
//	        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	        
	        connection.getInputStream();
	    } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	    }
	}
}
