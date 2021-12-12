package lab3.test

import lab3.program.shapes.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.math.*
import kotlin.random.Random
import kotlin.test.assertEquals

class ShapesTest {

    @Test
    fun circleTest() {
        val testFactory = ShapeFactoryImpl()
        var testCircle = testFactory.createCircle(5.0)
        assertEquals(5.0, testCircle.radius)
        assertThrows<IllegalArgumentException> {
            testCircle = testFactory.createCircle(-5.0)
        }

        for (i in 1..100) {
            val radius = Random.nextDouble(0.1, 1e9)
            testCircle = testFactory.createCircle(radius)
            assertEquals(PI * (radius.pow(2)), testCircle.calcArea())
            assertEquals(2 * PI * radius, testCircle.calcPerimeter())
        }
    }

    @Test
    fun squareTest() {
        val testFactory = ShapeFactoryImpl()
        var testSquare = testFactory.createSquare(5.0)
        assertEquals(5.0, testSquare.side)
        assertThrows<IllegalArgumentException> {
            testSquare = testFactory.createSquare(-5.0)
        }

        for (i in 1..100) {
            val side = Random.nextDouble(0.1, 1e9)
            testSquare = testFactory.createSquare(side)
            assertEquals(side.pow(2), testSquare.calcArea())
            assertEquals(side * 4, testSquare.calcPerimeter())
        }
    }

    @Test
    fun rectangleTest() {
        val testFactory = ShapeFactoryImpl()
        var testRect = testFactory.createRectangle(5.0, 10.0)
        assertEquals(5.0, testRect.sideA)
        assertEquals(10.0, testRect.sideB)
        assertThrows<IllegalArgumentException> {
            testRect = testFactory.createRectangle(-5.0, 10.0)
            testRect = testFactory.createRectangle(5.0, -10.0)
        }

        for (i in 1..100) {
            val sideA = Random.nextDouble(0.1, 1e9)
            val sideB = Random.nextDouble(0.1, 1e9)
            testRect = testFactory.createRectangle(sideA, sideB)
            assertEquals(sideA * sideB, testRect.calcArea())
            assertEquals(sideA * 2 + sideB * 2, testRect.calcPerimeter())
        }
    }

    @Test
    fun triangleTest() {
        val testFactory = ShapeFactoryImpl()
        var testTriangle = testFactory.createTriangle(5.0, 10.0, 14.0)
        assertEquals(5.0, testTriangle.sideA)
        assertEquals(10.0, testTriangle.sideB)
        assertThrows<IllegalArgumentException> {
            testTriangle = testFactory.createTriangle(-5.0, 10.0, 14.0)
            testTriangle = testFactory.createTriangle(5.0, -10.0, 14.0)
            testTriangle = testFactory.createTriangle(5.0, 10.0, -14.0)
        }

        for (i in 1..100) {
            val sideA = Random.nextDouble(0.1, 1e9)
            val sideB = Random.nextDouble(0.1, 1e9)
            val sideC = Random.nextDouble(max(sideA, sideB) - min(sideA, sideB) + 0.01, sideA + sideB)
            val p = (sideA + sideB + sideC) / 2
            testTriangle = testFactory.createTriangle(sideA, sideB, sideC)
            assertEquals(sqrt(p * (p - sideA) * (p - sideB) * (p - sideC)), testTriangle.calcArea())
            assertEquals(sideA + sideB + sideC, testTriangle.calcPerimeter())
        }
    }

    @Test
    fun randomCircleTest() {
        val testFactory = ShapeFactoryImpl()
        var testCircle = testFactory.createRandomCircle()
        for (i in 1..50) {
            assert(testCircle.radius > 0)
            testCircle = testFactory.createRandomCircle()
        }
    }

    @Test
    fun randomSquareTest() {
        val testFactory = ShapeFactoryImpl()
        var testSquare = testFactory.createRandomSquare()
        for (i in 1..50) {
            assert(testSquare.side > 0)
            testSquare = testFactory.createRandomSquare()
        }
    }

    @Test
    fun randomRectangleTest() {
        val testFactory = ShapeFactoryImpl()
        var testRect = testFactory.createRandomRectangle()
        for (i in 1..50) {
            assert(testRect.sideA > 0 && testRect.sideB > 0)
            testRect = testFactory.createRandomRectangle()
        }
    }

    @Test
    fun randomTriangleTest() {
        val testFactory = ShapeFactoryImpl()
        var testTriangle = testFactory.createRandomTriangle()
        for (i in 1..50) {
            assert(testTriangle.sideA < testTriangle.sideB + testTriangle.sideC)
            assert(testTriangle.sideB < testTriangle.sideA + testTriangle.sideC)
            assert(testTriangle.sideC < testTriangle.sideA + testTriangle.sideB)
            testTriangle = testFactory.createRandomTriangle()
        }
    }

    @Test
    fun randomShapeTest() {
        val testFactory = ShapeFactoryImpl()
        var testShape: Shape = testFactory.createRandomShape()
        var circles = 0
        var squares = 0
        var rectangles = 0
        var triangles = 0
        for (i in 1..100) {
            when (testShape) {
                is Circle -> circles++
                is Square -> squares++
                is Rectangle -> rectangles++
                is Triangle -> triangles++
            }
            testShape = testFactory.createRandomShape()
        }
        assert(circles > 0)
        assert(squares > 0)
        assert(rectangles > 0)
        assert(triangles > 0)
    }
}