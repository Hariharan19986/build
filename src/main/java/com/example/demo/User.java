package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "User")
//@JsonIgnoreProperties({"firstName","lastName"})
//@JsonFilter(value = "userFilter")
public class User extends RepresentationModel {
    @Id
    @GeneratedValue
    @JsonView(Views.External.class)
    private Long userId;

    @NotEmpty(message = "User Name is Mandatory")
    @Column(name = "USER_NAME", length = 50, nullable = false, unique = true)
    @JsonView(Views.External.class)
    private String userName;

    @Size(min = 2, message = "FirstName should be atleast 2")
    @Column(name = "FIRSTNAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String firstName;

    @Column(name = "LASTNAME", length = 50, nullable = false)
    @JsonView(Views.External.class)
    private String lastName;

    @Column(name = "Email", length = 100, nullable = false, unique = true)
    @JsonView(Views.External.class)
    private String email;

    @Column(name = "ROLE", length = 50, nullable = false)
    @JsonView(Views.Internal.class)
    private String role;

    @Column(name = "SSN", length = 50, nullable = false, unique = true)
    @JsonView(Views.Internal.class)
    private String ssn;

    @OneToMany(mappedBy = "user")
    @JsonView(Views.Internal.class)
    private List<Order> orders;

    private String address;


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public User(){

    }

    public User(Long userId, String userName, String firstName, String lastName, String email, String role, String ssn, List<Order> orders, String address) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.ssn = ssn;
        this.orders = orders;
        this.address = address;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", ssn='" + ssn + '\'' +
                ", orders=" + orders +
                ", address='" + address + '\'' +
                '}';
    }
}
