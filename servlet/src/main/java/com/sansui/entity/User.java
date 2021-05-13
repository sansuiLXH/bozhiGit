package com.sansui.entity;

/**
 * @author 西西里_SanSui
 * @date Created in 2021/5/10 1:28
 * @modified By  西西里_SanSui in 2021/5/10 1:28
 * @description AddDescriptionHere
 */

public class User {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String password;
    private String email;
    private String province;
    private String city;
    private String hobby;

    public User() {
    }

    public User( String name, String password, String email, String province, String city, String hobby) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.province = province;
        this.city = city;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
