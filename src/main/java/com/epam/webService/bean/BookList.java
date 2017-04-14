package com.epam.webService.bean;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Yauheni_Tsitsenkou on 4/14/2017.
 */
@XmlRootElement(name = "bookList")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookList {

    @XmlElement(name = "book")
    private List<Book> bookList;

    public BookList() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookList bookList1 = (BookList) o;

        return bookList != null ? bookList.equals(bookList1.bookList) : bookList1.bookList == null;
    }

    @Override
    public int hashCode() {
        return bookList != null ? bookList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "BookList{" +
                "bookList=" + bookList +
                '}';
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
