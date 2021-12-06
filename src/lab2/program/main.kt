package lab2.program
import lab2.program.arithmetic.ArithmeticExpression
import java.lang.IllegalArgumentException

fun main() {
    try {
        //var example = ArithmeticExpression("15*sin(12^(-3/5))/7")
        val example : String = readLine() ?: throw IllegalArgumentException("Expression shouldn't be empty")
        val calc = ArithmeticExpression(example)
        println(calc.calculate())
    }
    catch(e:Exception)
    {
        println(e.message)
    }
}