package ac.il.bgu.qa;

import ac.il.bgu.qa.errors.*;
import ac.il.bgu.qa.services.*;;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestLibrary {

    static DatabaseService DBS = Mockito.mock(DatabaseService.class);
    static ReviewService RS = Mockito.mock(ReviewService.class);
    @BeforeAll
    public static void testDBService() {
        assertNotNull(DBS , "DatabaseService is null");
    }
    @BeforeAll
    public static void testReviewService() {
        assertNotNull(RS , "ReviewService is null");
    }


    @Test
    public void testLibraryConstructor() {
        Library library = new Library(DBS , RS);
        assertNotNull(library , "Library is null");
    }

    //========================================Test addBook========================================
    @Test
    public void GivenNullBook_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        assertThrows(IllegalArgumentException.class , () -> library.addBook(null) , "Book is null but no exception thrown");
    }
    //----------------ISBN----------------
    @Test
    public void GivenBookWithNullISBN_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book(null , "title" , "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is null but no exception thrown");
    }
    @Test
    public void GivenBookWithEmptyISBN_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("" , "title" , "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is Empty but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN1_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("-------------" , "title" , "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN2_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("12-4567890123" , "title" , "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
    }

    @Test
    public void GivenBookWithInvalidISBN3_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("1234567890ABC" , "title" , "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN4_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("123456789012", "title", "author");
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book), "Book ISBN length is not 13 but no exception thrown");
    }
    @Test
    public void GivenBookWithValidAndLegalISBN1_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("1-1-1-1-1-1-1-1-1-1-1-1-6" , "title" , "author");
        assertDoesNotThrow(() -> library.addBook(book) , "Book ISBN is valid and legal but exception thrown");
    }
    @Test
    public void GivenBookWithValidAndLegalISBN2_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("1111111111116" , "title" , "author");
        assertDoesNotThrow(() -> library.addBook(book) , "Book ISBN is valid and legal but exception thrown");
    }

    @Test
    public void GivenBookWithInvalidAndLegalISBN2_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("1111-1-1-1-111-1-1_6" , "title" , "author");
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book), "Book ISBN  is not valid but no exception thrown");
    }


    //----------------Title----------------
    @Test
    public void GivenBookWithNullTitle_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , null , "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book title is null but no exception thrown");
    }
    @Test
    public void GivenBookWithEmptyTitle_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "", "author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book title is Empty but no exception thrown");
    }
    @Test
    public void GivenBookWithValidTitle_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "author");
        assertDoesNotThrow(() -> library.addBook(book) , "Book title is valid but exception thrown");
    }
    //----------------Author----------------
    @Test
    public void GivenBookWithNullAuthor_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , null);
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is null but no exception thrown");
    }
    @Test
    public void GivenBookWithEmptyAuthor_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is Empty but no exception thrown");
    }
    @Test
    public void GivenBookWithValidAuthor1_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "master-author");
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
    }
    @Test
    public void GivenBookWithValidAuthor2_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "l-e-g-a-l-a-u-t-h-o-r");
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
    }
    @Test
    public void GivenBookWithValidAuthor3_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "l'e'g'a'l'a'u't'h'o'r");
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
    }
    @Test
    public void GivenBookWithValidAuthor4_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "legal author");
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
    }
    @Test
    public void GivenBookWithValidAuthor5_WhenAddBook_ThenAddBook() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "l.egal author");
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
    }

    @Test
    public void GivenBookWithInvalidAuthor1_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "Illegalauthor-");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor2_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "Illegal--author");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor3_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "Illega/lauthor");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor4_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , "Illegalauthor-");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor5_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , " Illegalauthor");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor6_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("111-111-111-111-6" , "title" , " Illega.'lauthor");
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }

    //-----Borrowed-----
    @Test
    public void GivenBorrowedBook_WhenAddBook_ThenThrowException() {
        Library library = new Library(DBS , RS);
        Book book = new Book("ISBN" , "title" , "author");
        book.borrow();
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book is borrowed but exception thrown");
    }

    //------------------Test registerUser------------------


//    @Test
//    public void testLibraryAddBook() {
//        Library library = new Library(DBS , RS);
//        Book book = Mockito.mock(Book.class);
//        library.addBook(book);
//        verify(DBS).addBook(book);
//    }


    public static void main(String[] args)
    {
        Library library = new Library(DBS , RS);
        Book book = new Book("1111111111116" , "Title" , "author-name");
        library.addBook(book);
    }


}
