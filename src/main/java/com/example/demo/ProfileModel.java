package com.example.demo;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="profilenew")
public class ProfileModel implements Serializable {
    @Id
     @Column(name = "id")
    Integer id;
    @Column(name = "name")
    String name;
    @Column(name = "email")
    String email;
    @Column(name = "gender")
    String gender;
    @Column(name="dob")
    String dob;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public ProfileModel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    @Column(name = "password")
    String password;

    public ProfileModel(Integer id, String name, String email, String gender, String password, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.password = password;
        this.dob=dob;
    }

    public ProfileModel(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProfileModel() {

    }
}
