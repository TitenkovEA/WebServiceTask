package com.epam.webService.util.converter;

import com.epam.webService.bean.Book;
import com.epam.webService.bean.BookList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by Evgeny on 14.04.2017.
 */
public class XmlConverter {
    public static Book toJavaObj(String xml) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (Book) unmarshaller.unmarshal(new StringReader(xml));
    }

    public static String toXml(Book book) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(book, writer);

        return writer.toString();
    }

    public static String toXml(List<Book> books) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(BookList.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        StringWriter writer = new StringWriter();
        BookList bookList = new BookList();
        bookList.setBookList(books);
        marshaller.marshal(bookList, writer);

        return writer.toString();
    }
}
