import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

class ListTest {

    @Test
    fun test() {
        val list1 = listOf(1, 2, 3, 4, 5)
        val list2 = listOf(6, 7, 8, 9, 0)
        val list3 = List(5) { it }
        val list4 = List(10) { it }

        val list1Zip = list1.zip(list2)
        println(list1Zip)
        println(list4.zip(list3))

        val nameList = listOf("A", "B", "C")
        val ageList = listOf(1, 2, 3)

        val personList = nameList.zip(
            ageList,
            { a, b -> Person(a, b) }
        )
        println(personList)

        val personList2 = nameList.zip(ageList) { a, b -> Person(a, b) }
        println(personList2)

        println(listOf("a", "b", "c", "d", "e", "f").zipWithNext())
        println(listOf("a", "b", "c", "d", "e", "f").zipWithNext { a, b -> "$a and $b"})

        println((100..200).zip(list4).flatMap { (a, b) -> listOf(a, b) })
        val stackList = listOf(
            listOf(1, 2),
            listOf(3, 4),
            listOf(5, 6),
        )

        println(stackList)
        println(stackList.flatten())

        val intRange = 1..3
        println(intRange.map { a -> intRange.map { b -> Pair(a, b) } })
        println(intRange.map { a -> intRange.map { b -> a to b } })
        println(intRange.map { a -> intRange.map { b -> listOf(a, b) } })

        println("#####")

        println(intRange.map { a -> intRange.map { b -> Pair(a, b) } }.flatten())
        println(intRange.map { a -> intRange.map { b -> a to b } }.flatten())
        println(intRange.map { a -> intRange.map { b -> listOf(a, b) } }.flatten())
        println(intRange.map { a -> intRange.map { b -> listOf(a, b) }.flatten() }.flatten())

        println("#####")
        println(intRange.flatMap { a -> intRange.map { b -> Pair(a, b) } })
        println(intRange.flatMap { a -> intRange.map { b -> a to b } })
        println(intRange.flatMap { a -> intRange.map { b -> listOf(a, b) } })
    }

    @Test
    fun test2() {
        val bookList = listOf<Book>(
            Book("1984", listOf("George Orwell")),
            Book("Ulysses", listOf("JamesJyce", "Sub Author")),
        )

        println(bookList.map { it.authors })
        println(bookList.map { it.authors }.flatten())
        println(bookList.flatMap { it.authors })
        assertEquals(
            bookList.map { it.authors }.flatten(),
            bookList.flatMap { it.authors }
        )

        println(deck.size)

        val rand = Random(29)
        repeat(7) {
            println(deck.random(rand))
        }
    }
}

val deck = Suit.entries.flatMap { suit ->
    Rank.entries.map { rank ->
        Card(rank, suit)
    }
}

enum class Suit {
    Spade, Club, Heart, Diamond
}

enum class Rank(val faceValue: Int) {
    Ace(1), Two(2), Three(3), Four(4), Five(5), Six(6),
    Seven(7), Eight(8), Nine(9), Ten(10),
    Jack(11), Queen(12), King(13)
}

class Card(
    val rank: Rank,
    val suit: Suit
) {
    override fun toString(): String {
        return "$rank of $suit"
    }
}

class Book(
    val title: String,
    val authors: List<String>
)