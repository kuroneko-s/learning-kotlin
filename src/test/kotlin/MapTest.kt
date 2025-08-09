import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class MapTest {
    @Test
    fun test() {
        val groupBy: Map<Int, List<Person>> = getPeople().groupBy(Person::age)
        println(
            getPeople().groupBy { Person::age }
        )
        println("gorupBy".first())

        val personKeyMap: Map<Person, String> = getPeople().associateWith { it.name }
        println(personKeyMap)
        assertEquals(personKeyMap.size, 5)

        val stringKeyMap: Map<String, Person> = getPeople().associateBy { it.name }
        println(stringKeyMap)
        assertEquals(stringKeyMap.size, 4)

        val mapOf = mutableMapOf<String, String>()
        assertTrue { mapOf.isEmpty() }
        mapOf.getOrPut("empty") { "empty" }
        assertTrue { mapOf.isNotEmpty() }
        assertEquals("empty", mapOf["empty"])
        assertTrue { mapOf.getOrPut("empty") { "second" } == "empty" }
    }

    @Test
    fun test2() {
        val even = mapOf(99 to "maximum", 2 to "two", 4 to "four", 5 to "five", 6 to "six")
        println(even.map { (key, value) -> "$key=$value" })
        println("######")

        println(
            even.mapKeys { (key, _) -> key * -1 }
                .mapValues { (_, value) -> "minus $value" }
        )
        println("#####")

        println(
            even.map { (key, value) -> key * -1 to "minus $value" }.toMap()
        )

        println(
            listOf(
                Pair("Empty", "텅텅")
            ).toMap()
        )

        println(
            mapOf(
                Pair("Empty", "텅텅")
            )
        )

        println(
            even.maxByOrNull { (key, value) -> key }
        )
    }

    @Test
    fun test3() {
        val list = listOf(1,2,3,4)
        println(
            list
                .filter(::isEven)
                .map(::square)
                .any(::lessThanTen)
        )
        println("======")

        println(
            list.asSequence()
                .filter(::isEven)
                .map(::square)
                .any(::lessThanTen)
        )

        val r = list.asSequence()
            .map(::square)
        println(r.toString())
        println(r.toString().substringBefore("@"))

//        listOf(1,2,3,4)
//            .filter { it % 2 == 0 }
//            .map { it * it }
//            .any()

        val naturalNumbers = generateSequence(1) { it + 1 }
        println(
            naturalNumbers.take(3)
                .toList()
        )

        val items = mutableListOf("first", "second", "third", "XXX", "4th")
        val seq = generateSequence { items.removeAt(0).takeIf { it != "XXX" } }
        println(seq.toList())
        println(items)
        // println(seq.toList()) // error

        println(
            generateSequence(6) { (it - 1).takeIf { it > 0 } }.toList()
        )

        println(
            generateSequence('a') { (it + 1).takeIf { it <= 'z' } }.toList()
        )
    }
}

fun lessThanTen(n: Int): Boolean {
    println("### $n lessThanTen work ###")
    return n < 10
}

fun square(n: Int): Int {
    println("### $n square work ###")
    return n * n
}

fun isEven(n: Int): Boolean {
    println("### $n isEven work ###")
    return n % 2 == 0
}

val names = listOf("Alice", "Bob", "Charlie", "Dmitry", "Alice")
val ages = listOf(20, 40, 30, 10, 40)

fun getPeople(): List<Person> =
    names.zip(ages) { a, b -> Person(a, b) }