package ru.itmo.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.entity.Coordinates;
import ru.itmo.entity.LabWork;
import ru.itmo.entity.Location;
import ru.itmo.entity.Person;
import ru.itmo.utils.LabWorkParams;
import ru.itmo.utils.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public class LabWorksDAO {

    public List<LabWork> getAllLabWorks(LabWorkParams params){
        List<LabWork> labWorks = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<LabWork> criteriaQuery = criteriaBuilder.createQuery(LabWork.class);
            Root<LabWork> root = criteriaQuery.from(LabWork.class);
            Join<LabWork, Coordinates> coordinatesJoin = root.join("coordinates");
            Join<LabWork, Person> personJoin = root.join("author");
            Join<Person, Location> locationJoin = personJoin.join("location");
        }
        return labWorks;
    }

    public Optional<LabWork> getLabWork(int id){
        Transaction transaction;
        LabWork labWork = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            labWork = session.find(LabWork.class, id);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
        }
        return Optional.ofNullable(labWork);
    }
}
