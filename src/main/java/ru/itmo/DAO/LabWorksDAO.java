package ru.itmo.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.entity.LabWork;
import ru.itmo.utils.HibernateUtil;

import java.util.List;

public class LabWorksDAO {

    public List<LabWork> getAllLabWorks(){
        List<LabWork> labWorks = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            labWorks = session.createQuery("from LabWork").getResultList();
        }
        return labWorks;
    }
}
