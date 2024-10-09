package com.unit_test_exercise.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    // parameters
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private Integer age;
    private String address;

    // costruttore vuoto
    public User(){

    }
    // costruttore con tutti i parametri

    public User(Long id, String fullName, Integer age,String address) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // metodo to String per visualizzare un user
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
