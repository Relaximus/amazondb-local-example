[Amazon DynamoDb local tutorial](https://www.relaximus.com/post/spring-amazon-dynamodb-local-guide/) repository.

# DynamoDB example
In this example [Spring Data DynamoDB](https://derjust.github.io/spring-data-dynamodb/) 
and [DynamoDB Local](https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.Maven.html) 
are being used for showing case how all that could be configured and run.

There are two options to run code:
1. Locally on your computer
2. Using the aws account (key/secret needed)

## Local

Run 
```cmd
gradle bootRun
```

In the console you will find the message:
```cmd
There is 1 users in the table
```

## In real dynamodb table
This example shows the easines of operating with tables by means of spring data dynamodb.
```cmd
gradle bootRun -Dspring.profiles.active=prod
```
```cmd
There is X users in the table
```
x - will be incremented every time you start the application.
