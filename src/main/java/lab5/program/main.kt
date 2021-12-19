package lab5.program

import lab5.program.library.*
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import java.time.Year

val logger: Logger = LogManager.getLogger("Main function")

fun main() {
    try {
        val library = LibraryServiceImpl()
        val user1 = User("Ivan Ivanov")
        val user2 = User("Petr Petrov")
        val user3 = User("Alexandr Alexandrov")

        library.registerUser(user1)
        library.registerUser(user2)
        library.registerUser(user3)

        println(library.getAllBooks())

        library.addBook(Book("Avengers", Year.parse("2012"), Author("Stan Lee"), Genre.Adventures))
        library.addBook(Book("Sherlock Holmes", Year.parse("1880"), Author("Conan Doyle"), Genre.Detectives))
        library.addBook(
            Book(
                "7 skills of highly effective people",
                Year.parse("1989"),
                Author("Steven Kofi"),
                Genre.BusinessLiterature
            )
        )
        library.addBook(Book("Omagad", Year.parse("2005"), Author("MDA MDA"), Genre.Dramaturgy))


        println(library.getAllAvailableBooks())

        library.takeBook(user1, library.getAllBooks()[0])
        //library.takeBook(User("Petr Petrov"), library.getAllBooks()[0])
        library.takeBook(user1, library.getAllAvailableBooks()[0])
        library.takeBook(user1, library.getAllAvailableBooks()[0])

        println(library.getAllAvailableBooks())
        library.returnBook(library.findBooks("Aven")[0])
        library.returnBook(library.findBooks("Omag")[0])
        println(library.getAllAvailableBooks())

        library.takeBook(user2, library.getAllAvailableBooks()[0])
        library.takeBook(user2, library.getAllAvailableBooks()[0])
        library.takeBook(user2, library.getAllAvailableBooks()[0])
        println(library.getAllAvailableBooks())

    } catch (e: Exception) {
        logger.warn("exception has happen", e)
    }
}