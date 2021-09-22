package ru.itmo.utils;

import ru.itmo.entity.Coordinates;
import ru.itmo.entity.Difficulty;
import ru.itmo.entity.LabWork;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LabWorkParams {

    String name;
    LocalDateTime creationDate;
    Float minimalPoint;
    Float maximalPoint;
    Long personalQualitiesMaximum;
    Difficulty difficulty;
    Integer coordinatesX;
    Double coordinatesY;
    String personName;
    Float personWeight;
    Float locationX;
    Integer locationY;
    Integer locationZ;
    String locationName;
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
            String personName,
            String personWeight,
            String locationX,
            String locationY,
            String locationZ,
            String locationName,
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
        this.coordinatesX = FieldConverter.intConvert(coordinatesX);
        this.coordinatesY = FieldConverter.doubleConvert(coordinatesY);
        this.personName = personName;
        this.personWeight = FieldConverter.floatConvert(personWeight);
        this.locationX = FieldConverter.floatConvert(locationX);
        this.locationY = FieldConverter.intConvert(locationY);
        this.locationZ = FieldConverter.intConvert(locationZ);
        this.locationName = locationName;
        this.pageIdx = FieldConverter.intConvert(pageIdx, 1);
        this.pageSize = FieldConverter.intConvert(pageSize, 10);
        this.sortField = FieldConverter.sortFieldConvert(sortField, LabWork.getAllFields());
    }

    public List<Predicate> getPredicates(
            CriteriaBuilder criteriaBuilder,
            Root<LabWork> root,
            Join<LabWork, Coordinates> join
    ){
        List<Predicate> predicates = new ArrayList<>();
        if (this.name != null){
            predicates.add(criteriaBuilder.like(root.get("name"), FieldConverter.stringLikeConvert(this.name)));
        }
        if (this.creationDate != null){
            predicates.add(criteriaBuilder.equal(root.get("creationDate"), this.creationDate));
        }
        if (this.minimalPoint != null){
            predicates.add(criteriaBuilder.equal(root.get("minimalPoint"), this.minimalPoint));
        }
        if (this.maximalPoint != null){
            predicates.add(criteriaBuilder.equal(root.get("maximalPoint"), this.maximalPoint));
        }
        if (this.personalQualitiesMaximum != null){
            predicates.add(criteriaBuilder.equal(root.get("personalQualitiesMaximum"), this.personalQualitiesMaximum));
        }
        if (this.difficulty != null){
            predicates.add(criteriaBuilder.equal(root.get("difficulty"), this.difficulty));
        }
        if (this.coordinatesX != null){
            predicates.add(criteriaBuilder.equal(join.get("x"), this.coordinatesX));
        }
        if (this.coordinatesY != null){
            predicates.add(criteriaBuilder.equal(join.get("y"), this.coordinatesY));
        }
        return predicates;
    }
}
