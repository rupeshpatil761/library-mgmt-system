package com.book.service.model.dynamo;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "book_dynamo")
public class Book {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey //only supported for String type
    private String id;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private String author;
    @DynamoDBAttribute
    private String category;
}
