package lab4.program

import lab4.program.matrix.MatrixImpl
import lab4.program.matrix.MutableMatrixImpl


fun main() {

    val matrix = MutableMatrixImpl(3, 3, 17.0)
    //method ToString
    println(matrix)
    //operator set
    matrix[0, 1] = 5.00
    println(matrix)

    var matrix2 = MatrixImpl(3, 3, 17.0)
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
    val arrMatrix = arrayOf(
        arrayOf(1.0, 2.0),
        arrayOf(3.0, 4.0),
        arrayOf(5.0, 6.0)
    )
    matrix2 = MatrixImpl(arrMatrix)
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