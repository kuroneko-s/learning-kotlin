package com.choidh

import com.sun.source.tree.InstanceOfTree

const val str = "Sample"


fun main() {
    val str = "inner sample"
    println("str" === "str")
    println("str" == "str")

    showRange(1..5)
    showRange(0 until 5)
    showRange(5 downTo 1)
    showRange(0..9 step 2)
    showRange(0 until 10 step 3)
    showRange(9 downTo 2 step 3)
    showRange('a'..'z')

    val charArr = "abcdef"
    for (c in charArr) {
        print("$c ")
    }
    println()

    for (c in 0..charArr.lastIndex) {
        print("${charArr[c]} ")
    }
    println()

    for (c in "Jnskhm ") {
        print(c + 1)
    }
    println()

    repeat(5) {
        print("Hello ~ ")
    }
    println()
}

fun showRange(r: CharProgression) {
    for (i in r) {
        print("$i ")
    }
    print("// $r")
    println()
}

fun showRange(r: IntProgression) {
    for (i in r) {
        print("$i ")
    }
    print("// $r")
    println()
}

fun sample(number: Number) {
    println("""$str + $number """)
}

fun sample2(number: Int): String {
    return """$number $str"""
}

fun compareTo(a: Int, b: Int): Int {
    return if (a > b) a else b
}