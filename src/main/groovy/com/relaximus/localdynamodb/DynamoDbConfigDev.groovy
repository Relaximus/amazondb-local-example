package com.relaximus.localdynamodb


import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.document.DynamoDB
import com.amazonaws.services.dynamodbv2.local.main.ServerRunner
import com.amazonaws.services.dynamodbv2.local.server.DynamoDBProxyServer
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment

@Profile("!prod")
@Configuration
@EnableDynamoDBRepositories(basePackages = "com.relaximus.localdynamodb")
class DynamoDbConfigDev {
    @Value('${amazon.dynamodb.endpoint}')
    private String amazonDynamoDBEndpoint
    @Value('${amazon.dynamodb.region}')
    private String amazonDynamoDBRegion

    @Bean(destroyMethod = "shutdown")
    AmazonDynamoDB amazonDynamoDB(Environment env) {
        System.setProperty("sqlite4java.library.path", "build/libs")

        final String[] localArgs = ["-inMemory"].toArray()
        DynamoDBProxyServer server = ServerRunner.createServerFromCommandLineArgs(localArgs)
        server.start()

        def auth = new BasicAWSCredentials("fakeKey", "fakeSecret")
        AmazonDynamoDBClientBuilder
                .standard().withCredentials(new AWSStaticCredentialsProvider(auth))
                .withEndpointConfiguration(
                new AwsClientBuilder
                        .EndpointConfiguration(amazonDynamoDBEndpoint, amazonDynamoDBRegion))
                .build()
    }

    @Bean
    DynamoDB dynamoDB() {
        new DynamoDB(amazonDynamoDB())
    }
}
