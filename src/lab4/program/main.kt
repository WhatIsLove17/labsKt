package lab4.program

import lab4.program.matrix.MyMatrix
import lab4.program.matrix.MyMutableMatrix

fun main() {
    val matrix = MyMutableMatrix(3, 3, 17.0)
    //method ToString
    println(matrix)
    //operator set
    matrix[0, 1] = 5.00
    println(matrix)

    var matrix2 = MyMatrix(3, 3, 17.0)
    //method equals
    println(matrix == matrix2)

    matrix[0, 1] = 17.0

    println(matrix == matrix2)

    //operator *=
    matrix *= 3.0
    println(matrix)

    //operator /=
    matrix /= 2.0
    println(matrix)

    //operator *
    println(matrix * 2.0)

    //operator /
    println(matrix / 2.0)

    //operator +=
    matrix += matrix2
    println(matrix)

    //operator -=
    matrix -= matrix2
    println(matrix)

    //operator *=
    matrix2 = MyMatrix(3, 4, 2.0)
    matrix *= matrix2
    println(matrix)

    //operator unary -
    println(-matrix)

    //operator unary +
    println(+matrix)

    //operator -
    println(matrix - matrix2)

    //operator +
    println(matrix + matrix2)
}