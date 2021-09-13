package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement
public class LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @XmlElement
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    @XmlElement
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    @XmlElement
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @NotNull
    @Positive
    @XmlElement
    private Float minimalPoint; //Значение поля должно быть больше 0

    @NotNull
    @Positive
    @XmlElement
    private Float maximumPoint; //Поле не может быть null, Значение поля должно быть больше 0

    @NotNull
    @Positive
    @XmlElement
    private Long personalQualitiesMaximum; //Поле может быть null, Значение поля должно быть больше 0

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @XmlElement
    private Difficulty difficulty; //Поле может быть null
}