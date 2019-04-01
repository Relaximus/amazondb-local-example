package com.relaximus.localdynamodb

import com.relaximus.localdynamodb.model.User
import com.relaximus.localdynamodb.model.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class LocalDynamodbApplication {

	static void main(String[] args) {
		SpringApplication.run(LocalDynamodbApplication, args)
	}

    @Bean
    CommandLineRunner commandLineRunner (UserRepository userRepository) {
        new CommandLineRunner() {
            @Override
            void run(String... args) throws Exception {
                userRepository.save(new User(firstName: 'Alex', lastName: 'Xela'))
                println "There is ${userRepository.count()} users in the table"
            }
        }
    }
}
