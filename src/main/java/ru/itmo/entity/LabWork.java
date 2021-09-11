package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class    LabWork {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @NotBlank
    private String name; //Поле не может быть null, Строка не может быть пустой

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates; //Поле не может быть null

    @NotNull
    private java.time.LocalDateTime creationDate = java.time.LocalDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @NotNull
    @Positive
    private Float minimalPoint; //Значение поля должно быть больше 0

    @NotNull
    @Positive
    private int maximumPoint; //Поле не может быть null, Значение поля должно быть больше 0

    @NotNull
    @Positive
    private Integer personalQualitiesMaximum; //Поле может быть null, Значение поля должно быть больше 0

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Difficulty difficulty; //Поле может быть null

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name")
    private Person author; //Поле может быть null
}
