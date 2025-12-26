package com.example.roadmaps

/**
 * ======================================================================================
 * KOTLIN FUNDAMENTALS FOR INTERVIEWS
 * ======================================================================================
 *
 * This file contains explanations and examples of core Kotlin concepts that are
 * frequently asked about in technical interviews.
 */

//--------------------------------------------------------------------------------------
// 1. OBJECTS & SINGLETONS
//--------------------------------------------------------------------------------------
/**
 * `object` is a keyword used to declare a singleton class. A singleton ensures that only
 * one instance of a class is ever created.
 *
 * Interview Focus:
 * - Why use a singleton? For objects that are expensive to create or that manage a shared
 *   state, like a database connection, a network client, or a utility class with no state.
 * - How it works: The object is created lazily when it's first accessed. It's thread-safe.
 * - Example: `Day1Exercises` is a singleton. We can call its methods directly without
 *   creating an instance, like `Day1Exercises.runExercises()`.
 */
object Day1Exercises {

    //----------------------------------------------------------------------------------
    // 2. DATA CLASSES
    //----------------------------------------------------------------------------------
    /**
     * `data class` is a concise way to create classes that are primarily used to hold data. (concise : kısa, öz)
     *
     * Interview Focus:
     * - What the compiler generates automatically: `equals()`, `hashCode()`, `toString()`,
     *   `copy()`, and `componentN()` functions (for destructuring declarations).
     * - Key benefit: It eliminates a huge amount of boilerplate code, making the code
     *   cleaner and less error-prone. It promotes immutability if you use `val`. (promote = teşvik etmek, immutability = değiştirilmezlik)
     * - Primary constructor rule: Must have at least one parameter. All primary constructor
     *   parameters must be marked as `val` or `var`.  (boilerplate = tekrarlayan)
     */
    data class User(val name: String, val id: Int)

    /**
     * This function covers the basics of the Kotlin language.
     */
    fun languageBasics() {
        println("--- Language Basics Exercises ---")

        //------------------------------------------------------------------------------
        // 3. VARIABLES: val vs var
        //------------------------------------------------------------------------------
        /**
         * `val` (immutable reference): You can assign a value to it once. It's like `final` in Java.
         * `var` (mutable reference): You can reassign the value later on.
         *
         * Interview Focus:
         * - Best Practice: Always prefer `val` over `var`. This promotes immutability, which makes
         *   your code safer, more predictable, and easier to reason about, especially in
         *   multi-threaded environments.
         */
        println("\n* val vs var *")
        val immutableVar = "I can't be changed."
        var mutableVar = "I can be reassigned."
        mutableVar = "See?"
        println(immutableVar)
        println("Mutable var: $mutableVar")

        //------------------------------------------------------------------------------
        // 4. NULL SAFETY
        //------------------------------------------------------------------------------
        /**
         * Kotlin's type system is designed to eliminate the `NullPointerException`.
         *
         * Interview Focus:
         * - `?` (Nullable type): Declares that a variable can hold a `null` value. `String?`
         * - `?.` (Safe Call): The most common way to handle nulls. Executes the call only if the
         *   variable is not null; otherwise, it returns `null`.
         * - `?:` (Elvis Operator): Takes a default value to use if the expression on the left is null.
         * - `!!` (Non-null Assertion): The "hammer". Converts any value to a non-null type and (assertion = doğrulama)
         *   throws a `NullPointerException` if the value is null. Use it sparingly, only when you (sparingly = ihtiyatlı)
         *   are 100% certain the value is not null.
         * - `let` Scope Function: `nullableString?.let { ... }` is a common, elegant idiom for
         *   executing a block of code only if a value is not null.
         */
        println("\n* Null Safety *")
        var nullableString: String? = "I might be null."
        //nullableString = null // uncomment to see the effects

        println("Length (safe call): ${nullableString?.length}") // Returns null if nullableString is null

        nullableString?.let { // Executes only if not null
            println("let block: '$it' is not null")
        }

        val lengthOrDefault = nullableString?.length ?: 0 // Elvis operator
        println("Length or default: $lengthOrDefault")

        //------------------------------------------------------------------------------
        // 5. FUNCTIONS & CONTROL FLOW (when/if)
        //------------------------------------------------------------------------------
        /**
         * Functions are first-class citizens in Kotlin. `if` and `when` can be used not just
         * as statements, but as expressions that return a value.
         *
         * Interview Focus:
         * - Expression-based control flow: Using `if` and `when` to directly assign a value to a
         *   variable is more concise and readable than the traditional statement-based approach.
         * - `when`: A more powerful and readable replacement for the traditional `switch` statement.
         *   It can be used with a wide range of types and conditions.
         */
        println("\n* Functions & Control Flow *")
        fun greet(name: String): String = "Hello, $name!" // Single-expression function
        println(greet("Mobile Engineer"))

        val number = 2
        val numberAsText = when (number) {
            1 -> "One"
            2 -> "Two"
            else -> "Other"
        }
        println("The number $number is '$numberAsText'")

        //------------------------------------------------------------------------------
        // 6. COLLECTIONS (List, Map)
        //------------------------------------------------------------------------------
        /**
         * Kotlin provides a rich API for collections, with a key distinction( ayrım, fark) between mutable
         * and immutable collections.
         *
         * Interview Focus:
         * - Immutable vs. Mutable: `listOf()` and `mapOf()` create read-only collections. To add or
         *   remove elements, you must use `mutableListOf()` or `mutableMapOf()`.
         * - Functional API: Highlight the powerful extension functions available like `filter`,
         *   `map`, `forEach`, `first`, `sum`, etc. These allow for a declarative, functional style
         *   of programming that is often more concise and readable than imperative loops.
         */
        println("\n* Collections *")
        val fruits = listOf("Apple", "Banana", "Cherry") // Immutable list
        println("Fruits: $fruits")
        println("First fruit: ${fruits.first()}")

        val userMap = mapOf(1 to User("Zeynep", 101), 2 to User("Mehmet", 102))
        println("User map: $userMap")
    }

    /**
     * A function using the collection API to demonstrate functional style.
     */
    fun sumOfEvens(numbers: List<Int>): Int {
        return numbers.filter { it % 2 == 0 }.sum()
    }

    /**
     * Main entry point to run all exercises.
     */
    fun runExercises() {
        // Run today's focus exercises
        languageBasics()

        // Run previous exercises
        println("\n--- Previous Exercises ---")
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val evenSum = sumOfEvens(numbers)
        println("Sum of even numbers: $evenSum")
    }
}
