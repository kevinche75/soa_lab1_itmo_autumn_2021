package ru.itmo.validator;

import lombok.Getter;
import ru.itmo.converter.FieldConverter;
//import ru.itmo.entity.FrontRequest.LabWorkStr;
import ru.itmo.utils.LabWorkParams;

@Getter
public class Validator {

    ValidatorResult validatorResult;

    public Validator(){
        validatorResult = new ValidatorResult();
    }

//    public ru.itmo.entity.LabWork validateLabWork(LabWorkStr strLabWork){
//        ru.itmo.entity.LabWork labWork = new ru.itmo.entity.LabWork();
//
//        labWork.setId(FieldConverter.longConvert(strLabWork.getIdStr(), "LabWork Id", validatorResult));
//        if(labWork.getId() != null && labWork.getId() <= 0){
//            validatorResult.addMessage("LabWork id must be more than 0");
//        }
//        labWork.setName(FieldConverter.stringConvert(strLabWork.getNameStr(), "LabWork Name", validatorResult));
//        labWork.setMinimalPoint(FieldConverter.floatConvert(strLabWork.getMinimalPointStr(), "LabWork MinimalPoint", validatorResult));
//        if(labWork.getMinimalPoint() != null && labWork.getMinimalPoint() < 0){
//            validatorResult.addMessage("LabWork MinimalPoint must be more than 0");
//        }
//        labWork.setMaximumPoint(FieldConverter.floatConvert(strLabWork.getMaximumPointStr(), "LabWork MaximalPoint", validatorResult));
//        if(labWork.getMaximumPoint() != null && labWork.getMaximumPoint() < 0){
//            validatorResult.addMessage("LabWork MaximumPoint must be more than 0");
//        }
//        labWork.setPersonalQualitiesMaximum(FieldConverter.longConvert(strLabWork.getPersonalQualitiesMaximumStr(), "LabWork PersonalQualitiesMaximum", validatorResult));
//        if(labWork.getPersonalQualitiesMaximum() != null && labWork.getPersonalQualitiesMaximum() < 0){
//            validatorResult.addMessage("LabWork PersonalQualitiesMaximum must be more than 0");
//        }
//        labWork.setDifficulty(FieldConverter.difficultyConvert(strLabWork.getDifficultyStr(), "LabWork Difficulty", validatorResult));
//        labWork.getCoordinates().setX(FieldConverter.intConvert(strLabWork.getCoordinatesStr().getX(), "Coordinates X", validatorResult));
//        Double coordinatesY = FieldConverter.doubleConvert(strLabWork.getCoordinatesStr().getY(), "Coordinates Y", validatorResult);
//        if (coordinatesY != null){
//            labWork.getCoordinates().setY(coordinatesY);
//        }
//        labWork.getAuthor().setName(FieldConverter.stringConvert(strLabWork.getAuthorStr().getName(), "Author Name", validatorResult));
//        labWork.getAuthor().setWeight(FieldConverter.floatConvert(strLabWork.getAuthorStr().getWeight(), "Author Weight", validatorResult));
//        if(labWork.getAuthor().getWeight() != null && labWork.getAuthor().getWeight() < 0){
//            validatorResult.addMessage("Author Weight must be more than 0");
//        }
//        Float locationX = FieldConverter.floatConvert(strLabWork.getAuthorStr().getLocation().getX(), "Location X", validatorResult);
//        if (locationX != null){
//            labWork.getAuthor().getLocation().setX(locationX);
//        }
//        labWork.getAuthor().getLocation().setY(FieldConverter.intConvert(strLabWork.getAuthorStr().getLocation().getY(), "Location Y", validatorResult));
//        Integer locationZ = FieldConverter.intConvert(strLabWork.getAuthorStr().getLocation().getZ(), "Location Z", validatorResult);
//        if (locationZ != null){
//            labWork.getAuthor().getLocation().setZ(locationZ);
//        }
//        labWork.getAuthor().getLocation().setName(FieldConverter.stringConvert(strLabWork.getAuthorStr().getLocation().getName(), "Location Name", validatorResult));
//        return labWork;
//    }
//
//    public void validateCreationDate(LabWorkStr strLabWork, ru.itmo.entity.LabWork labWork){
//        labWork.setCreationDate(FieldConverter.localDateTimeConvert(strLabWork.getCreationDateStr(), "LabWork CreationDate", LabWorkParams.DATE_PATTERN, validatorResult));
//    }
}
