package entities;

import orm.annotation.Column;
import orm.annotation.Entity;
import orm.annotation.Id;


import java.time.LocalDate;


@Entity(name = "accounts")
public class Account {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "countOfFollowers")
    private Integer countOfFollowers;

    @Column(name = "createdOn")
    private LocalDate createdOn;

    @Column(name = "lastLogged")
    private LocalDate lastLogged;

    @Column(name = "nickname")
    private String nickname;

    public Account(String name, LocalDate createdOn, Integer age) {
        this.name = name;
        this.createdOn = createdOn;
        this.age = age;
    }
}