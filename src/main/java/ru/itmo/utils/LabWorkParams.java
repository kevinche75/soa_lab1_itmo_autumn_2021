package ru.itmo.utils;

import ru.itmo.entity.Difficulty;
import ru.itmo.entity.LabWork;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LabWorkParams {
    String name;
    LocalDateTime creationDate;
    Float minimalPoint;
    Float maximalPoint;
    Long personalQualitiesMaximum;
    Difficulty difficulty;
    Integer coordinatesX;
    Double coordinatesY;
    Integer pageIdx;
    Integer pageSize;
    String sortField;

    private final static String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    public LabWorkParams(
            String name,
            String creationDate,
            String minimalPoint,
            String maximalPoint,
            String personalQualitiesMaximum,
            String difficulty,
            String coordinatesX,
            String coordinatesY,
            String pageIdx,
            String pageSize,
            String sortField
    ) {
        this.name = name;
        this.creationDate = FieldConverter.localDateTimeConvert(creationDate, DATE_PATTERN);
        this.minimalPoint = FieldConverter.floatConvert(minimalPoint);
        this.maximalPoint = FieldConverter.floatConvert(maximalPoint);
        this.personalQualitiesMaximum = FieldConverter.longConvert(personalQualitiesMaximum);
        this.difficulty = FieldConverter.difficultyConvert(difficulty);
        this.coordinatesX = FieldConverter.intConverter(coordinatesX);
        this.coordinatesY = FieldConverter.doubleConverter(coordinatesY);
        this.pageIdx = FieldConverter.intConverter(pageIdx, 1);
        this.pageSize = FieldConverter.intConverter(pageSize, 10);
        this.sortField = FieldConverter.sortFieldConverter(sortField, LabWork.getAllFields());
    }
}
