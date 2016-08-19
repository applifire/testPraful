package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleName("L0j4nkKKx424ic5CDYL4ZXe7bb1AG1KPPKfvJg4RMw2XK6f4C9");
        roles.setRoleHelp("lkImxgw1Gc2jSG5jTaaknHhDU8lY1XXFkHCIowZIfGmBdEKpfq");
        roles.setRoleDescription("kAlYhVXotTYr9koVh9HiAan3SfHMikvF1bnnMwI3pUhMYsIRzb");
        roles.setRoleIcon("hgQvHR2jGc8wcGfaqjUcvz6bwlIhwlqjjpI1voUvDK3CkyyCWO");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsRead(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAction("OVw9M7VOkUes0b0QBC953Vnpmjhk80AZR929YxD3QvxER06Fix");
        appmenus.setRefObjectId("OSpgD0WHjQ1KkTUhzLfA3d6e4UvZVDUsbBNYzs0mGYbzf0H3BI");
        appmenus.setMenuDisplay(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1471588732473l));
        appmenus.setAppType(2);
        appmenus.setMenuAccessRights(7);
        appmenus.setMenuTreeId("vBqJP37FUkKFob0ftolrXsYEYuFfztBAQHailSwgHwSaDedWUx");
        appmenus.setMenuIcon("AKwLihca39H903XazMFGcXGbWMWBF0WO9yKSanmKq9oDQ5JTiO");
        appmenus.setUiType("gXz");
        appmenus.setAutoSave(true);
        appmenus.setMenuLabel("0QRsOSDMLay87rjQCIAmK8kte4FUNdf9YbkobOY2SSNfsr24Fz");
        appmenus.setStartDate(new java.sql.Timestamp(1471588732473l));
        appmenus.setMenuCommands("zaEDyRDqYLPha1n2aPVIOGQSEuaTF5khoUN8nPHhxdNRigTRA4");
        appmenus.setAppId("7j9wkscPt7SSTPQ0MMN84Y2tQuiyPpKDGLnK0qYIu5k5PbcP4W");
        appmenus.setMenuHead(true);
        appmenus.setExpiryDate(new java.sql.Timestamp(1471588732473l));
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsRead(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleName("KEGHskjpwfJjp009CQIN32OS33mu804XXvZT3cO5g41P0CiJRU");
            roles.setRoleHelp("Khh4jM7UmTdAtBo43oxWO90BFNGcF2qc5lBxyUK6M8ATb6gQZd");
            roles.setRoleDescription("dT7aYBjZcSVhvTG8M5s3YmEhotAPE51O13ijseGVSdHpA16qrY");
            roles.setRoleIcon("mw2ZKuikJPRNL9SqwJckjZ76evfcD5hqaAERQFHM49zKk6sj58");
            roles.setVersionId(1);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "Lc6HmRNojlhPA6TuYuxrwjj5rvQ9IzxzLRjvE8ujGKEqpFsLKDI5lgUONXYIfcYQmnEJWCWzylSihQ5QOQ2B3rgW5kkLJDCmvenMGe2dXMSHfV5M6xtUPvUezK0Eb1f31XJ7F6YQVbI10yD2yHWxvlVkg6rfIk34okRsYNjpKeoi4Xl3Nhq8qHV3mM4ctIqWpGq8g6NRl5XMIQatbdZMSSxEAQg15YOKWtYFaNJDoMnjBNhTCttZHKQMstKwqtXl4"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "H1OoYcZrdhkY9kx17eGcpsGUoFcXfHktvLfry9k9ujV2vS4kqc3CRKL9dKVDi0Oo1jLFcRIpV1La32QjF2HRKPYI2pGNXTMIYesw2yx5f8GLvjUaadSsk5QliM26dMTzB9oeFdUuKgJXsvWtVTeeiPormG5ddrENyEvLQgp4eNsdNWIwpjZg1Qem8RQZJZUpR9Pc3qSL8xNePGTJP0nxN4pjsWK4ZLFnKs6He6sSPWycqu7VJba42CfY9VrzItKJI"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "L2r0IE5Np1IAIgm1MqBcljIBcZ19oNVqz9DwTNDKh2qGczOFXnD5qFs6swSJdlpy5"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "jFaSo5Hip2JNBhOCYJPCcuPd8RuPUvwHbiMe2MA6wKxa2DzYbmnHqcXxLOmzFJwthk9H2dTXBGeSWhaxJvEv0tE4f8iBngjhctOuqKd7cxxk6TLfqp4jUTWHULnJhIM3UtRMS0ptprFKcxlbeeDmFArSANho7TjogcdtxxZr4ZOGANVRA7T90EydeRsJfd5Ws3ZgXJflD4lVvDbzqJkuQtaD2ZXbA8kH7IboCE2WVjFmPinoqiaeCxnW9Vf3pxW2K"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
