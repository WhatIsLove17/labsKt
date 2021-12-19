package lab6

import lab3.program.shapes.*
import lab6.shapecollector.ShapeCollector
import lab6.shapecollector.ShapeComparators

fun main() {
    val shapeFactory = ShapeFactoryImpl()
    val shapeCollector = ShapeCollector<Shape>()
    for (i in 0..5)
        shapeCollector.add(shapeFactory.createRandomShape())

    shapeCollector.getAll().forEach {
        println(it)
    }
    println("\nOrder by area\n")
    shapeCollector.getAllSorted(ShapeComparators.areaComparator).forEach {
        println(it)
    }
    println("\nDesc order by area\n")
    shapeCollector.getAllSorted(ShapeComparators.areaDescComparator).forEach {
        println(it)
    }
    println("\nOrder by perimeter\n")
    shapeCollector.getAllSorted(ShapeComparators.perimeterComparator).forEach {
        println(it)
    }
    println("\nDesc order by perimeter\n")
    shapeCollector.getAllSorted(ShapeComparators.perimeterDescComparator).forEach {
        println(it)
    }

    println("\n\n\n\n\n\n\n\n\n\n")

    shapeCollector.getAllByClass(Square::class.java).forEach {
        println(it)
    }

    println("\nCircles:\n")
    val circleCollector = ShapeCollector<Circle>()
    for (i in 0..5)
        circleCollector.add(shapeFactory.createRandomCircle())

    circleCollector.getAll().forEach {
        println(it)
    }
    println("\nOrder by radius\n")
    circleCollector.getAllSorted(ShapeComparators.radiusComparator).forEach {
        println(it)
    }
    println("\nDesc order by radius\n")
    circleCollector.getAllSorted(ShapeComparators.radiusDescComparator).forEach {
        println(it)
    }


}