package lab3.program.shapes

import kotlinx.serialization.Serializable
import kotlin.math.*
import kotlin.random.Random

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

@Serializable
data class Circle(val radius: Double) : Shape {

    init {
        if (radius <= 0) throw IllegalArgumentException("Radius should be more than zero")
    }

    override fun calcArea(): Double {
        return PI * radius.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 2 * PI * radius
    }

    override fun toString(): String {
        return "Circle: Radius - $radius, Area - ${calcArea()}, Perimeter - ${calcPerimeter()}"
    }
}

@Serializable
data class Square(val side: Double) : Shape {

    init {
        if (side <= 0) throw IllegalArgumentException("Side should be more than zero")
    }

    override fun calcArea(): Double {
        return side.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 4 * side
    }

    override fun toString(): String {
        return "Square: Sides - $side, Area - ${calcArea()}, Perimeter - ${calcPerimeter()}"
    }
}

@Serializable
data class Rectangle(val sideA: Double, val sideB: Double) : Shape {

    init {
        if (sideA <= 0) throw IllegalArgumentException("SideA should be more than zero")
        if (sideB <= 0) throw IllegalArgumentException("SideA should be more than zero")
    }

    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return 2 * sideA + 2 * sideB
    }

    override fun toString(): String {
        return "Rectangle: SideA - $sideA, SideB - $sideB, Area - ${calcArea()}, Perimeter - ${calcPerimeter()}"
    }
}

@Serializable
data class Triangle(val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA >= sideB + sideC ||
            sideB >= sideA + sideC ||
            sideC >= sideA + sideB
        )
            throw IllegalArgumentException("Triangle doesn't exist")
    }

    override fun calcArea(): Double {
        val p = (sideA + sideB + sideC) / 2
        return sqrt(p * (p - sideA) * (p - sideB) * (p - sideC))
    }

    override fun calcPerimeter(): Double {
        return sideA + sideB + sideC
    }

    override fun toString(): String {
        return "Triangle: SideA - $sideA, SideB - $sideB, SideC - $sideC, Area - ${calcArea()}, Perimeter - ${calcPerimeter()}"
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(side: Double): Square
    fun createRectangle(sideA: Double, sideB: Double): Rectangle
    fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactoryImpl : ShapeFactory {
    var randomUpperBound = 1e3
        set(value) {
            field = if (!value.isNaN() && !value.isInfinite()) value
            else throw IllegalArgumentException("So big or small Upper Bound")
        }
    var randomLowerBound = 1e-2
        set(value) {
            field = if (!value.isNaN() && !value.isInfinite()) value
            else throw IllegalArgumentException("So big or small Lower Bound")
        }

    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(side: Double): Square {
        return Square(side)
    }

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle {
        return Rectangle(sideA, sideB)
    }

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle {
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomCircle(): Circle {
        val radius = Random.nextDouble(randomLowerBound, randomUpperBound)
        return Circle(radius)
    }

    override fun createRandomSquare(): Square {
        val side = Random.nextDouble(randomLowerBound, randomUpperBound)
        return Square(side)
    }

    override fun createRandomRectangle(): Rectangle {
        val sideA = Random.nextDouble(randomLowerBound, randomUpperBound)
        val sideB = Random.nextDouble(randomLowerBound, randomUpperBound)
        return Rectangle(sideA, sideB)
    }

    override fun createRandomTriangle(): Triangle {
        val sideA = Random.nextDouble(randomLowerBound, randomUpperBound)
        val sideB = Random.nextDouble(randomLowerBound, randomUpperBound)
        val sideC = Random.nextDouble(max(sideA, sideB) - min(sideA, sideB) + Double.MIN_VALUE, sideA + sideB)
        return Triangle(sideA, sideB, sideC)
    }

    override fun createRandomShape(): Shape {
        return when (Random.nextInt(0, 4)) {
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            else -> createRandomTriangle()
        }
    }
}
