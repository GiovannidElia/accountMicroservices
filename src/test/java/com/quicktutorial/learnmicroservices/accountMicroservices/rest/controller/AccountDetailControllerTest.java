package com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quicktutorial.learnmicroservices.accountMicroservices.TestDescription;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.exceptions.NoDataFoundException;
import com.quicktutorial.learnmicroservices.accountMicroservices.common.model.ClientErrorInformation;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.AccountRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.OperationRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.UserRepository;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Account;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.Operation;
import com.quicktutorial.learnmicroservices.accountMicroservices.repository.entities.User;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.AccountDetailController;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.delegate.AccountDetailDelegate;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.controller.account.model.response.AccountDetailResponse;
import com.quicktutorial.learnmicroservices.accountMicroservices.rest.document.CommonDocumentRestTest;
import com.quicktutorial.learnmicroservices.accountMicroservices.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("embedded")
@SpringBootTest
@AutoConfigureRestDocs
@WebAppConfiguration
@Slf4j
public class AccountDetailControllerTest extends CommonDocumentRestTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    EncryptionUtils encryptionUtils;

    @Autowired
    AccountDetailController controller;

    @MockBean
    AccountDetailDelegate delegateMock;

    @Override
    protected RequestFieldsSnippet getRequestFieldsSnippet() {
        return null;
    }

    @Override
    protected ResponseFieldsSnippet getBasicResponseFieldsSnippet() {
        return responseFields(
                fieldWithPath("data")
                        .description(env.getProperty("rest.learnmicroservice.detail.response.data.field.description"))
                        .attributes(key("pattern_domain").value("")),
                fieldWithPath("data[].id")
                        .description(env.getProperty("rest.learnmicroservice.active.field.description"))
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value(env.getProperty("rest.double_2_digit.domain"))
                        ),
                fieldWithPath("data[].fkUser")
                        .description(env.getProperty("rest.learnmicroservice.passive.field.description"))
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value(env.getProperty("rest.double_2_digit.domain"))
                        ),
                fieldWithPath("data[].total")
                        .description(env.getProperty("rest.learnmicroservice.balance.field.description"))
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value(env.getProperty("rest.double_2_digit.domain"))
                        ),
                fieldWithPath("timestamp")
                        .description(env.getProperty("rest.learnmicroservice.timestamp.field.description"))
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value(env.getProperty("rest.timestamp.pattern"))
                        )
        );
    }

    @Before
    public void init() throws Exception {
        String encryptedPwd = encryptionUtils.encrypt("Abba");
        System.out.println("Ecripted pwd into DB: " + encryptedPwd);
        log.info("Ecripted pwd into DB: " + encryptedPwd);
        userRepository.save(new User("RGNLSN87H13D761R", "Alessandro Argentieri", encryptedPwd, "user"));

        encryptedPwd = encryptionUtils.encrypt("WeLoveTokyo");
        userRepository.save(new User("FRNFBA85M08D761M", "Fabio Fiorenza", encryptedPwd, "user"));

        encryptedPwd = encryptionUtils.encrypt("Melograno");
        userRepository.save(new User("DSTLCU89R52D761R", "Lucia Distante", encryptedPwd, "user"));

        accountRepository.save(new Account("cn4563df3", "RGNLSN87H13D761R", 3000.00));
        accountRepository.save(new Account("cn7256su9", "RGNLSN87H13D761R", 4000.00));
        accountRepository.save(new Account("cn6396dr7", "FRNFBA85M08D761M", 7000.00));
        accountRepository.save(new Account("cn2759ds4", "DSTLCU89R52D761R", 2000.00));
        accountRepository.save(new Account("cn2874da2", "DSTLCU89R52D761R", 8000.00));

        operationRepository.save(new Operation("3452",new Date(),"Bonifico bancario", 100.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3453",new Date(),"Pagamento tasse", -100.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3454",new Date(),"Postagiro", 230.00, "cn4563df3","cn2759ds4"));
        operationRepository.save(new Operation("3455",new Date(),"Vaglia postale", 172.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3456",new Date(),"Acquisto azioni", -3400.00, "cn2759ds4",""));
        operationRepository.save(new Operation("3457",new Date(),"Vendita azione", 100.00, "cn4563df3",""));
        operationRepository.save(new Operation("3458",new Date(),"Prelevamento", -100.00, "cn4563df3",""));
        operationRepository.save(new Operation("3459",new Date(),"Deposito", 1100.00, "cn4563df3",""));
        operationRepository.save(new Operation("3460",new Date(),"Bonifico bancario", 100.00, "cn2874da2","cn4563df3"));
        operationRepository.save(new Operation("3461",new Date(),"Bonifico bancario", 100.00, "cn4563df3","cn2874da2"));
        operationRepository.save(new Operation("3462",new Date(),"Bonifico bancario", 100.00, "cn4563df3","cn4563df3"));
        operationRepository.save(new Operation("3463",new Date(),"Postagiro", 230.00, "cn7256su9","cn2759ds4"));
        operationRepository.save(new Operation("3464",new Date(),"Vaglia postale", 172.00, "cn4563df3","cn7256su9"));
        operationRepository.save(new Operation("3465",new Date(),"Acquisto azioni", -3400.00, "cn7256su9",""));
    }


    @TestDescription(subject = "AccountDetailController",
            description = "Effettua l'invocazione del servizio /accountDetailExtendedResponse/{userCode}",
            expectation = "verifica che request e response siano conformi alla definizione di interfaccia dichiarata per produrre la documentazione")
    @Test
    public void accountDetailExtendedResponseDoc() throws Exception {

        AccountDetailResponse account_1 = new AccountDetailResponse();
        account_1.setId("cn4563df3");
        account_1.setFkUser("RGNLSN87H13D761R");
        account_1.setTotal(BigDecimal.valueOf(3000.2));

        List<AccountDetailResponse> list = new ArrayList<>();
        list.add(account_1);

        when(delegateMock.getAccountDetail(anyString())).thenReturn(list);

        MvcResult result =  mockMvc.perform(
                post(CONTEXT_PATH+"/accountDetailExtendedResponse/{userCode}", "RGNLSN87H13D761R")
                        .contextPath(CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(document("{class-name}-{method-name}",
                        pathParameters(
                                parameterWithName("userCode").description(env.getProperty("rest.learnmicroservice.request_type.field.description"))
                                        .attributes(
                                                key("mandatory").value(env.getProperty("rest.mandatory.yes")),
                                                key("pattern_domain").value(env.getProperty("rest.learnmicroservice.request_type.field.domain"))
                                        )
                        ),
                        getExtendedResponseFieldsSnippet()
                ))
                .andExpect(status().isOk()).andReturn();
    }

    @TestDescription(subject = "AccountDetailController",
            description = "Effettua l'invocazione del servizio /accountDetailBasicResponse/{userCode}",
            expectation = "verifica che request e response siano conformi alla definizione di interfaccia dichiarata per produrre la documentazione")
    @Test
    public void accountDetailBasicResponseDoc() throws Exception {

        AccountDetailResponse account_1 = new AccountDetailResponse();
        account_1.setId("cn4563df3");
        account_1.setFkUser("RGNLSN87H13D761R");
        account_1.setTotal(BigDecimal.valueOf(3000.2));

        List<AccountDetailResponse> list = new ArrayList<>();
        list.add(account_1);

        when(delegateMock.getAccountDetail(anyString())).thenReturn(list);

        MvcResult result =  mockMvc.perform(
                post(CONTEXT_PATH+"/accountDetailBasicResponse/{userCode}", "RGNLSN87H13D761R")
                        .contextPath(CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(document("{class-name}-{method-name}",
                        pathParameters(
                                parameterWithName("userCode").description(env.getProperty("rest.learnmicroservice.request_type.field.description"))
                                        .attributes(
                                                key("mandatory").value(env.getProperty("rest.mandatory.yes")),
                                                key("pattern_domain").value(env.getProperty("rest.learnmicroservice.request_type.field.domain"))
                                        )
                        ),
                        getBasicResponseFieldsSnippet()
                ))
                .andExpect(status().isOk()).andReturn();
    }



    @TestDescription(subject = "AccountDetailController",
            description = "Effettua l'invocazione del servizio /accountDetailBasicResponse simulando un errore di tipo NoDataFound",
            expectation = "Verifica che request e response siano conformi alla definizione di interfaccia dichiarata per produrre la documentazione")
    @Test
    public void docNoDataFound() throws Exception {

        when(delegateMock.getAccountDetail(anyString()))
                .thenThrow(new NoDataFoundException("No data found"));

        MvcResult result =  mockMvc.perform(
                post(CONTEXT_PATH+"/accountDetailBasicResponse/{userCode}", "RGNLSN87H13D761R")
                        .contextPath(CONTEXT_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(this.documentHandler.document(
                        getResponseFieldsErrorSnippet()
                ))
                .andExpect(status().isNotFound()).andReturn();

        verify(delegateMock).getAccountDetail(anyString());
        verifyNoMoreInteractions(delegateMock);

        String content = result.getResponse().getContentAsString();
        log.debug("content [{}]", content);

        ClientErrorInformation response = mapper.readValue(content, new TypeReference<ClientErrorInformation>() {
        });

        assertEquals(404,response.getErrorCode());
    }






    @TestDescription(subject = "accountDetailController",
            description = "Effettua l'invocazione del controllers del servizio /accountDetailController/{user}",
            expectation = "Verifica che il repository trasformi i dati correttamente dal dto verso la response")
    @Test(expected = NoDataFoundException.class)
    public void validateRequestWithNoDataResult() throws Exception {

        try {
            controller.accountDetailExtendedResponse("RGNLSN87H13D761RW");
        } catch (NoDataFoundException e) {
            log.debug("ERROR thrown {} ", e.getMessage(), e);
            if (e.getMessage().toUpperCase().contains("NO DATA FOUND")) {
                throw e;
            }
        }
    }
}
