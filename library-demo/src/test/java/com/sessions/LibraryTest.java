package com.sessions;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

//1st test case book size is 1
class LibraryTest {

    //we have created library object each and every time
    Library library;

    @BeforeAll
    public static void before_all()
    {

        System.out.println("Before all");
    }
    @AfterAll
    public static void after_all()
    {

        System.out.println("AFTER ALL");
    }
    @AfterEach
    public void after_each()
    {

        System.out.println("After each");
    }
    @BeforeEach

    public void setup()
    {
        library = new Library();
        System.out.println("Beforeeach");
    }

    @Test

    public void the_default_number_of_books_inLibrary_Intially_is_1()
    {
     //Library library = new Library(); - commenting since we have created before all
     int total_NO_OF_BOOKS = library.getBooks().size();
     //WHENEVER EQUALS TO = USE A ASSERT EQUALS IN UNIT TESTING
        assertEquals(1,total_NO_OF_BOOKS);
    }

    @Test
    public void adding_to_catalougue_should_increase_the_size_of_books_and_Nely_created_book_id_should_be_2()
    {
        Library library = new Library();
       Book newlyCreatedBook = library.addToCatalogue("Discover of India", "Jawahlral", 432, 11.99);

       int totalbooks = library.getBooks().size();
        List<Book> availableBooks = library.getBooks();


        assertEquals((2), newlyCreatedBook.getId());

        assertThat(totalbooks,equalTo(2));
        //book id and total books is 2
        //newly created books should be part of books
        assertThat(availableBooks,hasItem(newlyCreatedBook));

    }

//findbookbyname has condition - return true success test case - return null failure test cases
    @Test
    public void findBookByName_called_with_bookname_available_in_library_should_return_book_object()
    {
        Library library = new Library();
        Book book = library.findBookByName("The God Of Small Things");
        assertNotNull(book);
    }
    @Test
    public void findBookByName_called_with_non_existent_book_name_should_return_null()
    {
        Library library = new Library();
        Book book = library.findBookByName("Invalid Name");
        //this book name is not available - value should be null
        assertNull(book);
    }
    @Test
    public void calculateBookrent_should_retrun_2_dollars_if_number_of_days_is_4()
    {
        RentedBook rentedBook= Mockito.mock(RentedBook.class);
        //giv us mock objects of rented book - we can mock - get rennted date / get book
        LocalDate fourDaysbeforeToday = LocalDate.now().minusDays(4);
        Mockito.when(rentedBook.getRentedDate()).thenReturn(fourDaysbeforeToday);
        //takes method - when callled - it will return 4 days before
        Double calculatedPrice = library.calculateBookRent(rentedBook);
        assertThat(calculatedPrice,equalTo(2.0));
        //to endure getRented method has been called or not
        Mockito.verify(rentedBook,Mockito.times(2)).getRentedDate();

    }
    @Test
    public void calculateBookrent_should_retrun_2_dollars_if_number_of_days_is_6()
    {
        RentedBook rentedBook= Mockito.mock(RentedBook.class);
        //giv us mock objects of rented book - we can mock - get rennted date / get book
        LocalDate sixDaysbeforeToday = LocalDate.now().minusDays(6);
        Mockito.when(rentedBook.getRentedDate()).thenReturn(sixDaysbeforeToday);
        //takes method - when callled - it will return 6 days before
        Double calculatedPrice = library.calculateBookRent(rentedBook);
        assertThat(calculatedPrice,equalTo(Double.valueOf(6)));
        //to endure getRented method has been called or not
        Mockito.verify(rentedBook,Mockito.times(2)).getRentedDate();
    }

    //mick rented date in rented book function
    //TDD
    //When returning book along with the amount the receipt should be returned, it should have
    //The date of receipt - should be current date
    //book name
    //amountGiven, actualamount
    //balancetobereturned
    //and book should be available again in library
    //if an amount provided is less that the amount
    //an exception should be thrown mentioning lower amount

    @Test
    public void when_returning_book_receipt_should_be_returned(){
        //RentedBook rentedBook=null;
        RentedBook rentedBook=library.rent("The God Of Small Things");
        Double amount =3.0;
        Receipt receipt = library.returnBook(rentedBook,amount);
        assertNotNull(receipt);
        assertThat(receipt.bookName,equalTo("The God Of Small Things"));
        assertThat(receipt.receiptDate,equalTo(LocalDate.now()));
        //test case is faile because book name is null , we have created empty receipt name- so we crate receipt and then
    }
    //compiliation  failure is failue - we have test which is failing
    //to make test case pass - we created class
    //Rent actual book
}