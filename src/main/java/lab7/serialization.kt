package lab7

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import lab3.program.shapes.*

class SerializationShapes {
    private val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Circle::class)
                subclass(Square::class)
                subclass(Rectangle::class)
                subclass(Triangle::class)
            }
        }
    }

    fun encodeToJSON(shapes: List<Shape>): String {
        return json.encodeToString(shapes)
    }

    fun decodeFromJSON(codeJSON: String): List<Shape> {
        return json.decodeFromString(codeJSON)
    }
}
