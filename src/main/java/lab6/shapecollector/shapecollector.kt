package lab6.shapecollector

import lab3.program.shapes.*

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        TODO()

    }

    fun addAll(new: List<T>) {
        TODO()

    }

    fun getAll(): List<T> {
        TODO()

    }

    fun getAllSorted(comparator: Comparator<T>): List<T> {
        TODO()
    }

}
