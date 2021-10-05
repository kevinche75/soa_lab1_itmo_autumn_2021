package ru.itmo.service;

import lombok.SneakyThrows;
import ru.itmo.DAO.LabWorksDAO;
import ru.itmo.converter.XMLConverter;
import ru.itmo.entity.LabWork;
import ru.itmo.converter.FieldConverter;
import ru.itmo.utils.LabWorkParams;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itmo.utils.LabWorksResult;
import ru.itmo.utils.ServerResponse;

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

    public void getError(HttpServletResponse response, int code, String message){

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

    public void getError(HttpServletResponse response, int code){
        ServerResponse serverResponse = new ServerResponse("message");
    }

    public void getAllLabWorks(LabWorkParams params, HttpServletResponse response){
        try {
            LabWorksResult labWorksResult = dao.getAllLabWorks(params);
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(labWorksResult));
        } catch (Exception e){
            this.getError(response, 500);
        }
    }

    @SneakyThrows
    public void getLabWork(Long id, HttpServletResponse response){
        if (id == null){
            this.getError(response, 500);
            return;
        }
        Optional<LabWork> labWork = dao.getLabWork(id);
        if (labWork.isPresent()){
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(labWork.get()));
        } else {
            this.getError(response, 404, "No labWork with such id:" + id);
        }
    }

    public void getMinName(){

    }

    public void countPersonalQualitiesMaximum(Long personalQualitiesMaximum){

    }

    public void getLessMaximumPoint(LabWorkParams labWorkParams){

    }

    public void createLabWork(HttpServletRequest request, HttpServletResponse response){
        try {
            String xmlStr = FieldConverter.bodyToStringConvert(request);
            LabWork labWork = xmlConverter.fromStr(xmlStr, LabWork.class);
            Long id = dao.createLabWork(labWork);
            response.setStatus(200);
        } catch (Exception e){
            getError(response, 500);
        }
    }

    public void updateLabWork(HttpServletRequest request, HttpServletResponse response){
        try {
            LabWork labWorkUpdate = xmlConverter.fromStr(FieldConverter.bodyToStringConvert(request), LabWork.class);
            Optional<LabWork> lab = dao.getLabWork(labWorkUpdate.getId());
            if (lab.isPresent()) {
                LabWork labWorkPresent = lab.get();
                labWorkPresent.update(labWorkUpdate);
                dao.updateLabWork(labWorkPresent);
                response.setStatus(200);
            } else getError(response, 500);
        } catch (Exception e){
            getError(response, 500);
        }
    }

    public void deleteLabWork(Long id, HttpServletResponse response){
        if (id == null){
            getError(response, 500);
            return;
        }
        boolean result = dao.deleteLabWork(id);
        if (!result) getError(response, 500);
        else response.setStatus(200);
    }
}
