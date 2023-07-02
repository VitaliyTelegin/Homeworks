package ru.sbercourse.home_work_6.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Book {
    private Integer id;
    private String title;
    private String author;
    private LocalDateTime dateAdded;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }


    @Override
    public String toString() {
        return "Book {" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", dateAdded = " + dateAdded +
                '}';
    }
}
