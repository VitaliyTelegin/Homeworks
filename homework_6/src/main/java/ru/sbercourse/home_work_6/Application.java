package ru.sbercourse.home_work_6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbercourse.home_work_6.dao.BookDAO;
import ru.sbercourse.home_work_6.dao.UserDAO;

import java.time.LocalDate;
import java.util.Arrays;

/*
2. Создать класс UserDAO и Бин (prototype) этого класса.
3. Далее нам необходимо заполнить пользователей (т.е. у нас должен быть метод в коде,
который позволяет добавить пользователей в базу)
4. Написать метод, который принимает телефон/почту (на ваше усмотрение), достанет из
UserDAO список названий книг данного человека. С этим списком сходить в BookDAO и
получить всю информацию об этих книгах
 */
//@SpringBootApplication
public final class Application {
    private static final ClassPathXmlApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("beans.xml");
    private static final UserDAO USER_DAO = CONTEXT.getBean(UserDAO.class);
    private static final BookDAO BOOK_DAO = CONTEXT.getBean(BookDAO.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {

        USER_DAO.addUser("Иванов", "Иван", LocalDate.of(1990, 1, 25),
                "+72651355648", "mail1", "Путешествие из Петербурга в Москву", "book2");
        USER_DAO.addUser("Петров", "Петр", LocalDate.of(1990, 7, 5),
                "+72651355524", "mail2", "Доктор Живаго", "Недоросль");
        USER_DAO.addUser("Васильев", "Василий", LocalDate.of(1990, 3, 15),
                "+72651355614", "mail3");

        getBooksInfo("mail1");
        getBooksInfo("mail2");
        getBooksInfo("mail3");
        getBooksInfo("mail4");
    }

    private static void getBooksInfo(String mail) {
        LOGGER.info("--------start searching books for user with mail = '{}'--------", mail);

        String[] bookTitles = USER_DAO.getUserBooks(mail);

        if (bookTitles != null && bookTitles.length != 0)
            Arrays.stream(bookTitles).forEach(BOOK_DAO::findBooksByTitle);

        LOGGER.info("--------book search completed--------");
    }
}
