package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("AU8mXXXn3JsBErDiqkhCwFwPTRTQvH3256M5TNwrwaSwjUhkwk");
        useraccessdomain.setDomainIcon("3TNYBmhIsDA3d6idS5Yw5RdvV9dfav5sIbLUR2btC21lZ6wlMK");
        useraccessdomain.setDomainName("k4cUdFTuC5XNacImwupbuXHoGa3bws7FBY63YjSoysJ5EoKAcB");
        useraccessdomain.setDomainDescription("L2LJYdzf91pYFbcGSYFHhZDb4i38S4akWcZCtv2dWeqskQNyEh");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setUserAccessDomain(75583);
            useraccessdomain.setDomainHelp("23jp3UgK2Zz4ebSLNByYD7f8XpputrOqsUUc4FsOAZwO9tdEAy");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainIcon("OC6awQkPNkK1F8I64oSIK7CwM5lRrwV2IkiGu8WJEgaZovdPnY");
            useraccessdomain.setDomainName("OHbunOmHnZIfwzg0zO1VUuuY6hB9iFQRTBAvWzPYHiUSwN6IwL");
            useraccessdomain.setDomainDescription("4fTgO6Kent0owd3i3w4EZ7UCOuBgDbVMAPdWOaMxy0GEh0XHla");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 107797));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "MpuJJ7VzktnDMsdSbrXNEEzjPlZupLiQzMetyEsQtILQ8nH4CB1nF8BN8kx9ezvVMZrImgPCXkRNr6dOJwEU6YQOmtiYuGP4EKbH2J0fT4FvgKInK8xjpJ25cMek8YUZ8Z41sPqcIFOtkiq8RDQBokeligHSm0CR3grrmuP6uSdJP1MFhYk2PEleBc5AiRMllwwVRVEBpriijvZnBRm5oS9IjQjAUihblFrbSpkO4bJp4BnvWSnPM63Yv9Gomermt"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "FdHle1AwADCEWOvlTY76B1ChvyjsyJlANyXKE2sD8O0R31iRtyT2AqAGW3euKFucXCwrX17HLIG5TPf9wFDJht2crUKQ5AcKeCwpm9FrGCZ6yskDTxFAhShbuJLcudAHcNfUguKIvdjIfVMtRoM3Sy0mDyhwhoAebgO37bTry70GnyUmn8aDOjSfGYSN0upiNO7yZ5HORWi8j2GWm7Xahf9izg0vYZqC3vLOdI1oU1y5Mc7ogT68lgQvaamxt93Zp"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "thFvvzAKcvjhtJ2DD8gWZrHJlJdfBuTu9vscliTfOFbolGaY4ZI1QD1qPTHQDQwwZ6xufJ7DT3VALQqWjij0lu6aqnJdKCgSnESeWbYGRxjVfsLex8IDZGs7EK8ETFjCNRgSg5etYhHkHS5nq7mR7qjGfAibwY4wffMWEYqtlVfYw4hOcem3X2L46N8fJslLnql1Tv2AcvQJZ5orKPu3Vrh14TNRPlGys420pNTcJxUimMELwd1IBdlkgImIha13l"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
