package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement
public class Person {

    @Id
    @NotBlank
    @XmlElement
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    @Positive
    @XmlElement
    private Float weight; //Поле может быть null, Значение поля должно быть больше 0

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @XmlElement
    private Location location; //Поле не может быть null

    public static List<String> getAllFields(){
        Field[] fields = Person.class.getDeclaredFields();
        List<String> fieldList = Arrays
                .stream(fields)
                .map(Field::getName)
                .filter(field -> !field.equals("location"))
                .collect(Collectors.toList());
        Arrays
                .stream(Location.class.getDeclaredFields())
                .map(Field::getName)
                .filter(field -> !field.equals("id"))
                .forEach(field -> fieldList.add("location." + field));
        return fieldList;
    }
}