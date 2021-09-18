package ru.itmo.utils;

import ru.itmo.entity.Difficulty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FieldConverter {

    public static LocalDateTime localDateTimeConvert(String date, String pattern){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e){
            return null;
        }
    }

    public static Float floatConvert(String number){
        try {
            return Float.parseFloat(number);
        } catch (Exception e){
            return null;
        }
    }

    public static Long longConvert(String number){
        try {
            return Long.parseLong(number);
        } catch (Exception e){
            return null;
        }
    }

    public static Difficulty difficultyConvert(String difficulty){
        try {
            return Difficulty.valueOf(difficulty);
        } catch (Exception e){
            return null;
        }
    }

    public static Integer intConverter(String number){
        try {
            return Integer.parseInt(number);
        } catch (Exception e){
            return null;
        }
    }

    public static Integer intConverter(String number, int defaultInteger){
        try {
            return Integer.parseInt(number);
        } catch (Exception e){
            return defaultInteger;
        }
    }


    public static Double doubleConverter(String number){
        try {
            return Double.parseDouble(number);
        } catch (Exception e){
            return null;
        }
    }

    public static Boolean booleanConverter(String bool){
        try {
            return Boolean.parseBoolean(bool);
        } catch (Exception e){
            return null;
        }
    }

    public static String sortFieldConverter(String sortField, List<String> fields){
        return fields.contains(sortField) ? sortField : null;
    }
}
