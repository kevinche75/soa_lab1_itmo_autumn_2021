package ru.itmo.service;

import ru.itmo.DAO.LabWorksDAO;
import ru.itmo.converter.XMLConverter;
//import ru.itmo.entity.FrontRequest.LabWorkStr;
import ru.itmo.entity.LabWork;
import ru.itmo.converter.FieldConverter;
import ru.itmo.utils.LabWorkParams;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itmo.utils.LabWorksResult;
import ru.itmo.utils.ServerResponse;
import ru.itmo.validator.Validator;
import ru.itmo.validator.ValidatorResult;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class LabWorksService {

    private XMLConverter xmlConverter;
    private LabWorksDAO dao;

    public LabWorksService(){
        xmlConverter = new XMLConverter();
        dao = new LabWorksDAO();
    }

    public void getInfo(HttpServletResponse response, int code, String message){

        try {
            ServerResponse serverResponse = new ServerResponse(message);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(serverResponse));
            response.setStatus(code);
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(500);
        }

    }

    public void getInfo(HttpServletResponse response, int code){
        ServerResponse serverResponse = new ServerResponse("message");
    }

    public void getAllLabWorks(LabWorkParams params, HttpServletResponse response){
        if (!params.getValidatorResult().isStatus()){
            this.getInfo(response, 400, params.getValidatorResult().getMessage());
        }
        try {
            LabWorksResult labWorksResult = dao.getAllLabWorks(params);
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(labWorksResult));
        } catch (Exception e){
            this.getInfo(response, 500, "Server error, try again");
        }
    }

    public void getLabWork(String str_id, HttpServletResponse response){
        ValidatorResult validatorResult = new ValidatorResult();
        Long id = FieldConverter.longConvert(str_id, "LabWork Id", validatorResult);
        if (!validatorResult.isStatus()){
            this.getInfo(response, 400, validatorResult.getMessage());
            return;
        }
        try {
            Optional<LabWork> labWork = dao.getLabWork(id);
            if (labWork.isPresent()){
                response.setStatus(200);
                PrintWriter writer = response.getWriter();
                writer.write(xmlConverter.toStr(labWork.get()));
            } else {
                this.getInfo(response, 404, "No labWork with such id: " + id);
            }
        } catch (Exception e){
            this.getInfo(response, 500, "Server error, try again");
        }
    }

    public void getMinName(HttpServletResponse response){
        try{
            LabWork lab = dao.getMinName();
            if (lab == null){
                this.getInfo(response, 404, "No labs");
                return;
            }
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(lab));
        } catch (Exception e){
            this.getInfo(response, 500, "Server error, try again");
        }
    }

    public void countPersonalQualitiesMaximum(String str_pqm, HttpServletResponse response){
        ValidatorResult validatorResult = new ValidatorResult();
        Long pqm = FieldConverter.longConvert(str_pqm, "LabWork Personal Qualities Maximum", validatorResult);
        if (!validatorResult.isStatus()){
            this.getInfo(response, 400, validatorResult.getMessage());
            return;
        }
        try {
            Long count = dao.countPQM(pqm);
            if (count != null){
                this.getInfo(response, 200, "Count of labs with PQM: " + str_pqm + " is: " + count);
            } else {
                this.getInfo(response, 500, "Server error, try again");
            }
        } catch (Exception e){
            this.getInfo(response, 500, "Server error, try again");
        }
    }


    public void createLabWork(HttpServletRequest request, HttpServletResponse response){
        try {
            String xmlStr = FieldConverter.bodyToStringConvert(request);
//            LabWorkStr strlabWork = xmlConverter.fromStr(xmlStr, LabWorkStr.class);
            Validator validator = new Validator();
//            LabWork labWork = validator.validateLabWork(strlabWork);
            if (!validator.getValidatorResult().isStatus()){
                getInfo(response, 400, validator.getValidatorResult().getMessage());
            }
//            Long id = dao.createLabWork(labWork);
            response.setStatus(200);
        } catch (Exception e){
            getInfo(response, 500);
        }
    }

    public void updateLabWork(HttpServletRequest request, HttpServletResponse response){
        try {
//            LabWorkStr strLabWorkUpdate = xmlConverter.fromStr(FieldConverter.bodyToStringConvert(request), LabWorkStr.class);
            Validator validator = new Validator();
//            LabWork labWorkUpdate = validator.validateLabWork(strLabWorkUpdate);
//            validator.validateCreationDate(strLabWorkUpdate, labWorkUpdate);
            if (!validator.getValidatorResult().isStatus()){
                getInfo(response, 400, validator.getValidatorResult().getMessage());
            }
//            Optional<LabWork> lab = dao.getLabWork(labWorkUpdate.getId());
//            if (lab.isPresent()) {
//                LabWork labWorkPresent = lab.get();
//                labWorkPresent.update(labWorkUpdate);
//                dao.updateLabWork(labWorkPresent);
//                response.setStatus(200);
//            } else getInfo(response, 500);
        } catch (Exception e){
            getInfo(response, 500);
        }
    }

    public void deleteLabWork(String str_id, HttpServletResponse response){
        ValidatorResult validatorResult = new ValidatorResult();
        Long id = FieldConverter.longConvert(str_id, "Delete id", validatorResult);

        if (!validatorResult.isStatus()){
            this.getInfo(response, 400, validatorResult.getMessage());
            return;
        }

        boolean result = dao.deleteLabWork(id, validatorResult);
        if (!result) getInfo(response, validatorResult.getCode(), validatorResult.getMessage());
        else response.setStatus(200);
    }
}
