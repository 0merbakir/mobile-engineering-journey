package com.example.roadmaps.day2

import org.junit.Test
import java.util.UUID

/**
 * This is your personal practice area for Day 2.
 * Use the concepts from `day2_excs.kt` to build your own classes and functions here.
 *
 * To run your code, click the green play button (▶) next to the `myPractice` function.
 */

abstract class User(val id: String) {
    abstract val name: String
    fun getProfileInfo(): String {
        return "User Profile - Name: $name, ID: $id"
}
    class RegisteredUser(override val name: String, val email: String) : User(UUID.randomUUID().toString())


class Day2PracticeTest {

    @Test
    fun myPractice() {

        println("Day 2 Practice Area!")


    }
}
}
