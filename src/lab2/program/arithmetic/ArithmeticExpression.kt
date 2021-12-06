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

    //method returns operator's priority
    private fun priority(operator : String) : Int {
        return when(operator){
            "(", ")" -> 1
            "+", "-" -> 2
            "*", "/" -> 3
            "^" -> 4
            "++", "--", "tg", "ctg", "sin", "cos", "lg", "ln" -> 5
            else -> -1
        }
    }
    //method checks symbols and deletes spaces
    private fun parse(expression: String) : ArrayList<String> {
        // this is deleting of spaces
        val strExp = expression.filter {
            it != ' '
        }
        val listExpression = ArrayList<String>()
        var brackets = 0
        var i = 0

        // defining operators and operands
        while(i < strExp.length){
            when(strExp[i]){
                in '0'..'9'->{
                    if (i != 0) {
                        if (strExp[i - 1] in '0'..'9' || strExp[i-1] == '.')
                            listExpression[listExpression.lastIndex] = listExpression.last() + strExp[i]
                        else
                            listExpression.add(strExp[i].toString())
                    }
                    else
                        listExpression.add(strExp[i].toString())
                }
                '.'->{
                    if (i == 0 || i == strExp.lastIndex)
                        throw IllegalArgumentException("Expression contains invalid symbols")
                    if (strExp[i-1] !in '0'..'9' || strExp[i+1] !in '0'..'9')
                        throw IllegalArgumentException("Expression contains invalid symbols")
                    listExpression[listExpression.lastIndex] = listExpression.last() + '.'
                }
                '('->{
                    brackets++
                    listExpression.add("(")
                }
                ')'->{
                    brackets--
                    listExpression.add(")")
                }
                '*', '/', '^'->{
                    listExpression.add(strExp[i].toString())
                }
                '-'->{
                    if (i == 0)
                        listExpression.add("--") // unary minus
                    else if(listExpression.last() in arrayOf("(", "*", "/", "--", "++", "-", "+", "^"))
                        listExpression.add("--") // unary minus
                    else
                        listExpression.add("-")
                }
                '+'->{
                    if (i == 0)
                        listExpression.add("++") // unary plus
                    else if(listExpression.last() in arrayOf("(", "*", "/", "--", "++", "-", "+", "^"))
                        listExpression.add("++") // unary plus
                    else
                        listExpression.add("+")
                }
                else-> {
                    when(strExp.substring(i, i+2)){
                        "tg" -> {
                            listExpression.add("tg")
                            i++
                        }
                        "lg" -> {
                            listExpression.add("lg")
                            i++
                        }
                        "ln" -> {
                            listExpression.add("ln")
                            i++
                        }
                        else -> when(strExp.substring(i, i+3)){
                            "sin" -> {
                                listExpression.add("sin")
                                i += 2
                            }
                            "cos" -> {
                                listExpression.add("cos")
                                i += 2
                            }
                            "ctg" -> {
                                listExpression.add("ctg")
                                i += 2
                            }
                            else -> throw IllegalArgumentException("Expression contains invalid symbols")
                        }
                    }
                }
            }

            if (brackets < 0)
                throw IllegalArgumentException("The brackets are incorrectly placed")
            i++
        }

        if (brackets != 0)
            throw IllegalArgumentException("The brackets are incorrectly placed")

        return listExpression
    }

    //method transfers infix to postfix
    private fun infixToPostfix(expression: ArrayList<String>) : ArrayList<String>{
        val stack = ArrayDeque<String>()
        val newExp = ArrayList<String>()
        expression.forEach {
            when(it[0]){
                in '0'..'9' -> newExp.add(it)
                '(' -> stack.addLast(it)
                ')' -> {
                    while(stack.isNotEmpty()){
                        if (stack.last() == "(") break
                        newExp.add(stack.last())
                        stack.removeLast()
                    }
                    if (stack.isNotEmpty()) stack.removeLast()
                }
                else -> {
                    while(stack.isNotEmpty()){
                        if (priority(it) > priority(stack.last())) break
                        newExp.add(stack.last())
                        stack.removeLast()
                    }
                    stack.addLast(it)
                }
            }
        }
        while(stack.isNotEmpty()) {
            newExp.add(stack.last())
            stack.removeLast()
        }
        return newExp
    }
    //method calculates expression's result
    fun calculate() : Double
    {
        val stack = ArrayDeque <Double>()
        this.postfixExpression.forEach {
            val firstOperand : Double
            val secondOperand : Double
            when(it[0]){
                in '0'..'9'->{
                    stack.addLast(it.toDouble())
                }
                '*', '/', '^'->{
                    if (stack.size < 2)
                        throw IllegalArgumentException("Not enough operands")
                    secondOperand = stack.last()
                    stack.removeLast()
                    firstOperand = stack.last()
                    stack.removeLast()
                    when(it){
                        "*"->stack.addLast(firstOperand * secondOperand)
                        "/"->stack.addLast(firstOperand / secondOperand)
                        "^"->stack.addLast(firstOperand.pow(secondOperand))
                    }
                }
                '+'->{
                    if (it == "+"){
                        if (stack.size < 2)
                            throw IllegalArgumentException("Not enough operands")
                        secondOperand = stack.last()
                        stack.removeLast()
                        firstOperand = stack.last()
                        stack.removeLast()
                        stack.addLast(firstOperand + secondOperand)
                    }
                }
                '-'->{
                    if (it == "-"){
                        if (stack.size < 2)
                            throw IllegalArgumentException("Not enough operands")
                        secondOperand = stack.last()
                        stack.removeLast()
                        firstOperand = stack.last()
                        stack.removeLast()
                        stack.addLast(firstOperand - secondOperand)
                    }
                    else{
                        if (stack.isEmpty())
                            throw IllegalArgumentException("Not enough operands")
                        stack[stack.lastIndex] = -stack[stack.lastIndex]
                    }
                }
                's'->{
                    if (stack.isEmpty())
                        throw IllegalArgumentException("Not enough operands")
                    stack[stack.lastIndex] = sin(stack.last())
                }
                't'->{
                    if (stack.isEmpty())
                        throw IllegalArgumentException("Not enough operands")
                    stack[stack.lastIndex] = tan(stack.last())
                }
                'c'->{
                    if (stack.isEmpty())
                        throw IllegalArgumentException("Not enough operands")
                    if (it == "cos")
                        stack[stack.lastIndex] = cos(stack.last())
                    else
                        stack[stack.lastIndex] = 1/tan(stack.last())
                }
                'l'->{
                    if (stack.isEmpty())
                        throw IllegalArgumentException("Not enough operands")
                    if (it == "lg")
                        stack[stack.lastIndex] = log10(stack[stack.lastIndex])
                    else
                        stack[stack.lastIndex] = ln(stack[stack.lastIndex])
                }
            }
        }
        if (stack.size != 1)
            throw IllegalArgumentException("Not enough operators")
        return stack.last()
    }
}

