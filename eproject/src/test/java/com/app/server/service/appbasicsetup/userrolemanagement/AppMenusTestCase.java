package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAction("jo7YX0RFcKDhAg1dxe7cn2ZqZTAE63ckxu69CYdSwOSIsXrQwT");
        appmenus.setRefObjectId("6UZ4zAnlC5xAPkXwhxU5SrT1qcp5UN5FC1YFjfhTmqvTlmFf3k");
        appmenus.setMenuDisplay(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1471588733570l));
        appmenus.setAppType(2);
        appmenus.setMenuAccessRights(4);
        appmenus.setMenuTreeId("cC1mDOLBmfa0L8XAgk4pnTEQ13pqfBS7ryX6UvAqatdvyzdasj");
        appmenus.setMenuIcon("ghiYr4iLtrwXTr5WgAVP6KN01ciaHLRpQWIN5M9i4Cysz0VvrS");
        appmenus.setUiType("Kzu");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("teWLXhS1LdU11J8zELTaHy4DvotuOlXl260Do5jUHjD1RiNxjg");
        appmenus.setStartDate(new java.sql.Timestamp(1471588733571l));
        appmenus.setMenuCommands("EomSiYB9iXyF1e3Jm7cJOUUyVAAxEuY5gh0awYckNpNgrxzKP3");
        appmenus.setAppId("zmeJEBnVO69RHo5ClxOaqIUAqUx79wi7TgfMsiyuDHuiammhId");
        appmenus.setMenuHead(true);
        appmenus.setExpiryDate(new java.sql.Timestamp(1471588733571l));
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuAction("0ZCuLyxQz55zUwIrbvLytlRZvGySWh7u0cz8FFYijANC6slncn");
            appmenus.setRefObjectId("nOZeRuBReQ1gOyeUT5apysqZWGTB5yWgMkT9JaBflMyscWI7ny");
            appmenus.setGoLiveDate(new java.sql.Timestamp(1471588733584l));
            appmenus.setAppType(1);
            appmenus.setMenuAccessRights(7);
            appmenus.setMenuTreeId("HMur5ShO0xo3LJ138I3cpijufJbCAAeh4yavgQ2q68dTRlns7S");
            appmenus.setMenuIcon("OkwWIcqpw2O0Kh3oawkkEHS0OwWCu3Pl9VpsJ50LcbiKx3xCw9");
            appmenus.setUiType("d1k");
            appmenus.setMenuLabel("4q0nSMdJxsxoZu6J6QJ9LezWd4WnWqMq1j2jxMl57Hm7ag7vuJ");
            appmenus.setStartDate(new java.sql.Timestamp(1471588733589l));
            appmenus.setMenuCommands("v5OBSoiY4mdpGidlkDZTFMVUk0qHsvVOpSppSLVMBJ2lrPJVrn");
            appmenus.setAppId("gjs2o0aujF2N2KlJCVH7AthqAiqELJ6lZLB2vt640aqCO2IpTV");
            appmenus.setVersionId(1);
            appmenus.setExpiryDate(new java.sql.Timestamp(1471588733597l));
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "QHmvWSfouhqW5iRlsF2uM8bbSmcvoQfIWmkMcvVdn2Fi50UV2DszYvheZGiEPaEezrCTzvDZdA4IoTWQgKuBOjwaIXEw5dcQiYna6X5SL8u8e4rMLtVYiIwFCHGG0QWfepaZa8TItP2RCwUe4dyxxZEwwTAKmg4HM78dlmu1wHxoQxDILvTVhA8RyxhLsN4MJBus2JYb5EjcpB6GNrMnAeOFhYyRQoqmOOiPzQVwcQ5O6fuuLjiKfPWWiAflcdAT6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "aWi1ouI2zSRQrpoEGc8BBxuaYBvjgA5p3PGA9XcbpqmctBi9XhrcdxJqr80weqIsCgn1L0oBpS76wQwjeLAVJIQG9PFHhlNeOK4zFGbvlF3vrJrim1HKSjHNH1ZxiUZaRUKtvrF4c3YNmvdlAU4tXCfO7PBWd0kzLICeLRDkN7ZobFLHb4IrgrIQ9CAAUkd5NoHEaHR0gekqBdBfkaD4Jlx02ovIIFZXqJ2sVSEXd4U40QCSqtazASUgjVR8wamG0"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "ZqamnfyLIBuDlqwOSp1YGlPrqpLeq19QagkdlwgvXS1ieEiBjbyYMKbIAWseayJzIRAkbty5NtqXtPTDtoKue764CP1Bqpstkl0wMYCK5bspVnAI7ZgYGyybVAE3ai7Y3VZ8p79TULudtDs21vyv031zREYJLJ1RAdXsog0owau5moToxXu9d244hvbJVCFnFu6AvHuxmp57HSy9Q2AYRN79USHFrda8hu07C94zxBTALFCyE9oNMFPc4lwyVioz7"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "axfbtSFE3gyJTmIrnYr5ZzKITI5BLwDbq40j31hVyLNBY7kR2tEQjSaJgtS9IvOC3"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "vUaUIkVrUOpZXp6SbEKC4K1iA3BBk4fdQz88yZxAcMUdxOu2ogbE8dEqFBCa22AnFV8qUCpSutAy7N22MQXAiNBq809z1HaGKbgCdjCzARUpZnhOxD0saQeknSK0u3iDh7HlPfCcYPGxTX5VMEmlik47SOBCjqm1NNF3Z3OvHnsGrfP5lMC1XBTo2FIHY57Puro2eriJ3j1VgjhbiAsODJn5n5x4DxCGYMdSiOEYe5DYWMYkdb5EUZ77fY9oUvawD"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "d0XL"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "VcSyP0lwlDZeWnvZo0EMrglOTTCJYJXOGzUJTqWZZyU0cklRv6HqY1oNP3g7UAiN7q4DdPArw6D7a3CYwOThtG8cMO39j9E9dOUMVkDRcUFeWgEiT3vsVlRmpJOpXtFLtRXJR4eFCDPiHdj54zJBRp3IhcdRdj9D3Ey6b3XBb892c8N6Kh8MXA234L1LDwyEzyHZLz2knPZxm6lks2Td5O6RSTmEEUh0wVgdgGSrM4KTDxBH5J3CwFwrWdc4dD6r3"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 12));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "JMfuul0jOTVZlOxR3oyst7g9AUfKXE0ErfFzO8MxIrrsPIP2O2DQvJYvDLxRuX6oNnGiKvAEpY9qJG5W0aXxOJI2IwIIOijGtRUAaqNDI16eJe2nYyk2gG4bekDc9nm6XlIrRPGVl2Oki151raAMRapJPu1JCV8jFSAfrTWc1ekreIAdWaemtKlmEJ4lhdO9u8v6oK7c8htR1d8wG00zs2QnwYsW0RD8YmwN7psU9Acy8YighPS2x6xXbG1YuRUwD"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
