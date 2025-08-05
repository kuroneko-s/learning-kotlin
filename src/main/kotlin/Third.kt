package com.choidh

import kotlin.random.Random as r

val _r = r.nextInt()

class Sample(
    name:String,
    val innerName: String
) {
    val greeting = "Hello $name"

    fun getName(): String {
        return "name is $innerName"
    }

    fun printName(): Unit {
        println("inner print name is $greeting")
    }
}

fun main() {
    val sample1 = Sample("Kotlin", "Sub Kotlin")
    println(sample1)
    println(sample1.greeting)
    println(sample1.getName())
    println(sample1.innerName)
    sample1.printName()
    println(_r)
    println("Hi".doubleQuote())
}