package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("BHlq9KZfLllIrrkQ3i4oveZ16PQegMOMvCAg71LtKTisrzt72P");
        useraccesslevel.setLevelHelp("htb8vmBMa50r47TVrcOSrAHLmNGhjvaKDgKr6nRsO0prFibL0Z");
        useraccesslevel.setLevelName("rrndjlgm3uCL3gX0PhuGVJmJZMLVHKliwWCwqoDkBKcpyKB5FO");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("3itLLqUKvKnf0sThOZ7biUb7ahIyVsDKZlO7XBtNsnSXtgDWje");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelIcon("A3C8SrRmBfXeMQJDflpiIFqtGt6eAiz3QhPjtUfGItsZFfCtcm");
            useraccesslevel.setLevelHelp("qTs2TQ26mHntwRY7egxOC1JyOYl3jfUZmwPd58isoK00g7scM1");
            useraccesslevel.setLevelName("XcZGS02YLiGUSxW7sa5SrcMXzWrWkoN72Ctj42AkgcSMsr0ShF");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(7941);
            useraccesslevel.setLevelDescription("yeXXOsn8ddM3DrFaa2twJ8AsIPErjQpIm2JEMIse07GxxeoB8i");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 112586));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "oFM5awltYyuxPjJZiMIhErY1m00o07VHFF1YyVYIgJ3nE5mxYPkraHtf8hReBlc5gNv3L7qVz1Kjj6Ks3s14Fy6uq6a7MvSMBAm1Ra4lq1IQpopFfIk4mYXNWMntXYBXucTLt5FwVz0OLhRiee5laaiO5iXQB71GjyyF1v4b5C2SAfqNS3wdFkUh7qtvQixXatBAyA3LPlnR7ynDfDHmdJW23X7mSMQPB6ovw3XjRPEIYlhQ6heGzBsMFPhfaSjyT"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "QkCEsohMjXIMuK68a3jbImnsGYyQYDq6F50Vy2h8Sq7F5ShN5XnVfrbkRdJ6gf09QHYK9M35MyTyTx8MfUomsFbzjay2VzuUhkKIwl2QuErzbYTtzXv4wEZdUCZ6wEAWE57ie7SLD2y7V8MDK7lGqvN1wKgTNKdV1JezBubdbB3YcdykmoYjPJtFtyrRdPfKU63JNNw42D10YYnvIDXgqGYAs33ASL00ClnDLC7ORoOqZBmMxQAxT8pkEh5XVvyBl"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "0bOJcJSYkdGt6le1N2TLrmY0H9I8kWZ2Sy6zfr4Sqa0RBSCtz96zM6ePxRAEbD0TzdCZSQE6mGYP15fYcy8dk0LjLYx2BnrR8TSNq7g8Ko81KCm57yTKRO6xf58KiIesI4UHCgvPkfCHElwiGrDxQNWz62gSyCSk5huVIiBRD38EZoyNnFjeazp9KQS1DACYwzfk05agc4TKzqtQj9FZeiTKCxW5ItF0sFjr1FFEadRdZxrQVHtl8l57pRjfL9iVD"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
