package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    private java.util.Date birthday; //Поле не может быть null

    @NotNull
    @Positive
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0

    @NotNull
    @Positive
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0

    @NotBlank
    private String passportID; //Строка не может быть пустой, Поле не может быть null
}
