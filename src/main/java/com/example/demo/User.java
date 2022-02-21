package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    private String userName;
    @Column(name = "Email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    private String ssn;

    public User(Long id, String userName, String email, String ssn) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.ssn = ssn;
    }
    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", ssn='" + ssn + '\'' +
                '}';
    }
}
