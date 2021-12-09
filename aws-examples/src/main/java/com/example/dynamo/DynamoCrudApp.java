package com.example.dynamo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;

public class DynamoCrudApp {

	public static void main(String[] args) {
		String tableName = "jpopbatch";
		String studentId = "112";

		//putItem(tableName);
		
		getItems(tableName, studentId);
	}
	
	private static void putItem(String tableName) {
		
		HashMap<String, AttributeValue> item_values = new HashMap<String, AttributeValue>();
		
		item_values.put("student_id", new AttributeValue("112"));
		item_values.put("student_name", new AttributeValue("abcd"));

		final AmazonDynamoDB ddb = getDynamoClient();

		try {
			ddb.putItem(tableName, item_values);
		} catch (ResourceNotFoundException e) {
			logError("Error: The table \"%s\" can't be found.\n", tableName);
			logError("Be sure that it exists and that you've typed its name correctly!");
		} catch (AmazonServiceException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static void getItems(String tableName, String studentId) {
		try {
			HashMap<String, AttributeValue> key_to_get = new HashMap<String, AttributeValue>();

			key_to_get.put("student_id", new AttributeValue(studentId));

			GetItemRequest request = new GetItemRequest().withKey(key_to_get).withTableName(tableName);
			
			final AmazonDynamoDB ddb = getDynamoClient();

			Map<String, AttributeValue> returned_item = ddb.getItem(request).getItem();
			if (returned_item != null) {
				Set<String> keys = returned_item.keySet();
				for (String key : keys) {
					System.out.format("%s: %s\n", key, returned_item.get(key).toString());
				}
			} else {
				System.out.format("No item found with the key %s!\n", studentId);
			}
		} catch (AmazonServiceException e) {
			System.err.println(e.getErrorMessage());
		}
	}
	
	private static void logInfo(String msg) {
		System.out.println(msg);
	}
	
	private static void logInfo(String msg, String param) {
		System.out.format(msg,param);
	}
	
	private static void logError(String msg, String param) {
		System.err.format(msg,param);
	}
	
	private static void logError(String msg) {
		System.err.println(msg);
	}
	
	
	private static AmazonDynamoDB  getDynamoClient() {
		return AmazonDynamoDBClientBuilder.standard().withRegion(Regions.AP_SOUTH_1)
		.withCredentials(getAwsCredentials()).build();
	}
	
	private static AWSCredentialsProvider getAwsCredentials() {
		String accessKey = "key";
		String secretKey = "secret";
		AWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
		return new AWSStaticCredentialsProvider(creds);
	}
}
