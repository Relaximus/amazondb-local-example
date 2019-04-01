package com.relaximus.localdynamodb

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile('prod')
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.relaximus.localdynamodb")
class DynamoDBConfigProd {
    @Value('${amazon.dynamodb.accesskey}')
    private String amazonDynamoDBAccessKey
    @Value('${amazon.dynamodb.secretkey}')
    private String amazonDynamoDBSecretKey

    @Bean
    AWSCredentials amazonAWSCredentials() {
        new BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey)
    }

    @Bean
    AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDBClientBuilder.standard()
                .withCredentials(amazonAWSCredentialsProvider())
                .withRegion(Regions.EU_CENTRAL_1)
                .build()
    }
    @Bean
    DynamoDB dynamoDB() {
        new DynamoDB(amazonDynamoDB())
    }

    AWSCredentialsProvider amazonAWSCredentialsProvider() {
        new AWSStaticCredentialsProvider(amazonAWSCredentials())
    }
}
