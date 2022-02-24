package com.example.demo;

public class UserMsDTO {

    private Long userId;
    private String userName;
    private String emailAddress;
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserMsDTO(Long userId, String userName, String emailAddress) {
        this.userId = userId;
        this.userName = userName;
        this.emailAddress = emailAddress;
    }
    public UserMsDTO() {

    }

}
