package com.example.roadmaps

import com.example.roadmaps.Day1Exercises.User
import org.junit.Test


class Day1PracticeTest {

    @Test
    fun myPractice() {
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

        }



