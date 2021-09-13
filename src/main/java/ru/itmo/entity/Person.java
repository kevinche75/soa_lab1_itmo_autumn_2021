package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @XmlElement
    private Location location; //Поле не может быть null
}