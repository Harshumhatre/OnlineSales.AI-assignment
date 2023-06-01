package com.te.assignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MathExpressionEvaluator {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            boolean or=true;
            while (or) {
            	//Don't use "Enter" use space
                System.out.print("Enter a mathematical expression (or 'end' to quit): "); // Enter multiple expressions by entering spaces and type "end" and dont use "Enter" 
                String input = reader.readLine();
                String[] expressions = input.split(" ");  // Split input by space ('space') to get multiple expressions
                if(input.equalsIgnoreCase("end")) {
                	or=false;
                }
                for (int i = 0; i < expressions.length-1; i++) {
                	String expression= expressions[i].trim();
                	double result = evaluateMathExpression(expression);
                	System.out.println(expression +"=>"+ result);
				}

                System.out.println();
            }
        } catch (Exception e) {
        
        }
    }

    private static double evaluateMathExpression(String expression) throws Exception {
        String encodedExpression = java.net.URLEncoder.encode(expression, "UTF-8");
        String url = "http://api.mathjs.org/v4/?expr=" + encodedExpression;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            reader.close();
            return Double.parseDouble(response);
        } else {
        	return responseCode;
        }
    }
}
