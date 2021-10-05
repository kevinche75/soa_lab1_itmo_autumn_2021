package ru.itmo.utils;

import lombok.AllArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@AllArgsConstructor
@XmlRootElement
public class ServerResponse {

    @XmlElement
    String message;
}
