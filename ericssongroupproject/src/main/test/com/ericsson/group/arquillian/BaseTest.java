package com.ericsson.group.arquillian;

import com.ericsson.group.dao.BaseDAO;
import com.ericsson.group.dao.JPABaseDAO;
import com.ericsson.group.entities.*;
import com.ericsson.group.services.BaseService;
import com.ericsson.group.services.BaseServiceJPA;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.text.*;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;

/**
 * Created by d16125349 on 21/04/2017.
 */

@RunWith(Arquillian.class)
public class BaseTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(BaseService.class, BaseServiceJPA.class, BaseDAO.class, JPABaseDAO.class)
                .addPackage(BaseData.class.getPackage())
                .addAsResource("resources-wildfly/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    BaseService baseService;

    String startDate="2013-01-11";
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");


    @Test
    public final void test_baseService_All_Base() throws Exception {
        List<BaseData> testBase = (List<BaseData>) baseService.getAllBaseData();
        Assert.assertEquals(344930000000011L, testBase.get(0).getImsi());
    }

    @Test
    public final void test_baseService_IMSI_By_Cause_Code() throws Exception {
        List<BaseData> testBase = (List<BaseData>) baseService.getImsiByCauseCode(1);
        Assert.assertEquals(344930000000011L, testBase.get(0).getImsi());
    }

    @Test
    public final void test_baseService_Base_Data_By_Date() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        List<BaseData> testBase = (List<BaseData>) baseService.getBaseDataByDate(sqlStartDate, sqlStartDate);
        Assert.assertEquals(344930000000011L, testBase.get(0).getImsi());
    }

    @Test
    public final void test_baseService_Failures_By_Date() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        Long testBase =  baseService.getFailuresByDate(344930000000011L, sqlStartDate, sqlStartDate);
        Assert.assertEquals(Long.valueOf(1L), testBase);
    }

    @Test
    public final void test_baseService_Cause_Code_By_IMSI() throws Exception {
        List<BaseData> testBase = (List<BaseData>) baseService.getCauseCodeByImsi(344930000000011L);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_baseService_Count_Model_Date() throws Exception{
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        Long testBase =  baseService.countByModelAndDate("1",sqlStartDate,sqlStartDate);
        Assert.assertEquals(Long.valueOf(0L), testBase);
    }

    @Test
    public final void test_baseService_Base_Count_Model_Event_Cause() throws Exception {
        List<BaseData> testBase = (List<BaseData>) baseService.countByModelEventIdCauseCode("test ue ue_type");
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_baseService_Base_By_IMSI() throws Exception {
        List<BaseData> testBase = (List<BaseData>) baseService.getBaseDataByImsi(344930000000011L);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_Num_Failures_Duration_By_Date() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        Collection<?>  testBase = baseService.getNumFailuresAndDurationByDate(sqlStartDate,sqlStartDate);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_Top_10_IMSI() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        Collection<?>  testBase = baseService.top10imsi(sqlStartDate,sqlStartDate);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_Top_10_Market_Operator_Cell() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());

        Collection<?>  testBase = baseService.top10MarketOperatorCell(sqlStartDate,sqlStartDate);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_All_Models() throws Exception {
        Collection<?>  testBase = baseService.allModels("test ue model");
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_All_IMSI() throws Exception {
        Collection<?>  testBase = baseService.allIMSI(344930000000011L);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_Count_All_Failures() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Long  testBase = baseService.countAllFailures(sqlStartDate, sqlStartDate);
        Assert.assertEquals(Long.valueOf(1L), testBase);
    }

    @Test
    public final void test_Duration_Group_Country() throws Exception {
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        Collection<?> testBase = baseService.getDurationByDateGroupCountry(sqlStartDate, sqlStartDate);
        Assert.assertNotNull(testBase);
    }

    @Test
    public final void test_Imsi_Auto() throws Exception {
        Collection<?>  testBase = baseService.imsiAutoComplete(3449L);
        Assert.assertNotNull(testBase);
    }
}
