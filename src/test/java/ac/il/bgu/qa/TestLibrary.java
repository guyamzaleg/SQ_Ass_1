package ac.il.bgu.qa;

import ac.il.bgu.qa.errors.*;
import ac.il.bgu.qa.services.*;;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.mockito.*;

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

        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());

        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is null but no exception thrown");
    }
    @Test
    public void GivenBookWithEmptyISBN_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is Empty but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN1_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("-------------");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN2_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("12-4567890123");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
    }

    @Test
    public void GivenBookWithInvalidISBN3_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("1234567890ABC");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book ISBN is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN4_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("123456789012");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book), "Book ISBN length is not 13 but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidISBN5_WhenAddBook_ThenAddBook() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("1111-1-1-1-111-1-1_6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class, () -> library.addBook(book), "Book ISBN  is not valid but no exception thrown");
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
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book title is null but no exception thrown");
    }
    @Test
    public void GivenBookWithEmptyTitle_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("");
        when(book.getAuthor()).thenReturn("Legalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book title is Empty but no exception thrown");
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
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is null but no exception thrown");
    }
    @Test
    public void GivenBookWithEmptyAuthor_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is Empty but no exception thrown");
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
    }

    @Test
    public void GivenBookWithInvalidAuthor1_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegalauthor ");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor2_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegal--author");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor3_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegal/author");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor4_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("-Illegalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor5_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("[Illegalauthor");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
    }
    @Test
    public void GivenBookWithInvalidAuthor6_WhenAddBook_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(book.getISBN()).thenReturn("111-111-111-111-6");
        when(book.getTitle()).thenReturn("Legaltitle");
        when(book.getAuthor()).thenReturn("Illegal\\author");
        //verify for not reaching the addBook method of the DBS
        verify(DBS , never()).addBook(book.getISBN(),book);
        verify(DBS , never()).getBookByISBN(book.getISBN());
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.addBook(book) , "Book author is invalid but no exception thrown");
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
    }



    //========================================Test registerUser========================================
    @Test
    public void GivenNullUser_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(null) , "User is null but no exception thrown");
    }
    //----------------ID----------------
    @Test
    public void GivenUserWithNullID_WhenRegisterUser_ThenThrowException() {
        //Arrange
        Library library = new Library(DBS , RS);
        //Stubbing
        when(user.getId()).thenReturn(null);
        when(user.getName()).thenReturn("Legalname");
        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
        //verify for not reaching the next methods
        //TODO : verify isnt working as expected
        verify(DBS , never()).getUserById(anyString());
        verify(DBS , never()).registerUser(anyString() , any(User.class));
        verify(user , never()).getName();
        verify(user , never()).getNotificationService();
        //Action & Assertion
        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is null but no exception thrown");
    }
//    @Test
//    public void GivenUserWithInvalidID1_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser( , anyString());
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getName();
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithInvalidID2_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("12345678901");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getName();
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithInvalidID3_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("12345678901_");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getName();
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithInvalidID4_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("abcdefghijkl");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getName();
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
//    }  @Test
//    public void GivenUserWithInvalidID5_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("1a2a3a4a5a6a");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getName();
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithInvalidID6_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123_56789012");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getName();
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User ID is Empty but no exception thrown");
//    }
//
//    @Test
//    public void GivenUserWithValidID1_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123456789012");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        verify(DBS , never()).getUserById(anyString());
//        verify(DBS , never()).registerUser(anyString() , any(User.class));
//        //Action & Assertion
//        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
//    }
//    @Test
//    public void GivenUserWithValidID2_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("010101010101");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //Action & Assertion
//        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
//    }
//    @Test
//    public void GivenUserWithValidID3_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123123123123");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //Action & Assertion
//        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
//    }
//    @Test
//    public void GivenUserWithValidID4_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("111111111111");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //Action & Assertion
//        assertDoesNotThrow(() -> library.registerUser(user) , "user is valid but exception thrown");
//    }
//    //----------------Name----------------
//    @Test
//    public void GivenUserWithNullName_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123456789012");
//        when(user.getName()).thenReturn(null);
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User name is null but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithEmptyName_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123456789012");
//        when(user.getName()).thenReturn("");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(DBS , never()).registerUser(user.getId(),user);
//        verify(DBS , never()).getUserById(user.getId());
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User name is Empty but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithInvalidName1_WhenRegisterUser_ThenThrowException() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123456789012");
//        when(user.getName()).thenReturn("Illegalname ");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //verify for not reaching the next methods
//        verify(user , never()).getNotificationService();
//        //Action & Assertion
//        assertThrows(IllegalArgumentException.class , () -> library.registerUser(user) , "User name is invalid but no exception thrown");
//    }
//    @Test
//    public void GivenUserWithValidName1_WhenRegisterUser_ThenAddUser() {
//        //Arrange
//        Library library = new Library(DBS , RS);
//        //Stubbing
//        when(user.getId()).thenReturn("123456789012");
//        when(user.getName()).thenReturn("Legalname");
//        when(user.getNotificationService()).thenReturn(Mockito.mock(NotificationService.class));
//        //Action & Assertion
//        assertDoesNotThrow(() -> library.registerUser(user) , "User name is valid but exception thrown");
//    }




//    @Test
//    public void testLibraryAddBook() {
//        Library library = new Library(DBS , RS);
//        Book book = Mockito.mock(Book.class);
//        library.addBook(book);
//        verify(DBS).addBook(book);
//    }


//    public static void main(String[] args)
//    {
//        Library library = new Library(DBS , RS);
//        Book book = new Book("1111111111116" , "Title" , "author-name");
//        library.addBook(book);
//    }


}
