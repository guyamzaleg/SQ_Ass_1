package ac.il.bgu.qa;

import ac.il.bgu.qa.errors.*;
import ac.il.bgu.qa.services.*;;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class TestLibrary {

    @Mock
     DatabaseService DBS;
    @Mock
    ReviewService RS;
    @Mock
    Book book;
    @Mock
    User user;
    @Spy
    List<String> reviews = new ArrayList<>();

//    @BeforeAll
//    public static void testDBService() {
//        assertNotNull(DBS , "DatabaseService is null");
//    }
//    @BeforeAll
//    public static void testReviewService() {
//        assertNotNull(RS , "ReviewService is null");
//    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLibraryConstructor()
    {
        Library library = new Library(DBS , RS);
        assertNotNull(library , "Library is null");
    }

    //========================================Test addBook========================================
    @Test
    public void GivenNullBook_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(null) , "Book is null but no exception thrown");
    }
    //----------------ISBN----------------
    @Test
    public void GivenBookWithNullISBN_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn(null);
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is null but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());

    }
    @Test
    public void GivenBookWithEmptyISBN_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is Empty but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidISBN1_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("-------------");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidISBN2_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("12-4567890123");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }

    @Test
    public void GivenBookWithInvalidISBN3_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("1234567890ABC");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidISBN4_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("123456789012");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book), "Book ISBN length is not 13 but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidISBN5_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("1111-1-1-1-111-1-1_6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book), "Book ISBN  is not valid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithValidAndLegalISBN1_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("1-1-1-1-1-1-1-1-1-1-1-1-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book ISBN is valid and legal but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);

    }
    @Test
    public void GivenBookWithValidAndLegalISBN2_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("1111111111116");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book ISBN is valid and legal but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }




    //----------------Title----------------
    @Test
    public void GivenBookWithNullTitle_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn(null);
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book title is null but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithEmptyTitle_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book title is Empty but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithValidTitle_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("title");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book title is valid but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }
    //----------------Author----------------
    @Test
    public void GivenBookWithNullAuthor_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn(null);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is null but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithEmptyAuthor_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is Empty but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithValidAuthor1_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("legal-author");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book , atLeastOnce()).getAuthor();
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }
    @Test
    public void GivenBookWithValidAuthor2_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("l-e-g-a-l-a-u-t-h-o-r");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book , atLeastOnce()).getAuthor();
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }
    @Test
    public void GivenBookWithValidAuthor3_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("l'e'g'a'l'a'u't'h'o'r");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book , atLeastOnce()).getAuthor();
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }
    @Test
    public void GivenBookWithValidAuthor4_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("legal author");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book , atLeastOnce()).getAuthor();
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }
    @Test
    public void GivenBookWithValidAuthor5_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("l.egal author");
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book author is valid but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book , atLeastOnce()).getAuthor();
        verify(DBS).getBookByISBN(book.getISBN());
        verify(DBS).addBook(book.getISBN(),book);
    }

    @Test
    public void GivenBookWithInvalidAuthor1_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegalauthor ");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book,never()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidAuthor2_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegal--author");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book,never()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidAuthor3_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegal/author");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book,never()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidAuthor4_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("-Illegalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book,never()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidAuthor5_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("[Illegalauthor");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book,never()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenBookWithInvalidAuthor6_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegal\\author");
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book,never()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }

    //-----Borrowed-----
    @Test
    public void GivenBorrowedBook_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        when(book.isBorrowed()).thenReturn(true);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book is borrowed but exception thrown");
        //verify for not reaching the addBook method of the DBS
        verify(book, atLeastOnce()).getISBN();
        verify(book , atLeastOnce()).getTitle();
        verify(book , atLeastOnce()).getAuthor();
        verify(book , atLeastOnce()).isBorrowed();
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
    }
    @Test
    public void GivenNotBorrowedBook_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        when(book.isBorrowed()).thenReturn(false);
        //Action & Assertion
        assertDoesNotThrow(() -> library.addBook(book) , "Book is not borrowed but exception thrown");
        //verify for reaching the addBook method of the DBS
        verify(DBS , atLeastOnce()).addBook(book.getISBN(),book);
        verify(DBS , atLeastOnce()).getBookByISBN(book.getISBN());

    }



    //========================================Test registerUser========================================
    @Test
    public void GivenNullUser_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(null) , "User is null but no exception thrown");
        //verify for not reaching the next methods
        verify(user , never()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }
    //----------------ID----------------
    @Test
    public void GivenUserWithNullID_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS, RS);
        //Stubbing
        when(user.getId()).thenReturn(null);
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class, () -> library.registerUser(user), "User ID is null but no exception thrown");

        //verify for not reaching the next methods
        verify(user , atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }
    @Test
    public void GivenUserWithInvalidID1_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));

    }
    @Test
    public void GivenUserWithInvalidID2_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("12345678901");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }
    @Test
    public void GivenUserWithInvalidID3_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("12345678901_");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }
    @Test
    public void GivenUserWithInvalidID4_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("abcdefghijkl");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }
    @Test
    public void GivenUserWithInvalidID5_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("1a2a3a4a5a6a");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }
    @Test
    public void GivenUserWithInvalidID6_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("123_56789012");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString(),any(User.class));
    }

    @Test
    public void GivenUserWithValidID1_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("123456789012");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , atLeastOnce()).getName();
        verify(user , atLeastOnce()).getNotificationService();
        verify(DBS , atLeastOnce()).getUserById(user.getId());
        verify(DBS , atLeastOnce()).registerUser(user.getId(),user);

    }
    @Test
    public void GivenUserWithValidID2_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("010101010101");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , atLeastOnce()).getName();
        verify(user , atLeastOnce()).getNotificationService();
        verify(DBS , atLeastOnce()).getUserById(user.getId());
        verify(DBS , atLeastOnce()).registerUser(user.getId(),user);
    }
    @Test
    public void GivenUserWithValidID3_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("123123123123");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , atLeastOnce()).getName();
        verify(user , atLeastOnce()).getNotificationService();
        verify(DBS , atLeastOnce()).getUserById(user.getId());
        verify(DBS , atLeastOnce()).registerUser(user.getId(),user);
    }
    @Test
    public void GivenUserWithValidID4_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("111111111111");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
        //verify for not reaching the next methods
        verify(user ,atLeastOnce()).getId();
        verify(user , atLeastOnce()).getName();
        verify(user , atLeastOnce()).getNotificationService();
        verify(DBS , atLeastOnce()).getUserById(user.getId());
        verify(DBS , atLeastOnce()).registerUser(user.getId(),user);
    }
//    //----------------Name----------------
    @Test
    public void GivenUserWithNullName_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("123456789012");
        when(user.getName()).thenReturn(null);
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User name is null but no exception thrown");
        //verify for not reaching the next methods
        verify(user,atLeastOnce()).getId();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).registerUser(user.getId(),user);
        verify(DBS , never()).getUserById(user.getId());

    }
    @Test
    public void GivenUserWithEmptyName_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("123456789012");
        when(user.getName()).thenReturn("");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User name is empty but no exception thrown");
        //verify for not reaching the next methods
        verify(user,atLeastOnce()).getId();
        verify(user , never()).getNotificationService();
        verify(DBS , never()).registerUser(user.getId(),user);
        verify(DBS , never()).getUserById(user.getId());
    }
    @Test
    public void GivenUserWithValidName1_WhenRegisterUser_ThenAddUser() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn("123456789012");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //Action & Assertion
        assertDoesNotThrow(() -> library.registerUser(user) , "User name is valid but exception thrown");
        //verify for reaching the next methods
        verify(user,atLeastOnce()).getId();
        verify(user , atLeastOnce()).getName();
        verify(user , atLeastOnce()).getNotificationService();
        verify(DBS , atLeastOnce()).registerUser(user.getId(),user);
        verify(DBS , atLeastOnce()).getUserById(user.getId());
    }

    //----------------NotificationService----------------
    @Test
    public void GivenUserWithNullNotificationService_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS, RS);
        //Stubbing
        when(user.getId()).thenReturn("123456789012");
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(null);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class, () -> library.registerUser(user), "User NotificationService is null but no exception thrown");
        //verify for not reaching the next methods
        verify(user, atLeastOnce()).getId();
        verify(user, atLeastOnce()).getName();
        verify(user, atLeastOnce()).getNotificationService();
        verify(DBS, never()).registerUser(user.getId(), user);
        verify(DBS, never()).getUserById(user.getId());
    }

    //========================================Test borrowBook========================================
    //----------------ISBN----------------
    @Test
    public void GivenNullISBN_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook(null , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenEmptyISBN_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN1_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("1234567890123" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN2_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("123456789012_" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN3_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("-------------" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN4_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("123456789012" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN5_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("1111-1-1-1-111-1-1_6" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN6_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("1234567890ABC" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidISBN7_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("12-4567890123" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }

    //----------------DBS Return Null----------------
    @Test
    public void GivenDBSReturnNullBook_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(null);
        //Action & Assertion
        assertThrows(BookNotFoundException.class , () -> library.borrowBook("111-111-111-111-6" , "123456789012") , "DBS return null but BookNotFoundException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }

    //----------------User----------------
    @Test
    public void GivenNullUserId_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , null) , "UserId is null but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenEmptyUserId_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , "") , "UserId is empty but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidUserId1_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , "1234567890123") , "UserId is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidUserId2_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , "12345678901") , "UserId is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidUserId3_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , "12345678901_") , "UserId is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidUserId4_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , "123456A78901") , "UserId is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    @Test
    public void GivenInvalidUserId5_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.borrowBook("111-111-111-111-6" , "1234567890.3") , "UserId is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    //----------------DBS Return Null----------------
    @Test
    public void GivenDBSReturnNullUser_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(null);
        //Action & Assertion
        assertThrows(UserNotRegisteredException.class , () -> library.borrowBook("111-111-111-111-6" , "123456789012") , "DBS return null but UserNotRegisteredException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , atLeastOnce()).getUserById("123456789012");
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    //----------------DBS Return Borrowed----------------
    @Test
    public void GivenDBSReturnBorrowedBook_WhenBorrowBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(user);
        when(book.isBorrowed()).thenReturn(true);
        //Action & Assertion
        assertThrows(BookAlreadyBorrowedException.class , () -> library.borrowBook("111-111-111-111-6" , "123456789012") , "DBS return borrowed book but BookAlreadyBorrowedException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , atLeastOnce()).getUserById("123456789012");
        verify(DBS , never()).borrowBook(anyString(),anyString());
    }
    //----------------Legal Scenario----------------
    @Test
    public void GivenDBSReturnLegal1_WhenBorrowBook_ThenBorrowBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(user);
        when(book.isBorrowed()).thenReturn(false);
        //Action & Assertion
        assertDoesNotThrow(() -> library.borrowBook("111-111-111-111-6" , "123456789012") , "DBS return not borrowed book but exception thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , atLeastOnce()).getUserById("123456789012");
        verify(DBS , atLeastOnce()).borrowBook("111-111-111-111-6","123456789012");
    }

    //========================================Test returnBook========================================
    //----------------ISBN----------------
    @Test
    public void GivenNullISBN_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook(null) , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenEmptyISBN_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenInvalidISBN1_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("1234567890123") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenInvalidISBN2_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("123456789012_") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenInvalidISBN3_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("-------------") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenInvalidISBN4_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenInvalidISBN5_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("1111-1-1-1-111-1-1_6") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    @Test
    public void GivenInvalidISBN6_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.returnBook("1234567890ABC") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    //----------------DBS Return Null----------------
    @Test
    public void GivenDBSReturnNullBook_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(null);
        //Action & Assertion
        assertThrows(BookNotFoundException.class , () -> library.returnBook("111-111-111-111-6") , "DBS return null but BookNotFoundException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    //----------------DBS return not borrowed----------------
    @Test
    public void GivenDBSReturnNotBorrowedBook_WhenReturnBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(book.isBorrowed()).thenReturn(false);
        //Action & Assertion
        assertThrows(BookNotBorrowedException.class , () -> library.returnBook("111-111-111-111-6") , "DBS return not borrowed book but BookNotBorrowedException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).returnBook(anyString());
    }
    //----------------Legal Scenario----------------
    @Test
    public void GivenDBSReturnLegal_WhenReturnBook_ThenReturnBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(book.isBorrowed()).thenReturn(true);
        //Action & Assertion
        assertDoesNotThrow(() -> library.returnBook("111-111-111-111-6") , "DBS return borrowed book but exception thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , never()).getUserById(anyString());
        verify(book , atLeastOnce()).isBorrowed();
        verify(book , atLeastOnce()).returnBook();
        verify(DBS , atLeastOnce()).returnBook("111-111-111-111-6");
    }

    //======================================== Test notifyUseWithBookReviews========================================
    //----------------ISBN----------------
    @Test
    public void GivenInvalidISBN1_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews(null , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidISBN2_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidISBN3_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("1234567890123" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidISBN4_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("123456789012_" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidISBN5_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("-------------" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidISBN6_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-1" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }

    //----------------UserID----------------
    @Test
    public void GivenInvalidUserID1_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , null) , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidUserID2_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidUserID3_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "1234567890123") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidUserID4_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "12345678901") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    @Test
    public void GivenInvalidUserID5_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "12345678901_") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
        verify(RS , never()).getReviewsForBook(anyString());
    }
    //----------------DBS Return Null Book----------------
    @Test
    public void GivenDBSReturnNullBook_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(null);
        //Action & Assertion
        assertThrows(BookNotFoundException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "123456789012") , "DBS return null but BookNotFoundException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(RS , never()).getReviewsForBook(anyString());
    }
    //----------------DBS Return Null User----------------
    @Test
    public void GivenDBSReturnNullUser_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(null);
        //Action & Assertion
        assertThrows(UserNotRegisteredException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "123456789012") , "DBS return null but UserNotRegisteredException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , atLeastOnce()).getUserById("123456789012");
        verify(RS , never()).getReviewsForBook(anyString());
    }

    //----------------RS Return Null----------------
    @Test
    public void GivenRSReturnNull_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(user);
        when(RS.getReviewsForBook("111-111-111-111-6")).thenReturn(null);
        //Action & Assertion
        //TODO: check if the exception is the right one - look - line 286 inside Library class
        assertThrows(NoReviewsFoundException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "123456789012") , "RS return null but NoReviewsFoundException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , atLeastOnce()).getUserById("123456789012");
        verify(RS , atLeastOnce()).getReviewsForBook("111-111-111-111-6");
    }
    @Test
    public void GivenRSReturnNull_WhenNotifyUserWithBookReviews_ThenRSClosed() {
        //Arrange
        Library library = new Library(DBS, RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(user);
        when(RS.getReviewsForBook("111-111-111-111-6")).thenReturn(null);;
        //Action & Assertion
        assertThrows(NoReviewsFoundException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "123456789012") , "RS return null but NoReviewsFoundException isn't thrown");
        //verify for closing the RS
        verify(RS, atLeastOnce()).close();
    }


    @Test
    public void GivenRSReturnEmpty_WhenNotifyUserWithBookReviews_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        reviews.clear();
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(user);
        when(RS.getReviewsForBook("111-111-111-111-6")).thenReturn(reviews);
        when(reviews.isEmpty()).thenReturn(true);
        //Action & Assertion
        assertThrows(NoReviewsFoundException.class , () -> library.notifyUserWithBookReviews("111-111-111-111-6" , "123456789012") , "RS return empty but NoReviewsFoundException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS , atLeastOnce()).getUserById("123456789012");
        verify(RS , atLeastOnce()).getReviewsForBook("111-111-111-111-6");
    }
//    @Test
//    public void GivenUserSendNotificationThrows_WhenNotifyUserWithBookReviews_ThenthrowNotificationException() {
//        //Arrange
//        Library library = new Library(DBS, RS);
//        reviews.add("Review1");
//        reviews.add("Review2");
//        reviews.add("Review3");
//        //Stubbing
//        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
//        when(DBS.getUserById("123456789012")).thenReturn(user);
//        when(RS.getReviewsForBook("111-111-111-111-6")).thenReturn(reviews);
//        when(user.sendNotification()).thenThrow(new NotificationException("NotificationException"));
//        //Action & Assertion
//        assertDoesNotThrow(() -> library.notifyUserWithBookReviews("111-111-111-111-6", "123456789012"), "RS return legal but exception thrown");
//        //verify for reaching the next methods
//        verify(DBS, atLeastOnce()).getBookByISBN("111-111-111-111-6");
//        verify(DBS, atLeastOnce()).getUserById("123456789012");
//        verify(RS, atLeastOnce()).getReviewsForBook("111-111-111-111-6");
//        verify(user, atLeastOnce()).sendNotification(anyString());
//    }

    //----------------Legal Scenario----------------
    @Test
    public void GivenRSReturnLegal_WhenNotifyUserWithBookReviews_ThenNotifyUser() {
        //Arrange
        Library library = new Library(DBS, RS);
        reviews.add("Review1");
        reviews.add("Review2");
        reviews.add("Review3");
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(DBS.getUserById("123456789012")).thenReturn(user);
        when(RS.getReviewsForBook("111-111-111-111-6")).thenReturn(reviews);
        //Action & Assertion
        assertDoesNotThrow(() -> library.notifyUserWithBookReviews("111-111-111-111-6", "123456789012"), "RS return legal but exception thrown");
        //verify for reaching the next methods
        verify(DBS, atLeastOnce()).getBookByISBN("111-111-111-111-6");
        verify(DBS, atLeastOnce()).getUserById("123456789012");
        verify(RS, atLeastOnce()).getReviewsForBook("111-111-111-111-6");
        verify(user, atLeastOnce()).sendNotification(anyString());
    }
//======================================== Test getBookByISBN========================================
    //----------------ISBN----------------
    @Test
    public void GivenNullISBN_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN(null , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenEmptyISBN_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidISBN1_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("1234567890123" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidISBN2_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("123456789012_" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidISBN3_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("-------------" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidISBN4_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("123456789012" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidISBN5_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("1111-1-1-1-111-1-1_6" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidISBN6_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("1234567890ABC" , "123456789012") , "ISBN is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    //----------------User ID----------------
    @Test
    public void GivenNullUserID_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("111-111-111-111-6" , null) , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenEmptyUserID_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("111-111-111-111-6" , "") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidUserID1_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("111-111-111-111-6" , "1234567890123") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidUserID2_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("111-111-111-111-6" , "12345678901") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidUserID3_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("111-111-111-111-6" , "12345678901_") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    @Test
    public void GivenInvalidUserID4_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.getBookByISBN("111-111-111-111-6" , "123456A78901") , "UserID is invalid but no exception thrown");
        //verify for not reaching the next methods
        verify(DBS , never()).getBookByISBN(anyString());
    }
    //----------------DBS Return Null----------------
    @Test
    public void GivenDBSReturnNull_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(null);
        //Action & Assertion
        assertThrows(BookNotFoundException.class , () -> library.getBookByISBN("111-111-111-111-6" , "123456789012") , "DBS return null but BookNotFoundException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
    }
    //----------------DBS return already borrowed----------------
    @Test
    public void GivenDBSReturnBorrowedBook_WhenGetBookByISBN_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(DBS.getBookByISBN("111-111-111-111-6")).thenReturn(book);
        when(book.isBorrowed()).thenReturn(true);
        //Action & Assertion
        assertThrows(BookAlreadyBorrowedException.class , () -> library.getBookByISBN("111-111-111-111-6" , "123456789012") , "DBS return borrowed book but BookAlreadyBorrowedException isn't thrown");
        //verify for reaching the next methods
        verify(DBS , atLeastOnce()).getBookByISBN("111-111-111-111-6");
    }







}
