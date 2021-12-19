package lab5.program.library

import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import java.time.Year


data class Book(val name: String, val year: Year, val author: Author, val genre: Genre) {
    override fun toString(): String {
        return "'$name' ($year, $author, ${genre.name})"
    }
}

data class Author(val fullName: String) {
    val firstName = fullName.substringBefore(' ')
    val secondName = fullName.substringAfter(' ')
    override fun toString(): String {
        return fullName
    }
}

data class User(val fullName: String, var countBooks: Int = 0) {
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

class LibraryServiceImpl(val maxCountBooks: Int = 3) : LibraryService {
    private val logger: Logger = LogManager.getLogger(this.javaClass.name)

    private val userList = ArrayList<User>()
    private val bookMap = HashMap<Book, Status>()

    override fun findBooks(substring: String): List<Book> {
        return bookMap.filterKeys { it.name.contains(substring) }.keys.toList()
    }

    override fun findBooks(author: Author): List<Book> {
        return bookMap.filterKeys { it.author == author }.keys.toList()
    }

    override fun findBooks(year: Year): List<Book> {
        return bookMap.filterKeys { it.year == year }.keys.toList()
    }

    override fun findBooks(genre: Genre): List<Book> {
        return bookMap.filterKeys { it.genre == genre }.keys.toList()
    }

    override fun getAllBooks(): List<Book> {
        return bookMap.keys.toList()
    }

    override fun getAllAvailableBooks(): List<Book> {
        return bookMap.filterValues { it is Status.Available }.keys.toList()
    }

    override fun getBookStatus(book: Book): Status {
        return bookMap[book]!!
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return HashMap<Book, Status>(bookMap)
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (!bookMap.contains(book)) throw IllegalArgumentException("Library hasn't that book")

        bookMap[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        bookMap[book] = status
    }

    override fun registerUser(user: User) {
        if (userList.contains(user)) throw IllegalArgumentException("User is already registered in library")
        userList.add(user)
    }

    override fun unregisterUser(user: User) {
        userList.remove(user)
    }

    override fun takeBook(user: User, book: Book) {
        val userIndex = userList.indexOf(user)
        if (userIndex == -1) throw IllegalArgumentException("The user is not registered in the library")
        if (!bookMap.contains(book)) throw IllegalArgumentException("Library hasn't that book")
        if (user.countBooks >= maxCountBooks) throw IllegalArgumentException("The user can't have more than $maxCountBooks")
        if (bookMap[book] !is Status.Available) throw java.lang.IllegalArgumentException("Book isn't available")

        bookMap[book] = Status.UsedBy(user)
        user.countBooks++
    }

    override fun returnBook(book: Book) {
        if (!bookMap.contains(book))
            throw IllegalArgumentException("Library hasn't that book")

        var user = User("Nothing nothing", 0)

        val status = bookMap[book]
        if (status is Status.UsedBy) {
            status.user.countBooks--
            user = status.user
        }
        bookMap[book] = Status.Available
    }
}