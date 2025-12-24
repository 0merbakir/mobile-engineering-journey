package com.example.roadmaps


object Day1Exercises {

    fun runExercises() {
        // Kotlin Day 1 alıştırmaları
        val name = "Ömer"
        val age = 24
        println("Name: $name, Age: $age")

        val category = if (age > 20) "Adult" else "Young"
        println(category)

        // Liste örneği
        val fruits = listOf("Apple", "Banana", "Cherry")
        fruits.forEach { println(it) }

        // Map örneği
        val map = mapOf("A" to 1, "B" to 2)
        println(map["A"])

        // New exercise
        val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val evenSum = sumOfEvens(numbers)
        println("Sum of even numbers: $evenSum")
    }

    // Function to sum even numbers in a list
    fun sumOfEvens(numbers: List<Int>): Int {
        var sum = 0
        for (number in numbers) {
            if (number % 2 == 0) {
                sum += number
            }
        }
        return sum
    }
}