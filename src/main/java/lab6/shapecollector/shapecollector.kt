package lab6.shapecollector

import lab3.program.shapes.*
import java.lang.reflect.Type
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

object ShapeComparators {
    val areaComparator = Comparator<Shape> { o1, o2 ->
        if (o1.calcArea() > o2.calcArea()) 1
        else if (o1.calcArea() < o2.calcArea()) -1
        else 0
    }
    val areaDescComparator = Comparator<Shape> { o1, o2 ->
        if (o1.calcArea() > o2.calcArea()) -1
        else if (o1.calcArea() < o2.calcArea()) 1
        else 0
    }
    val perimeterComparator = Comparator<Shape> { o1, o2 ->
        if (o1.calcPerimeter() > o2.calcPerimeter()) 1
        else if (o1.calcPerimeter() < o2.calcPerimeter()) -1
        else 0
    }
    val perimeterDescComparator = Comparator<Shape> { o1, o2 ->
        if (o1.calcPerimeter() > o2.calcPerimeter()) -1
        else if (o1.calcPerimeter() < o2.calcPerimeter()) 1
        else 0
    }
    val radiusComparator = Comparator<Circle> { o1, o2 ->
        if (o1.radius > o2.radius) 1
        else if (o1.radius < o2.radius) -1
        else 0
    }
    val radiusDescComparator = Comparator<Circle> { o1, o2 ->
        if (o1.radius > o2.radius) -1
        else if (o1.radius < o2.radius) 1
        else 0
    }
}

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: List<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        return allShapes.toList()
    }

    fun getAllSorted(comparator: Comparator<T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

    fun getAllByClass(shape: Class<out T>): List<out T> {
        return allShapes.filter { it.javaClass == shape }
    }

}