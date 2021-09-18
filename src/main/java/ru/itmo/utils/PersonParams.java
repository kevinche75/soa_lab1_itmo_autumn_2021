package ru.itmo.utils;

import ru.itmo.entity.Person;

public class PersonParams {
    String name;
    Float weight;
    Float locationX;
    Integer locationY;
    Integer locationZ;
    String locationName;
    int pageIdx;
    int pageSize;
    String sortField;

    public PersonParams(
            String name,
            String weight,
            String locationX,
            String locationY,
            String locationZ,
            String locationName,
            String pageIdx,
            String pageSize,
            String sortField
    ) {
        this.name = name;
        this.weight = FieldConverter.floatConvert(weight);
        this.locationX = FieldConverter.floatConvert(locationX);
        this.locationY = FieldConverter.intConverter(locationY);
        this.locationZ = FieldConverter.intConverter(locationZ);
        this.locationName = locationName;
        this.pageIdx = FieldConverter.intConverter(pageIdx, 1);
        this.pageSize = FieldConverter.intConverter(pageSize, 10);
        this.sortField = FieldConverter.sortFieldConverter(sortField, Person.getAllFields());
    }
}
