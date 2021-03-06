/*
 * Copyright 2012-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.DescribeTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.amazonaws.services.dynamodbv2.util.Tables;

/**
 * This sample demonstrates how to perform a few simple operations with the
 * Amazon DynamoDB service.
 */
public class AmazonDynamoDBSample {

    /*
     * Before running the code:
     *      Fill in your AWS access credentials in the provided credentials
     *      file template, and be sure to move the file to the default location
     *      (C:\\Users\\Dillon\\.aws\\credentials) where the sample code will load the
     *      credentials from.
     *      https://console.aws.amazon.com/iam/home?#security_credential
     *
     * WARNING:
     *      To avoid accidental leakage of your credentials, DO NOT keep
     *      the credentials file in your source directory.
     */

    static AmazonDynamoDBClient dynamoDB;

    /**
     * The only information needed to create a client are security credentials
     * consisting of the AWS Access Key ID and Secret Access Key. All other
     * configuration, such as the service endpoints, are performed
     * automatically. Client parameters, such as proxies, can be specified in an
     * optional ClientConfiguration object when constructing a client.
     *
     * @see com.amazonaws.auth.BasicAWSCredentials
     * @see com.amazonaws.auth.ProfilesConfigFile
     * @see com.amazonaws.ClientConfiguration
     */
    private static void init() throws Exception {
        /*
         * The ProfileCredentialsProvider will return your [default]
         * credential profile by reading from the credentials file located at
         * (C:\\Users\\Dillon\\.aws\\credentials).
         */
        AWSCredentials credentials = null;
        try {
            credentials = new ProfileCredentialsProvider("default").getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (C:\\Users\\Dillon\\.aws\\credentials), and is in valid format.",
                    e);
        }
        dynamoDB = new AmazonDynamoDBClient(credentials);
        Region usWest2 = Region.getRegion(Regions.US_WEST_1);
        dynamoDB.setRegion(usWest2);
    }

    public static void main(String[] args) throws Exception {
        init();

        try {
            String tableName = "Accounts";

            // Create table if it does not exist yet
            if (Tables.doesTableExist(dynamoDB, tableName)) {
                System.out.println("Table " + tableName + " is already ACTIVE");
            } else {
            	createTable(tableName);
                // Create a table with a primary hash key named 'name', which holds a string
//                CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
//                    .withKeySchema(new KeySchemaElement().withAttributeName("name").withKeyType(KeyType.HASH))
//                    .withAttributeDefinitions(new AttributeDefinition().withAttributeName("name").withAttributeType(ScalarAttributeType.S))
//                    .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
//                    TableDescription createdTableDescription = dynamoDB.createTable(createTableRequest).getTableDescription();
//                System.out.println("Created Table: " + createdTableDescription);
                
                
//                CreateTableRequest createTableRequest = new CreateTableRequest().withTableName(tableName)
//                        .withKeySchema(new KeySchemaElement().withAttributeName("accountId").withKeyType(KeyType.HASH))
//                        .withAttributeDefinitions(new AttributeDefinition().withAttributeName("accountId").withAttributeType(ScalarAttributeType.N))
//                        .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
//                        TableDescription createdTableDescription = dynamoDB.createTable(createTableRequest).getTableDescription();
//                        System.out.println("Created Table: " + createdTableDescription);
                

                // Wait for it to become active
                System.out.println("Waiting for " + tableName + " to become ACTIVE...");
                Tables.waitForTableToBecomeActive(dynamoDB, tableName);
            }

            // Describe our new table
//            DescribeTableRequest describeTableRequest = new DescribeTableRequest().withTableName(tableName);
//            TableDescription tableDescription = dynamoDB.describeTable(describeTableRequest).getTable();
//            System.out.println("Table Description: " + tableDescription);
//
//            // Add an item
//            Map<String, AttributeValue> item = newItem(10555334, "Dillon Swanson", 10000, "Good", tableName);
//
//            // Add another item
//            item = newItem(10253941, "Chelsey", -25.32, "Overdrawn", tableName);

            // Scan for particular accountId
            getAccountInfo(tableName, "10555334");
            
//            HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
//            Condition condition = new Condition()
//                .withComparisonOperator(ComparisonOperator.EQ.toString())
//                .withAttributeValueList(new AttributeValue().withN("10555334"));
//            scanFilter.put("accountId", condition);
//            ScanRequest scanRequest = new ScanRequest(tableName).withScanFilter(scanFilter);
//            ScanResult scanResult = dynamoDB.scan(scanRequest);
//            System.out.println("Result: " + scanResult);
            
            // Scan items for movies with a year attribute greater than 1985
//            HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
//            Condition condition = new Condition()
//                .withComparisonOperator(ComparisonOperator.GT.toString())
//                .withAttributeValueList(new AttributeValue().withN("1985"));
//            scanFilter.put("year", condition);
//            ScanRequest scanRequest = new ScanRequest(tableName).withScanFilter(scanFilter);
//            ScanResult scanResult = dynamoDB.scan(scanRequest);
//            System.out.println("Result: " + scanResult);

        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it "
                    + "to AWS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with AWS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
    }
    
    private static void getAccountInfo(String tableName, String accountID) {
        HashMap<String, Condition> scanFilter = new HashMap<String, Condition>();
        Condition condition = new Condition()
            .withComparisonOperator(ComparisonOperator.EQ.toString())
            .withAttributeValueList(new AttributeValue().withN(accountID));
        scanFilter.put("accountId", condition);
        ScanRequest scanRequest = new ScanRequest(tableName).withScanFilter(scanFilter);
        ScanResult scanResult = dynamoDB.scan(scanRequest);
        System.out.println("Result: " + scanResult);
    }
    
    private static void newItem(int accountId, String name, double balance, String status, String tableName) {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("accountId", new AttributeValue().withN(Integer.toString(accountId)));
        item.put("name", new AttributeValue(name));
        item.put("currentBalance", new AttributeValue().withN(Double.toString(balance)));
        item.put("accountStatus", new AttributeValue(status));

        PutItemRequest putItemRequest = new PutItemRequest(tableName, item);
        PutItemResult putItemResult = dynamoDB.putItem(putItemRequest);
        System.out.println("Result: " + putItemResult);
    }
    
    private static void createTable(String tableName){
    	 CreateTableRequest createTableRequest = new CreateTableRequest().withTableName("Accounts")
                 .withKeySchema(new KeySchemaElement().withAttributeName("accountId").withKeyType(KeyType.HASH))
                 .withAttributeDefinitions(new AttributeDefinition().withAttributeName("accountId").withAttributeType(ScalarAttributeType.N))
                 .withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1L).withWriteCapacityUnits(1L));
                 TableDescription createdTableDescription = dynamoDB.createTable(createTableRequest).getTableDescription();
                 System.out.println("Created Table: " + createdTableDescription);
    }
    
//    private static Map<String, AttributeValue> newItem(String name, int year, String rating, String... fans) {
//        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
//        item.put("name", new AttributeValue(name));
//        item.put("year", new AttributeValue().withN(Integer.toString(year)));
//        item.put("rating", new AttributeValue(rating));
//        item.put("fans", new AttributeValue().withSS(fans));
//
//        return item;
//    }
}