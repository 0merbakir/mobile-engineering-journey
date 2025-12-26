package com.example.roadmaps.day2

import java.util.UUID

/**
 * ======================================================================================
 * DAY 2 – OOP & KOTLIN POWER FEATURES
 * ======================================================================================
 * This file demonstrates key OOP concepts and powerful Kotlin features using a simple
 * user authentication model.
 */

//--------------------------------------------------------------------------------------
// 1. ABSTRACT CLASS & INHERITANCE
//--------------------------------------------------------------------------------------
/**
 * An `abstract class` cannot be instantiated on its own. It serves as a blueprint for
 * other classes to inherit from. It can have both abstract (must be implemented by
 * subclasses) and concrete (default implementation) members.
 *
 * Interview Focus:
 * - Use an abstract class when you have a set of classes that share common functionality
 *   and state. It establishes(tesis etmek) a strong "is-a" relationship.
 * - Example: A `GuestUser` and a `RegisteredUser` are both types of `User`.
 */
abstract class User(val id: String) {
    abstract val name: String // subclasses must provide a name

    fun getProfileInfo(): String {
        return "User Profile - Name: $name, ID: $id"
    }
}

/**
 * `class RegisteredUser(...) : User(...)` demonstrates inheritance. `RegisteredUser`
 * inherits the `id` property and the `getProfileInfo()` function from the `User` class.
 * It MUST override the abstract `name` property.
 */
class RegisteredUser(override val name: String, val email: String) : User(UUID.randomUUID().toString())

//--------------------------------------------------------------------------------------
// 2. INTERFACE
//--------------------------------------------------------------------------------------
/**
 * An `interface` defines a contract that other classes must adhere(sadık olmak) to. It can contain
 * abstract method declarations and also methods with default implementations.
 *
 * Interview Focus:
 * - Interface vs. Abstract Class: A class can implement multiple interfaces (supporting
 *   composition), but can only inherit from one abstract class. Use an interface to define
 *   a "can-do" relationship (e.g., a class `can` authenticate).
 */
interface Authenticator {
    fun authenticate(user: RegisteredUser): AuthResult
}

//--------------------------------------------------------------------------------------
// 3. SEALED CLASS
//--------------------------------------------------------------------------------------
/**
 * `sealed class` is one of Kotlin's most powerful features. It restricts(sınırlamak) the class
 * hierarchy, meaning all direct subclasses must be declared in the same file. This is
 * extremely useful for representing a fixed set of states or outcomes.
 *
 * Interview Focus:
 * - Why it's important: When used with a `when` expression, the compiler can check for
 *   completeness. If you forget to handle one of the sealed subclasses, the compiler
 *   will give you an error. This completely eliminates bugs related to unhandled states.
 * - It's the perfect tool for modeling API responses (Success, Error, Loading), events,
 *   and any other restricted set of possibilities.
 */
sealed class AuthResult {
    data class Success(val user: RegisteredUser) : AuthResult()
    data class Failure(val reason: String)  : AuthResult()
    object InProgress : AuthResult() // Represents a state, so it can be an object
}

//--------------------------------------------------------------------------------------
// 4. EXTENSION FUNCTION
//--------------------------------------------------------------------------------------
/**
 * Extension functions allow you to add new functions to a class without modifying its
 * source code. This is incredibly useful for enhancing(geliştirmek) existing libraries or SDKs.
 *
 * Interview Focus:
 * - How it works: The compiler statically resolves these calls. It's just syntactic sugar
 *   for calling a static utility function, e.g., `log(user)` becomes `user.log()`.
 * - It does not actually modify the original class and cannot access its private members.
 */
fun RegisteredUser.logLoginAttempt() {
    println("Login attempt for user: '$name' with email: '$email'")
}

//--------------------------------------------------------------------------------------
// DAY 2 EXERCISES OBJECT
//--------------------------------------------------------------------------------------
object Day2Exercises {

    class SimpleAuthenticator : Authenticator {
        override fun authenticate(user: RegisteredUser): AuthResult {
            user.logLoginAttempt() // Calling the extension function!
            return if (user.email.contains("@")) {
                AuthResult.Success(user)
            } else {
                AuthResult.Failure("Invalid email address.")
            }
        }
    }

    fun runExercises() {
        println("--- Day 2: OOP & Kotlin Power ---")

        val user = RegisteredUser("Zeynep", "zeynep@example.com")
        val invalidUser = RegisteredUser("Guest", "guest-no-email")
        val authenticator = SimpleAuthenticator()

        // --- Demonstrate the auth flow ---
        println("\nAuthenticating valid user...")
        val result1 = authenticator.authenticate(user)
        processAuthResult(result1)

        println("\nAuthenticating invalid user...")
        val result2 = authenticator.authenticate(invalidUser)
        processAuthResult(result2)
    }

    private fun processAuthResult(result: AuthResult) {
        when (result) {
            is AuthResult.Success -> {
                println("Authentication successful!")
                println(result.user.getProfileInfo()) // Calling method from abstract class
            }
            is AuthResult.Failure -> {
                println("Authentication failed: ${result.reason}")
            }
            is AuthResult.InProgress -> {
                println("Authentication is currently in progress...")
            }
        } // Because AuthResult is sealed, we don't need an 'else' branch!
    }
}
