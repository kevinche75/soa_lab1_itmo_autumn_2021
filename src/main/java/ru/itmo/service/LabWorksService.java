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

import java.io.PrintWriter;
import java.util.Optional;

public class LabWorksService {

    private XMLConverter xmlConverter;
    private LabWorksDAO dao;

    public LabWorksService(){
        xmlConverter = new XMLConverter();
        dao = new LabWorksDAO();
    }

    public void getAllLabWorks(LabWorkParams params, HttpServletResponse response){
        try {
            LabWorksResult labWorksResult = dao.getAllLabWorks(params);
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(labWorksResult));
        } catch (Exception e){
            this.getError(response);
        }
    }

    @SneakyThrows
    public void getLabWork(Long id, HttpServletResponse response){
        if (id == null){
            this.getError(response);
            return;
        }
        Optional<LabWork> labWork = dao.getLabWork(id);
        if (labWork.isPresent()){
            response.setStatus(200);
            PrintWriter writer = response.getWriter();
            writer.write(xmlConverter.toStr(labWork.get()));
        } else {
            this.getError(response);
        }
    }

    public void getMinName(){

    }

    public void countPersonalQualitiesMaximum(Long personalQualitiesMaximum){

    }

    public void getLessMaximumPoint(LabWorkParams labWorkParams){

    }

    public void getError(HttpServletResponse response){

    }

    public void createLabWork(HttpServletRequest request, HttpServletResponse response){
        try {
            String xmlStr = FieldConverter.bodyToStringConvert(request);
            LabWork labWork = xmlConverter.fromStr(xmlStr, LabWork.class);
            Long id = dao.createLabWork(labWork);
            response.setStatus(200);
            response.getWriter().write(xmlConverter.toStr(id));
        } catch (Exception e){
            getError(response);
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
            } else getError(response);
        } catch (Exception e){
            getError(response);
        }
    }
}
