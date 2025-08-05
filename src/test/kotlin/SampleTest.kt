import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class SampleTest {
    @Test
    fun test() {
        val listOf: MutableList<Int> = mutableListOf(1, 2, 3)

        println(listOf)

//        assertThrows<IndexOutOfBoundsException> {
//            listOf[3]
//        }

        assertThrows<IndexOutOfBoundsException>(
            "error", {
                listOf[3]
            }
        )

        assertThrows<IndexOutOfBoundsException>(
            {
                "error"
            }, {
                listOf[3]
            }
        )

        listOf += 4
        println(listOf)
    }

    @Test
    fun test2() {
        var list = getList()
//        list += 4

        assertEquals(list, listOf(1, 2, 3))
        val first = mutableListOf(1)
        val second = first;
        assertEquals(first, second)
        println(first.hashCode())
        println(second.hashCode())
    }

    private fun getList(): List<Int> {
        return mutableListOf<Int>(1, 2, 3)
    }

    @Test
    fun test3() {
        // 상수 + 가변 리스트 => 수정 가능
        val list1 = mutableListOf<Int>(1) // 가변 리스트
        list1 += 2
        println(list1) // [1, 2]
        list1.plusAssign(3)
        println(list1) // [1, 2, 3]
        println("=====")

        // 상수 + 불변 리스트 => 수정 불가능
        val list2 = listOf(1) // 불면 리스트
        // list2 += 2 // Error
        println("=====")

        // 변수 + 불변 리스트 => 수정 가능
        var list3 = listOf(1)
        list3 += 2
        println(list3) // [1, 2]
        val newList3 = list3 + 3
        println(newList3) // [1, 2, 3]

        println("=====")

        assertEquals(list1, listOf(1, 2, 3))
        assertEquals(list2, listOf(1))
        assertEquals(list3, listOf(1, 2))
        assertEquals(newList3, listOf(1, 2, 3))
    }

    @Test
    fun test4() {
        println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9))
        println(sum(10, 42, 83))
        val message: IntArray = intArrayOf(1, 2, 3, 4, 5)
        for (i in message) {
            print(i)
        }
        println()
        println(sum(*message))
    }

    private fun sum(vararg numbers: Int): Int {
        println(numbers.javaClass.simpleName)

        var result = 0;
        for (number in numbers) {
            result += number
        }
        return result
    }

    @Test
    fun test5() {
        println(
            first(8, 9, 32)
        )

    }

    private fun first(vararg numbers: Int): Int {
        var result = 0;
        for (number in numbers) {
            result += number
        }

        return result
    }

    private fun second(vararg numbers: Int) = first(*numbers)

    @Test
    fun test6() {
        val mapOf = mapOf(
            "Pi" to 3.14,
            "e" to 2.718,
            "phi" to 1.618
        )

        println(mapOf)
        println("=====")

        for (entry in mapOf) {
            println(entry)
        }

        println("=====")
        for ((key, value) in mapOf) {
            println("$key -> $value")
        }

        val get = mapOf["Pi"]
        println(get)

        val linkedMapOf = linkedMapOf(
            "Pi" to 3.14,
            "e" to 2.718,
            "phi" to 1.618
        )
        println(linkedMapOf)

        mapOf.plus("sample" to 1)
        println(mapOf)

        println("=====")
        val mutableMapOf = mutableMapOf(
            "Pi" to 3.14,
            "e" to 2.718,
            "phi" to 1.618
        )
        mutableMapOf["Pi"] = 5.0
        println(mutableMapOf)
        mutableMapOf.plusAssign("sample" to 123123.1)
        println(mutableMapOf)
    }
}