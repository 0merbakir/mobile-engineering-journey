package com.example.roadmaps

import org.junit.Test


class Day1PracticeTest {

    @Test
    fun myPractice() {
        println("\n* Functions & Control Flow *")
        fun greet(name: String): String = "Hello, $name!" // Single-expression function
        println(greet("Mobile Engineer"))

        fun adder1(num1 : Int, num2 : Int) : Int{
            return num1 + num2
        }
        println(adder1(1,2))

        fun adder2(num1 : Int, num2 : Int) : Int = num1 + num2
        println(adder2(1,2))

        }

    }

