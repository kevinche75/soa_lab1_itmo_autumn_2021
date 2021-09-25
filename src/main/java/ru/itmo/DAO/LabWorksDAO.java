package ru.itmo.DAO;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.converter.FieldConverter;
import ru.itmo.entity.Coordinates;
import ru.itmo.entity.LabWork;
import ru.itmo.entity.Location;
import ru.itmo.entity.Person;
import ru.itmo.utils.LabWorkParams;
import ru.itmo.utils.HibernateUtil;
import ru.itmo.utils.LabWorksResult;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

public class LabWorksDAO {

    private void applyPagination(TypedQuery<LabWork> labWorkQuery, LabWorkParams params){
        int startIndex = params.getPageIdx() * (params.getPageSize() - 1);
        labWorkQuery.setFirstResult(startIndex);
        labWorkQuery.setMaxResults(params.getPageSize());
    }

    public LabWorksResult getAllLabWorks(LabWorkParams params){
        List<LabWork> labWorks = null;
        LabWorksResult result = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<LabWork> criteriaQuery = criteriaBuilder.createQuery(LabWork.class);
            Root<LabWork> root = criteriaQuery.from(LabWork.class);
            Join<LabWork, Coordinates> coordinatesJoin = root.join("coordinates");
            Join<LabWork, Person> personJoin = root.join("author");
            Join<Person, Location> locationJoin = personJoin.join("location");
            List<Predicate> predicates = params.getPredicates(criteriaBuilder, root, coordinatesJoin, personJoin, locationJoin);

            if (params.getSortField() != null){
                if (params.getSortField().startsWith("coordinates")){
                    criteriaQuery.orderBy(criteriaBuilder.asc(coordinatesJoin.get(FieldConverter.removePrefixFieldConvert(params.getSortField(), "coordinates"))));
                } else if (params.getSortField().startsWith("person")){
                    criteriaQuery.orderBy(criteriaBuilder.asc(personJoin.get(FieldConverter.removePrefixFieldConvert(params.getSortField(), "person"))));
                } else if (params.getSortField().startsWith("location")){
                    criteriaQuery.orderBy(criteriaBuilder.asc(locationJoin.get(FieldConverter.removePrefixFieldConvert(params.getSortField(), "location"))));
                } else {
                    criteriaQuery.orderBy(criteriaBuilder.asc(root.get(params.getSortField())));
                }
            }

            CriteriaQuery<LabWork> query = criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}));
            TypedQuery<LabWork> typedQuery = session.createQuery(query);
            applyPagination(typedQuery, params);
            labWorks = typedQuery.getResultList();

            CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
            countQuery.select(criteriaBuilder.count(countQuery.from(LabWork.class)));
            countQuery.where(predicates.toArray(new Predicate[]{}));
            Long count = session.createQuery(countQuery).getSingleResult();

            result = new LabWorksResult((int) (count / params.getPageSize() + 1), params.getPageIdx(), count, labWorks);
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            throw e;
        }
        return result;
    }

    public Optional<LabWork> getLabWork(long id){
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

    public long createLabWork(LabWork labWork){
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Long id = (Long) session.save(labWork);
            transaction.commit();
            return id;
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void updateLabWork(LabWork labWork){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.update(labWork);
            transaction.commit();
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
}
