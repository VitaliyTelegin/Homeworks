package ru.sbercourse.home_work_6.model;

import java.util.Arrays;
import java.util.Date;

public class User {
    private String lastName;
    private String firstName;
    private Date birthDate;
    private String phone;
    private String mail;
    private String[] bookTitles;


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String[] getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(String[] bookTitles) {
        this.bookTitles = bookTitles;
    }


    @Override
    public String toString() {
        return "User {" +
                "lastName = '" + lastName + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", birthDate = " + birthDate +
                ", phone = '" + phone + '\'' +
                ", mail = '" + mail + '\'' +
                ", bookTitles = " + Arrays.toString(bookTitles) +
                '}';
    }
}
