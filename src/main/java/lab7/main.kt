package lab7

import lab7.FileManager
import lab3.program.shapes.*

fun main() {
    val serializationShapes = SerializationShapes()
    val shapeFactory = ShapeFactoryImpl()
    val fileManager = FileManager()
    val shapeList = mutableListOf<Shape>()
    for (i in 0..5)
        shapeList.add(shapeFactory.createRandomShape())
    fileManager.writeTextIntoFile("output.json", serializationShapes.encodeToJSON(shapeList))
    val secondShapeList = serializationShapes.decodeFromJSON(fileManager.readTextFromFile("output.json"))

    println(shapeList == secondShapeList)
}