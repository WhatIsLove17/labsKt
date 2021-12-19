package lab5.program

import lab5.program.library.*
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import java.time.Year

fun main() {
    val puk = LibraryServiceImpl()
    val user1 = User("Ivan Ivanov")
    val user2 = User("Petr Petrov")
    val user3 = User("Alexandr Alexandrov")

    puk.registerUser(user1)
    puk.registerUser(user2)
    puk.registerUser(user3)

    println(puk.getAllBooks())

    puk.addBook(Book("Avengers", Year.parse("2012"), Author("Stan Lee"), Genre.Adventures))
    puk.addBook(Book("Sherlock Holmes", Year.parse("1880"), Author("Conan Doyle"), Genre.Detectives))
    puk.addBook(
        Book(
            "7 skills of highly effective people",
            Year.parse("1989"),
            Author("Steven Kofi"),
            Genre.BusinessLiterature
        )
    )
    puk.addBook(Book("Omagad", Year.parse("2005"), Author("MDA MDA"), Genre.Dramaturgy))


    println(puk.getAllAvailableBooks())

    puk.takeBook(user1, puk.getAllBooks()[0])
    //puk.takeBook(User("Petr Petrov"), puk.getAllBooks()[0])
    puk.takeBook(user1, puk.getAllAvailableBooks()[0])
    puk.takeBook(user1, puk.getAllAvailableBooks()[0])

    println(puk.getAllAvailableBooks())
    puk.returnBook(puk.findBooks("Aven")[0])
    puk.returnBook(puk.findBooks("Omag")[0])
    println(puk.getAllAvailableBooks())

    puk.takeBook(user2, puk.getAllAvailableBooks()[0])
    puk.takeBook(user2, puk.getAllAvailableBooks()[0])
    puk.takeBook(user2, puk.getAllAvailableBooks()[0])
    println(puk.getAllAvailableBooks())

}

}