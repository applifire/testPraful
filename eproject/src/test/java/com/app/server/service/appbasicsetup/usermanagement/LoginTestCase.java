package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("ZXfBXDs8PPvpB1MVeMlsS0EurlwkyEF7sCLvRDZCTwR3tCnvAg");
        useraccessdomain.setDomainIcon("AP0Jxt1JGnxz798WgsszzTZmLs5C3wDPP0UQXJMLrvv39ux5jy");
        useraccessdomain.setDomainName("TlY5moxRpQuslrLwFuq0SNEqpVxW334pTg8ZQroEI5bjRElw3C");
        useraccessdomain.setDomainDescription("K9L7S9SoqXWifRPksdKZE5jVaH4eHNjVT3MDWpiBKWygXz5vuT");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("4UmtZ3JFaXQbos12XDpWhRdfMJS5ZIT44wjFkhAWj1DWgavgox");
        useraccesslevel.setLevelHelp("tdX1DrUX3E5jiRd3UwB6vHxjqnpSRckvNfVMEmYAAcUrNapZfg");
        useraccesslevel.setLevelName("j0Ez27AXuVp6vBucvj1E5RdACyQDC0d7qiRYDoAbTw421DVnn7");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("Yw6w3pdiAbaUIWvYVKkE5Ji2RfXTsW2jwCU6M9Wc72ZhKehaQA");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordExpiryDate(new java.sql.Timestamp(1471588729277l));
        user.setIsDeleted(1);
        user.setChangePasswordNextLogin(1);
        user.setAllowMultipleLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setSessionTimeout(2000);
        user.setMultiFactorAuthEnabled(1);
        user.setPasswordAlgo("6xkvtOkszd6DpWm1pp7Ra0GD6yD7ivYKTeYIeWq13bJMfGfUEi");
        user.setIsLocked(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1471588729311l));
        user.setUserAccessCode(44197);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("cbqSGQeC8F5sUU9nGRP9XMAPftxma7eL0rODkjhrbDZI1h3t4M");
        Question question = new Question();
        question.setQuestionIcon("BjQpmQXxpRPzQ3i1b8hIIEPpgjs1Pv5wZfSdgeLYTgbBSvBQ9r");
        question.setQuestion("UV6XSjIbIxUAbyvDM0ktVJz9UVaBkqdWJvNPpUSBO16of9boQv");
        question.setLevelid(3);
        question.setQuestionDetails("8hxakXwPcm");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("yFeAgcSifcLgPViO5oxnlsWaog9CzNFZb3vTvmTGqvr7w7EZMM");
        passrecovery.setUser(user);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setPassword("GtvOBn1mZRb7KJAXezbw82mEMaItVvDLZA6aK8PSKw7KJF5Rmf");
        userdata.setPassword("fwpX9JxPgNsZq6FWGYuOnwcDjK1rQ6M8gAv82niSZxVRSzT8Wa");
        userdata.setUser(user);
        userdata.setLast5Passwords("THmrHTljGzrJCCyOZ57aajC2EJYTILgd1r4KZRVsyLFg1KfdRM");
        userdata.setOneTimePassword("gxILwDxumh46y1loIPa3I0NGB04afGBP");
        userdata.setOneTimePasswordExpiry(3);
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1471588729478l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1471588729545l));
        Language language = new Language();
        language.setLanguageType("9yEbEBLjlIhS1ZCSBuZLSVxmCY1ZTV3x");
        language.setLanguageDescription("RVZ8rtQqmy4XzaiNicU5N04uMRgxajMal0h9zXVYyJxMsGJkka");
        language.setLanguageIcon("VB2OItism9zoBmiVyjURTtp5cu53vwZ1ttLspF8xv7GKqhz7ym");
        language.setAlpha4("qePX");
        language.setAlpha3("Xqj");
        language.setAlpha2("ET");
        language.setAlpha4parentid(2);
        language.setLanguage("hA9ItVXT47t1J9w90kI3kTr5XgiK0QxTftOQqHNkaZNANRkKLY");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("rRbFuG7uTzXRsVSZbM1qbvVSESnM4s0xxtH8yVlPcuvC8aoVnr");
        timezone.setTimeZoneLabel("njOfffIoo78bULYsQjNCKbO4HLmsp3sMHAtL6UR9MzdTAuP9HM");
        timezone.setUtcdifference(2);
        timezone.setGmtLabel("28GMJomkpZgdwIBaV2PX58u0MUiMYjbZQprcjLUFoXRZFDtC7a");
        timezone.setCountry("7YKRXvWsUePbU1QQ7JIAL1ZTcAE4EbIEuNcjjlcoGgiDk9z4Ro");
        Title title = new Title();
        title.setTitles("xlGmWWJYjlqPbiiTCa4uC8hZxx1tSQuSWO25vSJUUv59Yn6Bcm");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("EsBX4pgRudcckjZ8dXFDRUrhHYbiSYgY0fsWu1ipHvxNBDP9Nh");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1471588729573l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setFirstName("F7XKhveDm4LumEunzlZO1CeO5SwgStqvgTw0MV2h1tFliowRIP");
        corecontacts.setNativeFirstName("fcW6CvSX5M47zGcXGOT7gKjQemPFsrTGRa8QinHH1MoD7g3O07");
        corecontacts.setNativeTitle("ObYy6bC8cRVH5RyO0wrQMLUnX3J0l1548wjtAECITR6p0LVGdZ");
        corecontacts.setLastName("ZeTyjFXpsDwiNPsH16Wmb3tGDLwZEm8ToSuSt78DDj3owcrONB");
        corecontacts.setMiddleName("ngbkYG2cYo4ivqDqVE6w3PTz2dIj6mMaVEQbXBF6CvK0Jaxmfn");
        corecontacts.setEmailId("vraDzI0tvf2RHMHlrMN2HLLmU4U9mRy6zkXxJ0ACDQ2GDztEd8");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(15);
        corecontacts.setPhoneNumber("Ut8ENqgZ0MWGXNqFz5qb");
        corecontacts.setNativeMiddleName("2O8bGdcM4PTfluIgaaSrRd8tD4DgL0HSmCSDQvtoF418uD25vW");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1471588729690l));
        corecontacts.setNativeLastName("XnKPnMqpXym5ERxwz48j0LAGcl0b3e4avAhLggnQbxwTCsp6yP");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("KJ3pSy9J7YGPFYgJJtSmt5pZk1KOnHxrIJzcLbvyOVq1JdDGEw");
        address.setAddress2("b2D5xw3ACZfDVCEn3z4t6MwotWMxNZorC9Amfeonlyqq7psLMG");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("V0gKUwggtUkvNs5C9kgqbyyDk30DjhhSeBZWP8dVkflr8MYwOT");
        addresstype.setAddressTypeDesc("qfqe0XzHTs8rSHJjPBrpneUklGgJMrCxIwqI8V9ADZEUTv4HMm");
        addresstype.setAddressType("4eRXUdvTjdasPCnmI5KqBjRNCGQrmaBkn98zEBCJyHmUfLidGJ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        State state = new State();
        state.setStateCodeChar3("LuvnUz8daKzl18QzGLXGAUXHAzsSLKnQ");
        state.setStateDescription("d7G0bEOHC4vzZZ2oyK99V4QGz9clS0HcXvGpeIrY6MyxzfCsEJ");
        state.setStateCapital("JpIlSWsmByG7r59AbKdbEV112dDbDkmbvamSNcr0r4SgctnfRp");
        Country country = new Country();
        country.setCountryFlag("0c4GvN0Ctj7fEBnorn1pear33Ugz6z884tqTNaaqrYtRNA2TNM");
        country.setCapital("XZi3523i4dk5pjnvfsariNdrJMYL72Kb");
        country.setIsoNumeric(157);
        country.setCurrencySymbol("yUib2aBO1dpzXtiqySUX21mgsTVoszOE");
        country.setCurrencyName("avagTffkOWdPTH8IjMhRdaphAbSkSYJlYZFHCNeHoWTT8oROBG");
        country.setCapitalLongitude(1);
        country.setCountryName("Q4HeVZWbnO9JnZSBI1jlAJdqKI1q54jROpTekCc2xnYtsaWbV5");
        country.setCountryCode1("4hL");
        country.setCapitalLatitude(7);
        country.setCountryCode2("8ON");
        country.setCurrencyCode("SO1");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("LVSq5THsiJOoT1fxsJbB2SiLnUvPnSql");
        state.setStateDescription("VZNJEaXFzWWRXKnSQ4ffzygcbKaNnBwBMZyCZybiUjeIlDVpuG");
        state.setStateCapital("u45Or1AYub295HgLJ9gxhY301SJwJT9SIfJnjrXOoKJM9w9CzD");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("Eo6p0uo303UnePqBe80cCGKzo8NrggtRsmkLGsVcnju1esl4CS");
        state.setStateCapitalLatitude(5);
        state.setStateCodeChar2("UZPiezP9uOkp9WBdWX4sSbSZSnJa582w");
        state.setStateName("AijS8S043FlrVSNLtnCCh3O2UmezBTDqoupQUE9F7da6KEDZIj");
        state.setStateCapitalLongitude(11);
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("p9ouON7wU9zDn440MNXoLqcEYL3eiLlL");
        city.setCityCodeChar2("04BSmx7lJpYRZXBaVtks8vYHqZmJUBqF");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(7);
        city.setCityCode(2);
        city.setCityName("EYpslJoxsGhoV8wCKopL4WFstCcvz0T90o8tL2FIHVQ7OXog4i");
        city.setCityFlag("SdBIYHjbq794wMcqKx8wAYuSMPIRPP0f5j7MR8LpW9EhYLjq0E");
        city.setCityLongitude(7);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("8wh1xjQ686EASfsPsh8unM3nxMqoCSJzMcXzkuu39sgJ7mQw1K");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setLongitude("Q5CugorR2g98kZQoRpqO5XUgGOf5cOEgYP5TAysB8Cx5tyjTrn");
        address.setAddress2("9ffXbPJuPwepluebPG5v6fdcqhZPy7yOgFtPiyYuOEEOwOm1UT");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("GvPUK8");
        address.setAddressLabel("eEHGmH2DGAu");
        address.setLatitude("cORz8V2FPf08NuurofqhY5KRNC6LF4F65fk4qa89soH1DxLLpT");
        address.setAddress3("dRh1xo0mchhw6Vr2Avanv2iUi7J20P2gz7iQbsXjhNi6boQiAz");
        address.setAddress1("xfLCkNDhc5FXiI2bCXa6Lo5h2dW3170ThcqgEkJcxlAnVw5mWf");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("y7KikQmJo2qO2bT5nFdjHlZCsMwjVv3gpUeuknoK7yP6lUaNHr");
        communicationgroup.setCommGroupName("7bdgtqtmahbvfAC5b1bFvc5z70SVBew7Ehn1nTEJPVX4pYb2Ls");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("tfEcsG6KRNOF4mtRRfatKtJEu7gnxk3GZygZt35rMY7pMXUVCJ");
        communicationtype.setCommTypeDescription("CiENTuo4Umk4RgJMcmrk2J4tHIJTfiAPmIVldYzp9X9VnRHJrz");
        communicationtype.setCommTypeName("JAuxBw0lnDtNYwAIbD1gyILAQi8KxBvCJAMPn3V4aS84QaKSr2");
        communicationtype.setCommTypeDescription("4kUJri3Y6Zjn2eWdIyZWuKu4yOXEhmz485YLGriHFcwb1bOyUi");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("COiJJ4EJOx");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        Login login = new Login();
        login.setLoginId("gXLCpYiF5ub8nEAXdcH5s5GRLv5PrrKKAeMWR55Bg2bsiy9v1t");
        user.setUserId(null);
        login.setUser(user);
        login.setServerAuthText("4xoyZmDLFVc4UKKA");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthImage("vvXQj6wDBxnu6RaKplesMowkrxXv7Sdc");
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(3);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setLoginId("7RkizlTn53E8e1XqM5ZLl9qmZjUNabYDCUlA7AxEaYZtUK3nHC");
            login.setServerAuthText("wU02NlQDgnr4nkvC");
            login.setServerAuthImage("2gGEg5RkDxAt4SpOGQZsWOXpkpDUdspJ");
            login.setVersionId(1);
            login.setFailedLoginAttempts(7);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "68kLXJJ10mHQTmW4nHMWOf2wIiF3t5rtTfK4rNdwEp4M5JTeh5KeVDCe0cV1dbGT5EeUyLCjGsrq0U61ZInul17aTjcnlrFv5la6jZDaMS2dZOc2tbucnXaRoOX14ejrtfXn3Gxf9Hl8cUIpbsGHQDW6hfOQLnnjbb1VEQ44cV8Ho0H0eXreOcEpWqdNCLNXnklLAxC4W"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "IDpeYCk4RIXw2ebwjXEhWewnULqivcl2G"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "OF80OTUaKK1NKCRj3"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 14));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
