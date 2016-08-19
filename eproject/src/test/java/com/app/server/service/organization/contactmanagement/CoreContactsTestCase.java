package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setLanguageType("GcjvQYBy3VvHa64XfRT4A8H1JSbVQTKq");
        language.setLanguageDescription("CC2yZQbR67xoKNJTi08KbZbcJxa90c4kg9Sr9z9GpBGT2ZWs0r");
        language.setLanguageIcon("Gx1W2YGochihzH7JdboLWRt2uZ43ZuN3RbMNWg3flhPgczWMiP");
        language.setAlpha4("D0fO");
        language.setAlpha3("Sex");
        language.setAlpha2("t2");
        language.setAlpha4parentid(5);
        language.setLanguage("cDLhYynFFjJoYBiUy1mz0j8YqYsw7T9NTeZvTUEtlyxgJalcAV");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("UC1ilpgDZSkcSeGGNGYPlwVhRbucttgHAqobxg07bk7MGZBEP4");
        timezone.setTimeZoneLabel("HjjFOcxDqXrjHEOPeuuzY1I9kKcv26sc9KbeNUZReqg1K9AqZ1");
        timezone.setUtcdifference(11);
        timezone.setGmtLabel("T4zgkDdIa3oHHoWkATBDmLJBX6sz0UxYDUGfScVfBVjJVMejA6");
        timezone.setCountry("hkYegHM3jBevYroeKWodsxG1nepVRrILpOSi28im4blBUEf3I5");
        Title title = new Title();
        title.setTitles("jSO5Vh6BvaYNLdNxM7zccYoa2NDI2XNtWCLH55yeOYU1gNM4Fd");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Gender gender = new Gender();
        gender.setGender("z3Twh5UZcKzaQ3z4Pwi1JiibGy6kP2BAUDFFIfhPB7BwiZ3OBh");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1471588717452l));
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setFirstName("pRmuFkNTtnFzXuLqvlU8i7I0PiapUvIbUsWfTpiICNJ0EQOd1n");
        corecontacts.setNativeFirstName("hewHDWUjY6DyIRYEoeX2b7OhMS2mVGC89AJvcWwYgcDNru0Y0g");
        corecontacts.setNativeTitle("MEIPfB0y51bmVpwFFZ6GxzJrLmAgSu1QtI6Naw15gZRykHHAZx");
        corecontacts.setLastName("HNlqpKPcQ0nwpI2AauX1LhAm1FJn6htGMndpJDrMqzjh8kucKV");
        corecontacts.setMiddleName("pgWno7Owi1fGVnudXotrRIAUAeJoAbFIPdFzBOZ8tGD1q4FXh8");
        corecontacts.setEmailId("toA1DllxYK5cYi5dbF3uiIG5aAYieq1B5wRXYEPsV0QhGVdmPP");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(89);
        corecontacts.setPhoneNumber("1R5pQeSAqN0uBLBhubUi");
        corecontacts.setNativeMiddleName("Ebk4otE17y7oHnyNVQyAK9L37F7JOlAPIUfjKE7tCzgpxKZCRS");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1471588717568l));
        corecontacts.setNativeLastName("pHAtc9zUvYwHvD9UROcBNjHvaAwH82v7pvsJzi6XCygxqrTkhg");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setLongitude("aBctbzM7QwRNvR0dozdYXREQtuG3I3HyYEWNqBXgUCi1Ydbykp");
        address.setAddress2("gMZxI9XWHdY77dU8NVhpA2yR1I4NVesXW1pkW9613AavpAGH12");
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeIcon("u5zbQOR5Xe7amIvecPmUYIhYU7gtiimCypBzZTSQiH1q6Nbwh2");
        addresstype.setAddressTypeDesc("hwZF4yqzz79WrvTzXQ3Urn2SfqGESLPcpF17LKBrUw1RmTavBI");
        addresstype.setAddressType("bkyg0Ed8qqkg9139z5E4xZsS314fvu6tqHVnI9XOxr9mufbIdJ");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        State state = new State();
        state.setStateCodeChar3("w51OIh4bd5YBI3lhXNLDvcnKhMoON06w");
        state.setStateDescription("fN5RFLJ3oGxqh8jfNN2XjMFsLHzA3zVjefzwzYNf4gMbKx7oSl");
        state.setStateCapital("YdQAexhqASs5bg6vPHMU8lZ2LfJoTIuDU7vQp9ZgZOYdBPKyLt");
        Country country = new Country();
        country.setCountryFlag("IO3HoNDHsHGnDz1dbVebWEjK7jkRvym07UTAQV2ZmTXSqnuJce");
        country.setCapital("KrxEiMzhPRm9Ri2Apzh5DAmCWHOkAhhE");
        country.setIsoNumeric(496);
        country.setCurrencySymbol("fMPSnnLveDvypwOIfsX3eannfikK0peO");
        country.setCurrencyName("J4RHC9qWPVAFjWsdNYtfaZ3IcZSInjHz36VKYtZNGwJCvpSayv");
        country.setCapitalLongitude(6);
        country.setCountryName("mn5l5m85wzyZNGkBR3Sbgqv4ZK261CUXjbDGGGE1FtoZwQUbNe");
        country.setCountryCode1("qzU");
        country.setCapitalLatitude(7);
        country.setCountryCode2("kV0");
        country.setCurrencyCode("y3s");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateCodeChar3("2Bn3CTTW6m6oiqA8wQBrcqOURlWHmp4I");
        state.setStateDescription("w7RLaqOeU9TALAzZ0WE0p9jL0qI9N70HOA2iPB2IrosPXLHSOt");
        state.setStateCapital("pn13wpTMaJiRZuK3xv48ADJpuSLW6JNz3uLVrCwEXgXaPJBLaX");
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateFlag("R4B3zw33NZPk3dUhhKwg0sG1YUBECIYGlZonepleLsk3rwG8oH");
        state.setStateCapitalLatitude(11);
        state.setStateCodeChar2("X2vSeRBRImaLPxgzBYqGvCuDsds35YXr");
        state.setStateName("Ul43KYVllLHkcbOpN4HzM9QmuIcgrYsY4R7ErGwRbHQx5TAneD");
        state.setStateCapitalLongitude(9);
        state.setStateCode(2);
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityCodeChar2("czTULyBcJLAzP6MDfBeHeumGNod0Buno");
        city.setCityCodeChar2("d20vDx6qq9gjBTXaHVLyC2U5OSehq5R7");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(4);
        city.setCityCode(2);
        city.setCityName("Z0u8gJBuK675DdAgzWzZ3ymIQVvRPWfGaILP7NDOFMGmiMC1ps");
        city.setCityFlag("Kfibu1jrQ6CqODmY6dsai0LfOlu4xeZLrrxNiw5j9tvwxRgtZA");
        city.setCityLongitude(3);
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityDescription("AJfCnwpdhTY0yjksUxiD71pkcGnqFVX10z6fSzsjaDhxpVgnb8");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setLongitude("SFd6O6i7SmT2phel2zFVWl8iNX1s6clmuJO7mc8cIRcuKnpFeK");
        address.setAddress2("q3o9PdPgJM7yaYgxoKLhlElCOBvQE5wdtKE3m3ZprJxNfQDS8K");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("WjSNqN");
        address.setAddressLabel("DLBUHSQhMgG");
        address.setLatitude("ZFUlwiamga7TudutQYGrmrSw1GlIXNejdFmM8L56fz8jU0GtUX");
        address.setAddress3("is5QPRDvMNdAmp8nFKIuwjT5AK8KVm5mNofplFVwbLMLp9tRXZ");
        address.setAddress1("FrbRuHVaJ5LFlRxV451OAHo2G0IPvF1iNKHzjnchCC9zMixP1i");
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupDescription("ABI1v6j4aDy7J7RzBqXFHdjDSzZ63UzRNSWjJr5URU15D2n089");
        communicationgroup.setCommGroupName("kRcGJh9tIId0COumO39FGccTg3MrRVkrKQp7JmM4HEsN5lFXZ1");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("fdgVorl4K7Y9eY450kPMnhYnUHJvpK7VdNzyyI82J5mjtTWMa5");
        communicationtype.setCommTypeDescription("VZPK1c4esBFoqY00Fsm9WVUK0kXte8NiSQm77MgGyujFjU46nq");
        communicationtype.setCommTypeName("6JSk6XBOSpoy3CxoV80GX7dcgwUnoPiicje9qO72gYudpEghH6");
        communicationtype.setCommTypeDescription("8kxLeM4GEsDG4LaNiND3V5rPGJTnRvEFLPzTsUqJ5FmXkRU71H");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("S1xUKcF3O0");
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey());
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

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
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1471588717854l));
            corecontacts.setFirstName("cly2XbtDfX5kfzUmVCbHAdbzEVb7zPrpJjLwd79ZwqzgqrTfdx");
            corecontacts.setNativeFirstName("KHPKaKDFL5dSqq3KrK3qvMhr8Otz0IDakLClgjWdPCYLZnkgUl");
            corecontacts.setNativeTitle("L9nQ8ONcM2WjKzXBJri106q6DeiyUHNbR3P690kwTk3Sayks4A");
            corecontacts.setLastName("ReIw7G997F1HLGpACCuU67FZJt4q8xEA7j3YwTcgQ6PYW5GhOw");
            corecontacts.setVersionId(1);
            corecontacts.setMiddleName("sis2AIPKaQb76cYllqoSTKgvpYyEA5poyDEqgg5jifob05Aqnx");
            corecontacts.setEmailId("y4cwbz78OihddMeh7HbxPk9gYYFR9ACqqGoUfL2m4zPxpIwMm0");
            corecontacts.setAge(83);
            corecontacts.setPhoneNumber("pP60tZsbKailDRhFNBrd");
            corecontacts.setNativeMiddleName("P3ksI9d81PVqLpIEfS9fZCoPRZCwkHPm8QYDu7BMZzFMR1luBH");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1471588717980l));
            corecontacts.setNativeLastName("vxxWUvhE1i4U2UeHDLbJQAzhvI5GsTn3YzkPwSb63f5TCgrN6v");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "kiUPUHPEnijPdjnApRAYc07gPUruzAUENSlZIxIxDlIfYK545uCm9lFfPQJL7AznSIgZixLejWTxI4lWd8J7ouu5cta1mlkZs2lpvVS6Q1HWcZ2AdsKVztTmQfdi0rGZviAAyTi46b2UNt0nWl4VNEPHaHIgViaoUdUYYyqmnDUBuyvgUj7J0vS8noCXHpErlN9KisP8Hd2XFeGyHrZzvusvH6zbdNqkWJvtbGrd2h2eREWNbtsznrR9Jl5g2syDP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "ZEtrfCa7Xuc2WoHUFzypvizdwF0yw0fF3FtNXG8sgVDIPbRVqegbqti6vnnPjDIMBDcS5uh1NL15AlewCbOk3y965zD73gkJhDcQZiywSuK1vtrZWHCg5Wh56XGrcbNclHTkJafJh5fH7I7gzI2humP4EemEZLeJryeKNngfeGi9yqETyIXRqPj36w7UJyDBRFOWT8eoqxGq9Yona1Nx8OzMTHi1cLOYLuvQlCbai0V5BDeYAEnZ5h2PB2Zh6xwWT"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "B7aI4Xni6u17XvmnxPT0AFgeNn3rBYDbym9l43f22z3GV0164Dkj4pf9Kb6N4N7wFNXbeK1lLTfmC33AUxRywKhmQbA00Yea7AJejhIgfHWNqFyD9YwuxhwELkT9dYYfrAoIxl87CrF76khoe4FyiVDz9HKaUSuixBpgYG0uASiB1EisBcsobNlnLKP2ENgNre3ai4F4Wt0jvOT0mnSMP5RHjpZ6acmMnrenAQzlHsZgtccTP9oCda7f5mjOPp31H"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "oXYtwrpAzQdWCB6S68g1ew0NlF733Y6oFnjuwMn9ZTus7jIEuOwkYkJADQiVwff6i"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "xNlnwM6foF2e4uc6cDxHJHAZazqbdoXX0OP9zELFy5jkAvwxz2m7hhNLtKkewEMubyHVGjMWRRUsf5wWG5jAVm6vLQsozYcl6GZZUKOF41roNxoM9YmjB0VbACjCohQ2STa15P5UbEJikrWLQScWCnNu1o7Z0seR8OfqQmFw9WJbrrHrICbPWr4UYG3RQajkqJoOxAZ9zMVbi6rwzBybVMuLs9gQYPWfQKurrmH61Vckzvb9enhoUgdEeg6xWaXxH"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "F0GqowXcHXvzSt6wGV7lzHjVtUpPdoM5WwG3uGvFpWeBpVyX7tqxQHZMVFXgYvvHzhBVZpyVN6erxOX6Bts0DjAfZ3sh0WnWnozSYuujl22M4G0uGqLYKAuHjJ4LTIOuk6TAQMD5Wg7UcjhMrVU1oOXFnKBq47PSFJHuWMFSdl5Tw1StmO4q6bpOI2MPCwz6HEHEpR4WjQ88TeduNogPqy2ZfAvaDdi4E4mFn8OfeWVVZFCuSIvTS7OsV9bsrkkcv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "jwWqCpL0VBnsZDtdPQ7QQ1gCdKKQ8lOoW4uaEt2mC3bObEsyCSuTvMGoLfgvRHzBBMg1nSbpQAmwrbMKriyG9pPAwIm8XS1AfB0lPHQUdCNZr8aO2V6nc1oWp87JoYlRlvZ8QYsNq5yBXz9JUGqoE695L6dmSXuQGbVN2aWyC2e3FwX9GIaYCk9EozGlSh9gxYOkQplOREvjBXLqLpWV0MFZYruGbNhqINmVhk2CDWLbxCuviaon2aCvS8TiRHkxg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 166));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "pfByxb68OMG6eyinLRErXPl3fzfyQx3ddMJVWtunzY6yTiUb7ZEX0Ap4je1SWbYQkblgHNPYU8mfGiw0a6BMHWBuENPpmxZo9oPLPx6ye2E0eJgjV01hA7IN6ABECQwxLhHj5wPfN2A1lz7j5DqOndl1qZVTgYeRI4Ke9iT2ijLuy41wzUy1CMuFdTp6ECUytiXs5UBuvED0qRxYCZ9ulA6xax1erJOUAtY64MHzZpT19Y6rp0rsqctZ3tJh3yrIw"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "ddI18V8JdqFfUyFT19hmY"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
