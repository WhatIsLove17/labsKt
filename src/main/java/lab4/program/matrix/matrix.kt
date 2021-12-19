package lab4.program.matrix

interface Matrix {
    val countRows: Int
    val countColumns: Int
    operator fun plus(other: Matrix): Matrix
    operator fun minus(other: Matrix): Matrix
    operator fun times(scalar: Double): Matrix
    operator fun div(scalar: Double): Matrix
    operator fun get(i: Int, j: Int): Double
    override fun equals(other: Any?): Boolean
    operator fun times(other: Matrix): Matrix
    operator fun unaryMinus(): Matrix
    operator fun unaryPlus(): Matrix
    override fun hashCode(): Int
    override fun toString(): String
}

interface MutableMatrix : Matrix {
    operator fun plusAssign(other: Matrix)
    operator fun minusAssign(other: Matrix)
    operator fun timesAssign(scalar: Double)
    operator fun divAssign(scalar: Double)
    operator fun set(i: Int, j: Int, value: Double)
    operator fun timesAssign(other: Matrix)
}

open class MatrixImpl(_countRows: Int, _countColumns: Int, private val fillingNumber: Double = 0.0) : Matrix {

    final override val countRows: Int
        get() = matrix.size
    final override val countColumns: Int
        get() = matrix[0].size
    protected var matrix: Array<Array<Double>> = Array(_countRows) { Array(_countColumns) { fillingNumber } }

    constructor(_matrix: Array<Array<Double>>) : this(_matrix.size, _matrix[0].size) {
        if (_matrix.isEmpty()) throw IllegalArgumentException("the transmitted matrix is empty")
        _matrix.forEach {
            if (it.size != countColumns) throw IllegalArgumentException("All rows in the transmitted matrix must be same size")
        }
        for (i in 0 until countRows)
            for (j in 0 until countColumns)
                matrix[i][j] = _matrix[i][j]
    }

    override operator fun plus(other: Matrix): Matrix {
        if (other.countColumns != this.countColumns || other.countRows != this.countRows)
            throw IllegalArgumentException("the sizes of the matrices should be equal")
        val result = MatrixImpl(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] + other[i, j]
        return result
    }

    override operator fun minus(other: Matrix): Matrix {
        if (other.countColumns != this.countColumns || other.countRows != this.countRows)
            throw IllegalArgumentException("the sizes of the matrices should be equal")
        val result = MatrixImpl(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] - other[i, j]
        return result
    }

    override operator fun times(scalar: Double): Matrix {
        val result = MatrixImpl(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] * scalar
        return result
    }

    override operator fun div(scalar: Double): Matrix {
        val result = MatrixImpl(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] / scalar
        return result
    }

    override operator fun get(i: Int, j: Int): Double {
        return matrix[i][j]
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix) return false
        if (this.countRows != other.countRows || this.countColumns != other.countColumns)
            return false
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                if (this[i, j] != other[i, j]) return false
        return true
    }

    override operator fun times(other: Matrix): Matrix {
        if (this.countRows != other.countColumns)
            throw IllegalArgumentException("Count of Rows of the first matrix should be equal count of columns of the second matrix")
        val result = MatrixImpl(this.countRows, other.countColumns)
        for (i in 0 until result.countRows)
            for (j in 0 until result.countColumns) {
                for (k in 0 until this.countColumns)
                    result.matrix[i][j] += this[i, k] * other[k, j]
            }
        return result
    }

    override fun unaryMinus(): Matrix {
        val result = Array(countRows) { Array(countColumns) { fillingNumber } }
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result[i][j] = -this[i, j]
        return MatrixImpl(result)
    }

    override fun unaryPlus(): Matrix {
        val result = Array(countRows) { Array(countColumns) { fillingNumber } }
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result[i][j] = this[i, j]
        return MatrixImpl(result)
    }

    override fun hashCode(): Int {
        var result = countRows
        result = 31 * result + countColumns
        result = 31 * result + matrix.contentDeepHashCode()
        return result
    }

    override fun toString(): String {
        var out = ""
        for (i in 0 until countRows) {
            for (j in 0 until countColumns)
                out += this[i, j].toString() + if (j != countColumns - 1) " " else ""
            out += "\n"
        }
        return out
    }
}


class MutableMatrixImpl(_countRows: Int, _countColumns: Int, fillingNumber: Double = 0.0) :
    MatrixImpl(_countRows, _countColumns, fillingNumber), MutableMatrix {

    override fun plusAssign(other: Matrix) {
        if (other.countColumns != this.countColumns || other.countRows != this.countRows)
            throw IllegalArgumentException("the sizes of the matrices should be equal")
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                this[i, j] += other[i, j]
    }

    override fun minusAssign(other: Matrix) {
        if (other.countColumns != this.countColumns || other.countRows != this.countRows)
            throw IllegalArgumentException("the sizes of the matrices should be equal")
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                this[i, j] -= other[i, j]
    }

    override fun timesAssign(scalar: Double) {
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                this[i, j] *= scalar
    }

    override fun timesAssign(other: Matrix) {
        if (this.countColumns != other.countRows)
            throw IllegalArgumentException("Count of Rows of the first matrix should be equal count of columns of the second matrix")
        val result = Array(this.countRows) { Array(other.countColumns) { 0.0 } }
        for (i in 0 until this.countRows)
            for (j in 0 until other.countColumns) {
                for (k in 0 until this.countColumns)
                    result[i][j] += this[i, k] * other[k, j]
            }
        this.matrix = result
    }

    override fun divAssign(scalar: Double) {
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                this[i, j] /= scalar
    }

    override fun set(i: Int, j: Int, value: Double) {
        matrix[i][j] = value
    }

}
