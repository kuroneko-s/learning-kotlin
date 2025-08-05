import com.choidh.doubleQuote
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

fun color(red: Int = 0, green: Int = 0, blue: Int = 0): String = "red- $red, green- $green, blue- $blue"

class Sample {
    fun hello() {
        println("hello")
    }

    fun hello(name: String, n: Int) {
        println("hello $name, int $n")
    }

    fun hello(n:Int, name:String) {
        println("hello $name, int $n")
    }
}

fun f(n: Int = 0) = n + 373
fun f() = 100

class ExtentionTest {
    @Test
    fun test() {
        println("Hi".doubleQuote())
        println("=====")

        println(color(
            green = 2,
        ))
        println("=====")

        val list = listOf<Int>(1, 2, 3)
        println(list.joinToString(separator = ":", prefix = "[", postfix = "]"))
        "".trimMargin()
    }

    @Test
    fun test2() {
        assertEquals(f(), 100)
        assertNotEquals(f(), 373)
        assertEquals(f(100), 473)

        val result = when(7) {
            1 -> "one"
            2 -> "two"
            3 -> "three"
            4 -> "four"
            5 -> "five"
            6, 7 -> "six and seven"
            else -> "other"
        }
        assertEquals(result, "six and seven")
    }

    @Test
    fun test3() {
        val f = DataSample("a", 1)
        val s = DataSample("a", 1)

        assertEquals(f, s)
        assertEquals(f.hashCode(), s.hashCode())
    }

    @Test
    fun test4() {
        val r1 = PairSample(5)
        val r2 = PairSample(15)
        assertEquals(r1, Pair("first", 10))
        assertEquals(r2, Pair("first", 45))
        val (r1Name, r1Value) = r1
        val (r2Name, r2Value) = r2

        assertEquals(r1Name, "first")
        assertEquals(r1Value, 10)
        assertEquals(r2Name, "first")
        assertEquals(r2Value, 45)
    }

    @Test
    fun test5() {
        val s1 = null
        // var s2:String = null // compile 레벨에서 에러
        val s3:String? = null
        val s4:String? = s3
        var s5: String = ""

        // null 체킹하면 삽입가능
        if (s4 != null) s5 = s4

        val a1: Amphibian = Amphibian()
        val a2: Amphibian? = null
        val a3: Species = Species.Toad
        val a4: Amphibian? = null

        println(s3?.length ?: 0)

        assertThrows<NullPointerException> {
            var a5:String = s1!!
        }
    }

    @Test
    fun test6() {
        val s1: String? = null
        assertTrue(s1.isNullOrBlank())
        assertTrue { s1.customNullOrEmpty() }
    }

    fun isNullOrEmpty(str: String?): Boolean {
        return str == null || str.isEmpty()
    }

    fun String?.customNullOrEmpty(): Boolean = this == null || this.isEmpty()

    @Test
    fun test7() {
        val anyHolder = AnyHolder(Dog())
        val any = anyHolder.getValue()
        // println(any.bark())

        val genericHolder = GenericHolder(Dog())
        val generic = genericHolder.getValue()
        println(generic.bark())

        assertEquals(listOf(0, 1).firstOrNull, 0)
        assertEquals(emptyList<Int>().firstOrNull, null)
        val listOf: List<*> = listOf(1, 2, 3)
        val v: Any? = listOf[0]
    }
}

val <T> List<T>.firstOrNull: T?
    get() = if (isEmpty()) null else this[0]

val Dog.something: String
    get() = "$this hello"

class Dog {
    fun bark() = "Ruff"
}

class AnyHolder(private val value: Any) {
    fun getValue(): Any = value
}

class GenericHolder<T>(private val value: T) {
    fun getValue(): T = value
}


class Amphibian

enum class Species {
    Frog, Toad, Salamander, Caecilian
}

private fun PairSample(n: Int) : Pair<String, Int> {
    val result = when {
        (10 >= n) -> n  * 2
        else -> n * 3
    }
    return Pair("first", result)
}

// getter, setter, toString, equals 자동 생성
data class DataSample(
    val name: String,
    val n: Int
)