package lab3.program

import lab3.program.shapes.*

fun main() {
    val listOfShapes = ArrayList<Shape>()
    val shapeFct = ShapeFactoryImpl()
    for (i in 0..50)
        listOfShapes.add(shapeFct.createRandomShape())
    println("Sum of shapes' areas: \n${listOfShapes.sumOf { it.calcArea() }}")
    println("Sum of shapes' perimeters: \n${listOfShapes.sumOf { it.calcPerimeter() }}")
    println("Shape with the biggest area: \n${listOfShapes.find { it -> it.calcArea() == listOfShapes.maxOf { it.calcArea() } }} ")
    println("Shape with the biggest perimeter: \n${listOfShapes.find { it -> it.calcPerimeter() == listOfShapes.maxOf { it.calcPerimeter() } }} ")
    println("Shape with the smallest area: \n${listOfShapes.find { it -> it.calcArea() == listOfShapes.minOf { it.calcArea() } }} ")
    println("Shape with the smallest perimeter: \n${listOfShapes.find { it -> it.calcPerimeter() == listOfShapes.minOf { it.calcPerimeter() } }} ")
}