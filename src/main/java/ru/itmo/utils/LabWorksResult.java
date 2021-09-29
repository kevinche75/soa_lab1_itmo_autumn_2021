package ru.itmo.utils;

import lombok.AllArgsConstructor;
import ru.itmo.entity.LabWork;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@XmlRootElement(name = "labworks_result")
public class LabWorksResult {

//    @XmlElement
//    private final int totalPages;
//    @XmlElement
//    private final int currentPage;
//    @XmlElement
//    private final long totalLabWorks;
    @XmlElementWrapper(name = "labworks")
    @XmlElement(name = "labwork")
    private final List<LabWork> list;
    public LabWorksResult(){
//        this.totalPages = 0;
//        this.currentPage = 0;
//        this.totalLabWorks = 0;
        this.list = new ArrayList<>();
    }
}