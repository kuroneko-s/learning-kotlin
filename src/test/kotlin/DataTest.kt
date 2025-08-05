import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Data(var i: Int)
class DefaultClass {
    var i: Int = 0
        get() {
            println("get()")
            return field // getter, setter 에서 사용할 수 있는 고유 명사
        }
        set(value) {
            println("set($value)")
            field = value
        }
    var n: String = ""
        set(value) {
            field = "setting is $value"
        }
}

class DataTest {
    @Test
    fun test() {
        val data = Data(10)
        assertEquals(data.i, 10)
        println("-----")
        val d = DefaultClass()
        d.i = 2
        println(d.i)

        d.n = "Hi"
        println(d.n)
    }

    @Test
    fun test2() {
        val cage = Cage(2)
        assertFalse { cage.full }
        assertEquals(cage.capacity, 2)
        assertTrue { cage.put(Hamster("Cutey")) }
        assertTrue { cage.put(Hamster("Kawaii")) }
        assertFalse { cage.put(Hamster("Angry")) }
        assertTrue { cage.full }
        val take = cage.take()
        assertEquals(take.name, "Cutey")
        assertFalse { cage.full }
        assertEquals(cage.capacity, 1)
    }
}

class Hamster(val name: String)
class Cage(private val maxCapacity: Int) {
    private val hamsterList = mutableListOf<Hamster>()
    val capacity: Int get() = maxCapacity - hamsterList.size
    val full: Boolean get() = hamsterList.size == maxCapacity
    fun put(hamster: Hamster): Boolean =
        if (full) false
        else {
            hamsterList.add(hamster)
            true
        }
    fun take(): Hamster = hamsterList.removeAt(0)
}