package lab4.program.matrix

interface IMyMatrix {
    var countRows: Int
    var countColumns: Int
    operator fun plus(other: IMyMatrix): IMyMatrix
    operator fun minus(other: IMyMatrix): IMyMatrix
    operator fun times(scalar: Double): IMyMatrix
    operator fun div(scalar: Double): IMyMatrix
    operator fun get(i: Int, j: Int): Double
    override fun equals(other: Any?): Boolean
    operator fun times(other: IMyMatrix): IMyMatrix
    override fun hashCode(): Int
    override fun toString(): String
}

interface IMyMutableMatrix {
    operator fun plusAssign(other: IMyMatrix)
    operator fun minusAssign(other: IMyMatrix)
    operator fun timesAssign(scalar: Double)
    operator fun divAssign(scalar: Double)
    operator fun set(i: Int, j: Int, value: Double)
    operator fun unaryMinus(): IMyMatrix
    operator fun unaryPlus(): IMyMatrix
    operator fun timesAssign(other: IMyMatrix)
}

open class MyMatrix(_countRows: Int, _countColumns: Int, private val fillingNumber: Double = 0.0) : IMyMatrix {

    final override var countRows: Int = _countRows
        set(value) {
            if (value > 0) field = value
            else throw IllegalArgumentException("matrix's count of rows and columns should be more than zero")
        }
    final override var countColumns: Int = _countColumns
        set(value) {
            if (value > 0) field = value
            else throw IllegalArgumentException("matrix's count of rows and columns should be more than zero")
        }
    protected var matrix: Array<Array<Double>> = Array(countRows) { Array(countColumns) { fillingNumber } }

    override operator fun plus(other: IMyMatrix): IMyMatrix {
        if (other.countColumns != this.countColumns || other.countRows != this.countRows)
            throw IllegalArgumentException("the sizes of the matrices should be equal")
        val result = MyMatrix(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] + other[i, j]
        return result
    }

    override operator fun minus(other: IMyMatrix): IMyMatrix {
        if (other.countColumns != this.countColumns || other.countRows != this.countRows)
            throw IllegalArgumentException("the sizes of the matrices should be equal")
        val result = MyMatrix(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] - other[i, j]
        return result
    }

    override operator fun times(scalar: Double): IMyMatrix {
        val result = MyMatrix(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] * scalar
        return result
    }

    override operator fun div(scalar: Double): IMyMatrix {
        val result = MyMatrix(this.countRows, this.countColumns)
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                result.matrix[i][j] = this[i, j] / scalar
        return result
    }

    override operator fun get(i: Int, j: Int): Double {
        return matrix[i][j]
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MyMatrix) return false
        if (this.countRows != other.countRows || this.countColumns != other.countColumns)
            return false
        for (i in 0 until this.countRows)
            for (j in 0 until this.countColumns)
                if (this[i, j] != other[i, j]) return false
        return true
    }

    override operator fun times(other: IMyMatrix): IMyMatrix {
        if (this.countRows != other.countColumns)
            throw IllegalArgumentException("Count of Rows of the first matrix should be equal count of columns of the second matrix")
        val result = MyMatrix(this.countRows, other.countColumns)
        for (i in 0 until result.countRows)
            for (j in 0 until result.countColumns) {
                for (k in 0 until this.countColumns)
                    result.matrix[i][j] += this[i, k] * other[k, j]
            }
        return result
    }

    override fun hashCode(): Int {
        var result = countRows
        result = 31 * result + countColumns
        result = 31 * result + fillingNumber.hashCode()
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

