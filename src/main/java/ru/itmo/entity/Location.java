package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlElement
    private long id;

    @XmlElement
    private float x;

    @NotNull
    @XmlElement
    private Integer y; //Поле не может быть null

    @XmlElement
    private int z;

    @NotBlank
    @XmlElement
    private String name; //Строка не может быть пустой, Поле не может быть null
}