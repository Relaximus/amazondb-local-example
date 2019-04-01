package com.relaximus.localdynamodb.model

import org.socialsignin.spring.data.dynamodb.repository.EnableScan
import org.springframework.data.repository.CrudRepository

@EnableScan
interface UserRepository extends CrudRepository<User, String> {}
