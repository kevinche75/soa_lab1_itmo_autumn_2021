package ru.itmo.service;

import lombok.SneakyThrows;
import ru.itmo.DAO.LabWorksDAO;
import ru.itmo.converter.XMLConverter;
import ru.itmo.entity.LabWork;
import ru.itmo.utils.LabWorkParams;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    }

    @SneakyThrows
    public void getLabWork(Integer id, HttpServletResponse response){
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
}
