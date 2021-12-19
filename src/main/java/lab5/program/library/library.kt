package lab5.program.library

import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import java.time.Year


data class Book(val name: String, val year: Year, val author: Author, val genre: Genre){
    override fun toString(): String {
        return "'$name' ($year, $author, ${genre.name})"
    }
}
data class Author(val fullName: String){
    val firstName = fullName.substringBefore(' ')
    val secondName = fullName.substringAfter(' ')
    override fun toString(): String {
        return fullName
    }
}
data class User(val fullName: String, var countBooks : Int = 0){
    val firstName = fullName.substringBefore(' ')
    val secondName = fullName.substringAfter(' ')
    override fun toString(): String {
        return fullName
    }
}


enum class Genre {
    BusinessLiterature,
    Detectives,
    Thrillers,
    Nonfiction,
    Dramaturgy,
    Art,
    IT,
    RomanceNovels,
    ScienceAndEducation,
    Poetry,
    Adventures,
    Prose,
    Spirituality
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()

    override fun toString(): String {
        return this.javaClass.name
    }
}

interface LibraryService {
    fun findBooks(substring: String): List<Book>
    fun findBooks(author: Author): List<Book>
    fun findBooks(year: Year): List<Book>
    fun findBooks(genre: Genre): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    fun takeBook(user: User, book: Book)
    fun returnBook(book: Book)
}

class LibraryServiceImpl(val maxCountBooks : Int = 3) : LibraryService{
    override fun findBooks(substring: String): List<Book> {
        TODO("Not yet implemented")
    }

    override fun findBooks(author: Author): List<Book> {
        TODO("Not yet implemented")
    }

    override fun findBooks(year: Year): List<Book> {
        TODO("Not yet implemented")
    }

    override fun findBooks(genre: Genre): List<Book> {
        TODO("Not yet implemented")
    }

    override fun getAllBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override fun getAllAvailableBooks(): List<Book> {
        TODO("Not yet implemented")
    }

    override fun getBookStatus(book: Book): Status {
        TODO("Not yet implemented")
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        TODO("Not yet implemented")
    }

    override fun setBookStatus(book: Book, status: Status) {
        TODO("Not yet implemented")
    }

    override fun addBook(book: Book, status: Status) {
        TODO("Not yet implemented")
    }

    override fun registerUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun unregisterUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun takeBook(user: User, book: Book) {
        TODO("Not yet implemented")
    }

    override fun returnBook(book: Book) {
        TODO("Not yet implemented")
    }
}