package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.Question;
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
public class QuestionTestCase extends EntityTestCriteria {

    /**
     * QuestionRepository Variable
     */
    @Autowired
    private QuestionRepository<Question> questionRepository;

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

    private Question createQuestion(Boolean isSave) throws Exception {
        Question question = new Question();
        question.setQuestionIcon("5IBpcV3ms1lKVErXSzIURsuoVQuGhob06NSc1NTKZXFwBxxVPo");
        question.setQuestion("97RTsnb58Wx82X6pUp03v8SUGhXXQELZZZrMHKfdLEoCG5XV7h");
        question.setLevelid(7);
        question.setQuestionDetails("sqSbweYGod");
        question.setEntityValidator(entityValidator);
        return question;
    }

    @Test
    public void test1Save() {
        try {
            Question question = createQuestion(true);
            question.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            question.isValid();
            questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("QuestionPrimaryKey"));
            Question question = questionRepository.findById((java.lang.String) map.get("QuestionPrimaryKey"));
            question.setQuestionIcon("gijr2jCFRBu3ejyTcrItVBLSYiempWxFEilbBUQAek5KF3Cmkd");
            question.setVersionId(1);
            question.setQuestion("ombFOPn9astBS9bGbO0fXLooUTYsar2pzmUCa6yqElsnkx1Uly");
            question.setLevelid(3);
            question.setQuestionDetails("Qo8ABXvMQr");
            question.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            questionRepository.update(question);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("QuestionPrimaryKey"));
            questionRepository.findById((java.lang.String) map.get("QuestionPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("QuestionPrimaryKey"));
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateQuestion(EntityTestCriteria contraints, Question question) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            question.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            question.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            question.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            questionRepository.save(question);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "levelid", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "levelid", 12));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "question", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "question", "RILV2L65U9FhUsslxnytaKSEfWoNu6PFkmofkgyyndXUEZfN8aSxY3UUIJT1vyIieujgjBBwM6FGGLz5xuOIgMDiW9wJnfyl8uQrTRVikzBpYyrblddsyCrXpQmTDQHDvNM4yGaI8bZZv8FQGrr9g7rqYUhdZR4HggTh65ZRLSRZ2FC3PoO2tKtzmADUQBtj6P4U19RaSJ8nvh1doAnejKRBGOunlhKVYXfp3GwrWFEzf9iSovSFvsZzOEIlq6Wfc"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "questionDetails", ""));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "questionIcon", "SyvNXTxb4JKebAhY4flDPO7Q6dyQ3mi31kEQP6FK4Lk56n3vl2WfubKJ0xi9G74jH"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Question question = createQuestion(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = question.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(question, null);
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 2:
                        question.setLevelid(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(question, null);
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 4:
                        question.setQuestion(contraints.getNegativeValue().toString());
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 5:
                        question.setQuestionDetails(contraints.getNegativeValue().toString());
                        validateQuestion(contraints, question);
                        failureCount++;
                        break;
                    case 6:
                        question.setQuestionIcon(contraints.getNegativeValue().toString());
                        validateQuestion(contraints, question);
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
