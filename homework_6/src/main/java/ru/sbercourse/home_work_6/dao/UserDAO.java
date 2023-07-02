package ru.sbercourse.home_work_6.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.sbercourse.home_work_6.DBConnection;

import java.sql.*;
import java.time.LocalDate;

@Component
@Scope("prototype")
public final class UserDAO {
    private DBConnection dbConnection;
    private final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);


    public void addUser(String lastName, String firstName, LocalDate birthDate,
                        String phone, String mail, String... bookTitles) {
        try (Connection connection = dbConnection.getConnection()) {
            String sql = "insert into home_work_6.users (last_name, first_name, birth_date, phone, mail, book_titles)" +
                    "values (?, ?, ?, ?, ?, ?)";
            PreparedStatement insertQuery = connection.prepareStatement(sql);
            insertQuery.setString(1, lastName);
            insertQuery.setString(2, firstName);
            insertQuery.setDate(3, Date.valueOf(birthDate));
            insertQuery.setString(4, phone);
            insertQuery.setString(5, mail);
            Array arrayBookTitles = connection.createArrayOf("text", bookTitles);
            insertQuery.setArray(6, arrayBookTitles);

            insertQuery.executeUpdate();
            LOGGER.info("New user added: {}, {}, {}, {}, {}, {}",
                    lastName, firstName, birthDate, phone, mail, bookTitles);
        } catch (Exception e) {
            LOGGER.error("Adding a user to the database failed.", e);
        }
    }

    public String[] getUserBooks(String mail) {
        String[] bookTitles = null;

        try (Connection connection = dbConnection.getConnection()) {
            PreparedStatement selectQuery = connection.prepareStatement(
                    "select * from home_work_6.users where mail=?");
            selectQuery.setString(1, mail);

            ResultSet resultSet = selectQuery.executeQuery();

            if (resultSet.next()) {
                bookTitles = (String[]) resultSet.getArray("book_titles").getArray();
                if (bookTitles.length == 0) {
                    LOGGER.info("The user with mail = '{}' has no books.", mail);
                } else {
                    LOGGER.info("Book titles found for a user with mail = '{}': {}", mail, bookTitles);
                }
            } else {
                LOGGER.info("User with mail = '{}' not found.", mail);
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get a list of books from the database", e);
        }
        return bookTitles;
    }


    @Autowired
    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
}
/*
    public void getUserByMail(String mail) {
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement selectQuery = connection.prepareStatement("select * from home_work_6.users where mail=?");
            selectQuery.setString(1, mail);
            ResultSet resultSet = selectQuery.executeQuery();
            if (resultSet.next()) {
                user.setLastName(resultSet.getString("last_name"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setBirthDate(resultSet.getDate("birth_date"));
                user.setPhone(resultSet.getString("phone"));
                user.setMail(resultSet.getString("mail"));
                user.setBookTitles((String[])resultSet.getArray("book_titles").getArray());
                LOGGER.info("Get user by mail = '{}': {}", mail, user);
            }
        }
        catch (SQLException e) {
            LOGGER.error("Unexpected exception", e);
        }
    }
 */