package ru.itmo.converter;

import jdk.vm.ci.meta.Local;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class XMLLocalDateTimeConverter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.toString();
    }
}
