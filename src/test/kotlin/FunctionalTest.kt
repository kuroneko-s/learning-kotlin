import org.junit.jupiter.api.Test
import java.util.Locale
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FunctionalTest {
    @Test
    fun test() {
        val strList = listOf("a", "b", "c", "d", "e", "f")
        // strList.map({ str: String -> "[$str]" })
        // strList.map({ str -> "[$str]" }) // 타입 명시 삭제
        // strList.map { str -> "[$str]" } // 괄호 삭제
        // strList.map { "[${it.uppercase()}]" } // 인자가 한개인 경우 파라미터명 it로 바인딩 (default)
        val mapList = strList.map { "[${it.uppercase()}]" }
        for (str in mapList) {
            println(str)
        }

        val joinStr = mapList.joinToString(" ") { "{$it}" } // 람다를 마지막에 받는 경우에 이렇게 쓸 수 있음.
        assertEquals(joinStr, "{[A]} {[B]} {[C]} {[D]} {[E]} {[F]}")

        val joinStr2 = mapList.joinToString(
            separator = " ",
            transform = { "{$it}" }
        ) // 파라미터명으로 파라미터 값 넘겨줄 때는 괄호안에 람다식을 배치해줘야함.
        assertEquals(joinStr2, "{[A]} {[B]} {[C]} {[D]} {[E]} {[F]}")

        val widthIndexList = strList.mapIndexed { index, str -> "[$index] - $str]" }
        assertEquals(widthIndexList.toString(), "[[0] - a], [1] - b], [2] - c], [3] - d], [4] - e], [5] - f]]")
        println(widthIndexList)

        println("#####")

        for (i in strList.indices) {
            println(i)
        }

        println("#####")

        val blankClass = BlankClass()
        // blankClass.print { -> println("Hello") } // 파라미터가 없는 사실을 강조하기위해서 화살표를 남겨둘 수 있는데 스타일 가이드에서는 비추천.
        blankClass.print { println("Hello") } // 이렇게 쓰면 돔.
    }

    @Test
    fun test2() {
        val intList = (1..100).toList()
        val even = intList.filter { it % 2 == 0 }
        val evenFiltering: (Int) -> Boolean = { e: Int -> e % 2 == 0 }
        val moreBigger = intList.filter { it > 50 }
        assertTrue(48 in even)
        assertEquals(even, intList.filter(evenFiltering))
        assertFalse { 50 in moreBigger }
        assertEquals(2550, intList.filter(evenFiltering).sum())
        assertTrue(intList.any { it >= 100 })
        assertFalse(intList.any { it > 100 })
    }

    @Test
    fun test3() {
        val list1 = List(10) { it }
        assertEquals(list1.toString(), "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]")
        val list2 = List(10) { 0 }
        assertEquals(list2.toString(), "[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]")
        val list3 = List(10) { 'a' + it } // 문자열에 숫자 더하면 다음 문자열 나오는 원리
        assertEquals(list3.toString(), "[a, b, c, d, e, f, g, h, i, j]")
        val list4 = List(10) { list3[it % 3] } // 반복
        assertEquals(list4.toString(), "[a, b, c, a, b, c, a, b, c, a]")

        assertEquals(list2.count { it == 0 }, 10)
        assertEquals(list1.count { it == 0 }, 1)
    }

    @Test
    fun test4() {
        val messageList = listOf(
            Message(
                "Boss",
                "Let's discuss goals for next year",
                false,
                listOf(
                    Attachment("image", "cute cats")
                )
            ),
        )

//        val filterMessageList = messageList.filterNot(Message::isRead)
//        assertEquals(filterMessageList.size, 1)
        assertTrue { messageList.any(Message::isImportant) }
    }


}

data class Message(
    val sender: String,
    val text: String,
    val isRead: Boolean,
    val attachments: List<Attachment>
)

data class Attachment(
    val type: String,
    val name: String
)

fun Message.isImportant(): Boolean = text.contains("Salary increase") || attachments.any {
    it.type == "image" && it.name.contains("cat")
}

class BlankClass() {
    fun print(fn: () -> Unit) {
        println(fn) // 일급함수 취급해줌
        fn() // 동작
    }
}