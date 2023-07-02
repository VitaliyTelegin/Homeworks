package ru.sbercourse.home_work_6.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbercourse.home_work_6.DBConnection;
import ru.sbercourse.home_work_6.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public final class BookDAO {
    private Book book;
    private DBConnection dbConnection;
    private final Logger LOGGER = LoggerFactory.getLogger(BookDAO.class);


    public void findBooksByTitle(String title) {
        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement selectQuery = connection.prepareStatement(
                    "select * from home_work_6.books where title=?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            selectQuery.setString(1, title);

            ResultSet resultSet = selectQuery.executeQuery();

            if (resultSet.isBeforeFirst()) {
                resultSet.last();
                int rowsNumber = resultSet.getRow();
                resultSet.beforeFirst();
                LOGGER.info("{} book(s) with the title '{}' were found:", rowsNumber, title);
                while (resultSet.next()) {
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setDateAdded(resultSet.getTimestamp("date_added").toLocalDateTime());
                    LOGGER.info("{}", book);
                }
            } else LOGGER.info("Books with the title '{}' were not found.", title);
        } catch (SQLException e) {
            LOGGER.error("Getting books from the database failed", e);
        }
    }


    @Autowired
    private void setBook(Book book) {
        this.book = book;
    }

    @Autowired
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
