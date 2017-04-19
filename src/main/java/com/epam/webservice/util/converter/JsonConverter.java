package com.epam.webservice.util.converter;

import com.epam.webservice.bean.Book;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by Evgeny on 14.04.2017.
 */
public class JsonConverter {
    public static Book toJavaObj(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Book.class);
    }

    public static String toJson(Book book) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(book);
    }

    public static String toJson(List<Book> books) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(books);
    }
}
