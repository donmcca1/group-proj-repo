package com.ericsson.group.dao;

import com.ericsson.group.entities.BaseData;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Stateless
@Local
public class JPABaseDAO implements BaseDAO {

    @PersistenceContext
    private EntityManager em;

    //--- SELECT ALL (NO FRONT END) ---//
    public Collection<BaseData> getBaseData() {
        Query query = em.createQuery("from BaseData");
        return (List<BaseData>)query.getResultList();
    }

    //--- IMSI AUTO COMPLETE ---//
    public Collection<Long> imsiAutoComplete(Long imsi){
        Query query = em.createNamedQuery("imsiautocomplete").setParameter("imsi",imsi);
        return (List<Long>)query.getResultList();
    }

    //*******************//
    //*** CSR QUERIES ***//
    //*******************//

    //--- 1. SELECT BY IMSI, RETURN EVENT_ID, CAUSE_CODE ---//
    // currently returns all; selection made at front end
    public Collection<BaseData> getBaseDataByImsi(Long imsi){
        Query query = em.createQuery("from BaseData c where c.imsi = :imsi");
        query.setParameter("imsi",imsi);
        return (List<BaseData>)query.getResultList();
    }

    //--- 2. SELECT BY IMSI & DATE, COUNT NUMBER OF FAILURES ---//
    public Long getFailuresByDate(Long imsi, Date startDate, Date endDate) {
        Query query = em.createQuery("select count (c) from BaseData c where c.imsi = :imsi AND c.date_time >= :sDate AND c.date_time <= :eDate");
        query.setParameter("imsi", imsi);
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        System.out.println("DAO");
        return (Long)query.getSingleResult();
    }

    //--- 3. SELECT BY IMSI, RETURN UNIQUE CAUSE CODES ---//
    public Collection<?> getCauseCodeByImsi(Long imsi) {
        Query query = em.createQuery("select distinct event_cause.cause_code, event_cause.description from BaseData c where c.imsi = :imsi");
        query.setParameter("imsi", imsi);
        return (List<?>)query.getResultList();
    }


    //******************//
    //*** SE QUERIES ***//
    //******************//

    //--- 4. SELECT BY DATE, RETURN IMSI ---//
    // currently returns all; selection made at front end
    public Collection<BaseData> getBaseDataByDate(Date startDate, Date endDate){
        Query query = em.createQuery("from BaseData c where c.date_time >= :sDate AND c.date_time <= :eDate");
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        return (List<BaseData>)query.getResultList();
    }

    //--- 5. SELECT BY MODEL & DATE, COUNT NUMBER OF FAILURES ---//
    public Long countByModelAndDate(String ue_type, Date startDate, Date endDate){
        Query query = em.createQuery("select count (c) from BaseData c where c.ue.model = :ue_type AND c.date_time >= :sDate AND c.date_time <= :eDate");
        query.setParameter("ue_type", ue_type);
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        return (Long)query.getSingleResult();
    }

    //--- 6. SELECT BY CAUSE_CODE, RETURN IMSIs ---//
    public Collection<BaseData> getImsiByCauseCode(Integer cause_code){
        Query query = em.createQuery("from BaseData c where c.event_cause.cause_code = :cause_code");
        query.setParameter("cause_code", cause_code);
        return (List<BaseData>)query.getResultList();
    }

    //*******************//
    //*** NME QUERIES ***//
    //*******************//

    //--- 7. SELECT BY DATE, COUNT FAILURES, SUM DURATION BY IMSI ---//
    public Collection<?> getNumFailuresAndDurationByDate(Date startDate, Date endDate) {
        Query query = em.createQuery("select imsi, c.mcc_mnc.country, c.mcc_mnc.operator, count (c), sum (duration) from BaseData c where c.date_time >= :sDate AND c.date_time <= :eDate group by imsi, c.mcc_mnc.country, c.mcc_mnc.operator");
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        return (List<?>)query.getResultList();
    }

    //--- 7. SELECT BY DATE, COUNT BY COUNTRY for pie chart ---//
    public Collection<?> getDurationByDateGroupCountry(Date startDate, Date endDate) {
        Query query = em.createQuery("select count (c), c.mcc_mnc.country from BaseData c where c.date_time >= :sDate AND c.date_time <= :eDate group by c.mcc_mnc.country");
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        return (List<?>)query.getResultList();
    }

    //--- 8. SELECT BY UE_TYPE, RETURN UNIQUE EVENT_ID, CAUSE_CODE COMBINATIONS & COUNT ---//
    public Collection<?> countByModelEventIdCauseCode(String ue_type){
        Query query = em.createQuery("select count (c), c.event_cause.event_id, c.event_cause.cause_code, c.event_cause.description from BaseData c where c.ue.model = :ue_type group by c.event_cause.event_id, c.event_cause.cause_code");
        query.setParameter("ue_type", ue_type);
        return (List<?>)query.getResultList();
    }

    //--- 9. SELECT BY DATE, RETURN TOP 10 MARKET/OPERATOR/CELL_ID COMBINATIONS ---//
    public Collection<?> top10MarketOperatorCell(Date startDate, Date endDate) {
        Query query = em.createQuery("select c.mcc_mnc.country, c.mcc_mnc.operator, cell_id, c.mcc_mnc.mcc, c.mcc_mnc.mnc, count (c) from BaseData c where c.date_time >= :sDate AND c.date_time <= :eDate "
                + "group by c.mcc_mnc.country, c.mcc_mnc.operator, c.cell_id order by count(c) desc");
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        query.setMaxResults(10);
        return (List<?>)query.getResultList();
    }

    //--- 9. For graph, count all failures. ---//
    public Long countAllFailures(Date startDate, Date endDate) {
        Query query = em.createQuery("select count(c) from BaseData c where c.date_time >= :sDate AND c.date_time <= :eDate ");
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        return (Long) query.getSingleResult();
    }

    //--- 10. SELECT BY DATE, RETURN TOP 10 IMSIs ---//
    public Collection<?> top10imsi(Date startDate, Date endDate) {
        Query query = em.createQuery("select imsi, c.mcc_mnc.country, c.mcc_mnc.operator, count (c) from BaseData c where c.date_time >= :sDate AND c.date_time <= :eDate "
                + "group by imsi, c.mcc_mnc.country, c.mcc_mnc.operator order by count(c) desc");
        query.setParameter("sDate",startDate);
        query.setParameter("eDate", endDate);
        query.setMaxResults(10);
        return (List<?>)query.getResultList();
    }

    //***********************//
    //*** SA ONLY QUERIES ***//
    //***********************//

    //****************************//
    //*** AUTOCOMPLETE QUERIES ***//
    //****************************//

    public Collection<?> allIMSI(Long imsi){
        Query query = em.createQuery("select distinct imsi from BaseData c where c.imsi like CONCAT(:imsi,'%')");
        query.setParameter("imsi", imsi);
        return (List<?>)query.getResultList();
    }

    public Collection<?> allModels(String model){
        Query query = em.createQuery("select model from UE c where model like CONCAT('%', :model, '%')");
        query.setParameter("model", model);
        return (List<?>)query.getResultList();
    }

}