package ru.itmo.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.itmo.converter.FieldConverter;
import ru.itmo.entity.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
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
    Boolean lessThanMaximalPointFlag;

    public final static String DATE_PATTERN = "dd.MM.yyyy";

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
            String sortField,
            String lessThanMaximalPointFlag
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
        this.pageIdx = Math.max(FieldConverter.intConvert(pageIdx, 1), 1);
        this.pageSize = Math.max(FieldConverter.intConvert(pageSize, 10), 1);
        this.sortField = FieldConverter.sortFieldConvert(sortField, LabWork.getAllFields());
        this.lessThanMaximalPointFlag = FieldConverter.booleanConvert(lessThanMaximalPointFlag);
    }

    public List<Predicate> getPredicates(
            CriteriaBuilder criteriaBuilder,
            Root<LabWork> root,
            Join<LabWork, Coordinates> coordinatesJoin,
            Join<LabWork, Person> personJoin,
            Join<Person, Location> locationJoin
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
            predicates.add(criteriaBuilder.equal(coordinatesJoin.get("x"), this.coordinatesX));
        }
        if (this.coordinatesY != null){
            predicates.add(criteriaBuilder.equal(coordinatesJoin.get("y"), this.coordinatesY));
        }
        if (this.personName != null){
            predicates.add(criteriaBuilder.like(personJoin.get("name"), FieldConverter.stringLikeConvert(this.personName)));
        }
        if (this.personWeight != null){
            predicates.add(criteriaBuilder.equal(personJoin.get("weight"), this.personWeight));
        }
        if (this.locationX != null){
            predicates.add(criteriaBuilder.equal(locationJoin.get("x"), this.locationX));
        }
        if (this.locationY != null){
            predicates.add(criteriaBuilder.equal(locationJoin.get("y"), this.locationY));
        }
        if (this.locationZ != null){
            predicates.add(criteriaBuilder.equal(locationJoin.get("z"), this.locationZ));
        }
        if (this.locationName != null){
            predicates.add(criteriaBuilder.like(locationJoin.get("name"), FieldConverter.stringLikeConvert(this.locationName)));
        }
        return predicates;
    }

    public List<Predicate> getLessMaximalPointPredicate(CriteriaBuilder criteriaBuilder, Root<LabWork> root){
        List<Predicate> predicates = new ArrayList<>();
        if (this.maximalPoint != null){
            predicates.add(criteriaBuilder.lessThan(root.get("maximalPoint"), this.maximalPoint));
        }
        return predicates;
    }
}
