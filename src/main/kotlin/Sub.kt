package com.choidh

const val random = 35

fun main() {
    println(random in 0..100)
    println(0 <= random && random <= 100)
    println("k" in "kotlin")
    println("s" in "Special")
    println(notDigit('a'))
    println(notDigit('5'))
    println(notDigit('A'))
    println(notDigit('Z'))
    println("az" in "aa"..<"az") // semi open range
    println("ba" in "aa".."az")
}

fun notDigit(ch: Char) = ch !in '0'..'9'
