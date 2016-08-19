package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCountryFlag("yQQ09WpOcZGoiqxSx7yC9xRZbV7ayOpjQwZfNV1qrYeHYpw1ay");
        country.setCapital("HfnzGw5BbTpzSR2DmdMHhCR5uTvAdUdr");
        country.setIsoNumeric(636);
        country.setCurrencySymbol("ZVgKn5ghunYXlolreRmKX7O9UsV2YLia");
        country.setCurrencyName("0DUJK1SLsFkoaMrNmuxIVRykK7XFgTLs0T3lwIMFslTncvcjMw");
        country.setCapitalLongitude(5);
        country.setCountryName("OaWuIy25SktRQzQ9WFjiY1kmGaGrFOzseUMl8PCciqnnAqF0X1");
        country.setCountryCode1("hE2");
        country.setCapitalLatitude(4);
        country.setCountryCode2("seS");
        country.setCurrencyCode("EdL");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCodeChar3("Y05ejADKrnqJ6eIcXfz8O52MgVSd7LqB");
        state.setStateDescription("M4RGmqAELQu4QmvuKesIw6gqEbmGb7ogIXIr0rQyfIlPeNjNdv");
        state.setStateCapital("zQIcG78S4KblcEVoImcnguxfnLmlFvwfORPTl7XrcfzRcbocRD");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateFlag("c8iwguubOg6Zbsud6Q53bycmr5kVSxLcVLeI0B5vVl23CdZeMj");
        state.setStateCapitalLatitude(7);
        state.setStateCodeChar2("NtKp2In966TGsVeJs0nzS6HVFtxacMpl");
        state.setStateName("ncJi03WoYrBKFn80Iiwa9XZY8MqdKOY7D9NMMC8c3KCJ4Cb60H");
        state.setStateCapitalLongitude(1);
        state.setStateCode(1);
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCodeChar3("axaYbCZr1taO969f9fb5gcvFP8AyfcPB");
            state.setStateDescription("BHGDlaMdwsBAzYH533w0ZOnb1Khbzi0cbvpi8dEQkZ97GTXLYz");
            state.setStateCapital("0lBoAhQciJFsHkW2lP2sPDI5Hxyv505CJ0SRcpfgudUCGD6ujT");
            state.setStateFlag("0LYhIIRVswNVUkRSfZxfHXYBh56u9anKI8HtIeIoVBX5xqpHLu");
            state.setStateCapitalLatitude(8);
            state.setStateCodeChar2("37EO7tWBSBIRcIOzEQSPmh9xj1XhIt4m");
            state.setStateName("MMGvktDYoJZzqHDpZxMps9c2kX9wIuiOMfyrUmy5ZDNOwlV5wW");
            state.setStateCapitalLongitude(9);
            state.setStateCode(2);
            state.setVersionId(1);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "soIEFaZKBJjqH5Z6PPXM8GATPnaeTm6YXQPHg3IVYOiiQpLhbbtz7D3gNmJQ2wvCH3z4j7uDPBeUi2eFXgND5pHAVtoKCPuAdNBTBlZjEfQYxAD2rRtA4UOocGWolxDwGoeAR3U2beuanplxAI36SgvQPFieZqIR80XqZMZ8PGPyNemXBm8lDCoE5Q3p1vs1kdZFf8VobQjN3pJUEUG60Qkh29S3orP9zERwgFipdzrq7C41kJ2Nv2LnGmcS8wM4P"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "dwmFjgx6qR7qsjlBZJomhDAYUmYHGbWs6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "OA0A7GKmwRRzlNhsUwaRuLHb5JTDX6byw"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "7RTb78BPfCL8uTIz98wuJRVC3zVcoZYGlONwePazjfVXpgK6kyD1GwHdw0PWWO0p5a8C2z6BUvlaR6gyiKcJy8c73EXX5kXlr9A9kKZgr9VrYYIQvg7IrZk06Izzku2Nl8jf4Lc3cvaIFbzETzgZIPUPjQqMBpIvu2bPdHbnx1elhRQg1oCg2XLMMI0mdlIUM8vPlY7bCuBCLzmfJ3JeBWa6vRjPzDssExcA8frm5kvRCtSWFGxXPMlynr6bc68IX"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "gaORCdl59DxIDgALyjg57hXox9VBcrntV2K3mt4aEiCMXED3bt9T7JSK5Y4fQG0oqjma4V2kf1rVttu31PbV0pSX3Isk0aQpsKhvqnFXwqgJb6tLuOZHmW4pKz0nyYaMa"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "dWr8BmKVbF56Gfowe1WMLZ9Hjm9CIPFGAz3DdldO2LzBQAKPjSu8jUl6vtnW1M4MBguwF5GRI8iPUhxx2Jw1QQohtveKg8B8T5bm7X13a96Kd8CaNWlkKW7Ljh0lrscPS"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 16));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 20));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
