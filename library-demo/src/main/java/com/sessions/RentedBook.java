package com.sessions;

import java.time.LocalDate;

public class RentedBook {



    private Book book;
    private LocalDate rentedDate;

    public RentedBook(Book book) {

        this.book = book;
        this.rentedDate = LocalDate.now();
    }

    public LocalDate getRentedDate() {
        return this.rentedDate;
    }
    //mock get rented method to test > 5 days condition

    public Book getBook() {
        return book;
    }
}
