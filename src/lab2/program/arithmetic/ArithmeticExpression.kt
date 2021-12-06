package lab2.program.arithmetic

import java.lang.IllegalArgumentException
import kotlin.math.*

class ArithmeticExpression(expression : String) {
    //expression
    private val expression : ArrayList<String>
    //postfixExpression
    private val postfixExpression : ArrayList<String>
    init {
        this.expression =
            if (expression.isEmpty())
                throw IllegalArgumentException("Expression shouldn't be empty")
            else
                parse(expression)
        this.postfixExpression = infixToPostfix(this.expression)
    }
}

