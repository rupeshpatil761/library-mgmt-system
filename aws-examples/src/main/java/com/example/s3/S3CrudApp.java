package com.example.s3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class S3CrudApp {

	public static void main(String[] args) {
		
		String bucketName = "jpopbatch8bucket";
		//String keyName = "index.html";
		
		//createBucket(bucketName);
		
		putObject(bucketName);
	}
	
	private static void logInfo(String msg) {
		System.out.println(msg);
	}
	
	private static void logInfo(String msg, String param) {
		System.out.format(msg,param);
	}
	
	public static Bucket createBucket(String bucket_name) {
		final AmazonS3 s3 = getS3Client();
		Bucket b = null;
		if (s3.doesBucketExistV2(bucket_name)) {
			logInfo("Bucket %s already exists.\n", bucket_name);
		} else {
			try {
				b = s3.createBucket(bucket_name);
			} catch (AmazonS3Exception e) {
				System.err.println(e.getErrorMessage());
			}
		}
		return b;
	}
	
	public static void putObject(String bucketName) {
        String file_path = "C:\\Users\\Rupesh_Patil\\Desktop\\index.html";
        String keyName = Paths.get(file_path).getFileName().toString();

        System.out.format("Uploading %s to S3 bucket %s and key name %s...\n", file_path, bucketName,keyName);
        final AmazonS3 s3 = getS3Client();
        try {
            s3.putObject(bucketName, keyName, new File(file_path));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
        logInfo("Uploaded Successfully!");
	}
	
	private static void getObject(String bucketName, String keyName) {

        System.out.format("Downloading %s from S3 bucket %s...\n", keyName, bucketName);
        final AmazonS3 s3 = getS3Client();
        try {
            S3Object o = s3.getObject(bucketName, keyName);
            S3ObjectInputStream s3is = o.getObjectContent();
            FileOutputStream fos = new FileOutputStream(new File(keyName));
            byte[] read_buf = new byte[1024];
            int read_len = 0;
            while ((read_len = s3is.read(read_buf)) > 0) {
                fos.write(read_buf, 0, read_len);
            }
            s3is.close();
            fos.close();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Done!");
	}

	public static Bucket getBucket(String bucket_name) {
        final AmazonS3 s3 = getS3Client();
        Bucket named_bucket = null;
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucket_name)) {
                named_bucket = b;
            }
        }
        return named_bucket;
    }
	
	private static AmazonS3 getS3Client() {
		return AmazonS3ClientBuilder.standard().withRegion(Regions.AP_SOUTH_1) //Regions.AP_SOUTH_1
		.withCredentials(getAwsCredentials()).build();
	}
	
	private static AWSCredentialsProvider getAwsCredentials() {
		String accessKey = "key";
		String secretKey = "secret";
		AWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
		return new AWSStaticCredentialsProvider(creds);
	}
}
