package com.quicktutorial.learnmicroservices.accountMicroservices.rest.document;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource(value = {"classpath:doc.properties"})
public abstract class CommonDocumentRestTest extends CommonTest {

    public static final String CONTEXT_PATH = "/learnmicroservices/services";
    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected Environment env;

    protected MockMvc mockMvc;

    protected RestDocumentationResultHandler documentHandler;

    @Autowired
    protected ObjectMapper mapper;


    @Before
    public void setUp() throws Exception{

        documentHandler = document("{class-name}-{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );


        mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation).snippets()
                        .and()
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8094))

                .alwaysDo(this.documentHandler)
                .build();

        init();
    }

    protected MvcResult performPostOk(String url, Object requestBody) throws Exception {
        return performPostOk(url, requestBody, null);
    }

    protected MvcResult performPostOk(String url, Object requestBody, String contexPath) throws Exception {
        if (contexPath == null) {
            contexPath = CONTEXT_PATH;
        }
        return this.mockMvc.perform(post(contexPath+url)
                .contextPath(contexPath).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(this.mapper.writeValueAsString(requestBody))
        )
                .andDo(documentFields())
                .andExpect(status().isOk()).andReturn();
    }


    protected RestDocumentationResultHandler documentFields() {
        return document("{class-name}-{method-name}",
                getRequestFieldsSnippet(),
                getBasicResponseFieldsSnippet()
        );

    }

    protected abstract RequestFieldsSnippet getRequestFieldsSnippet();
    protected abstract ResponseFieldsSnippet getBasicResponseFieldsSnippet();

    protected Snippet getResponseFieldsErrorSnippet() {
        return responseFields(
                fieldWithPath("errorCode").description(env.getProperty("rest.error.code.value.field.description"))
                        .attributes(key("pattern_domain").value("")),
                fieldWithPath("errorDescription").description(env.getProperty("rest.error.description.value.field.description"))
                        .attributes(key("pattern_domain").value(""))
        );
    }

    protected Snippet getExtendedResponseFieldsSnippet() {
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
                        ),
                fieldWithPath("lastUpdates")
                        .description(env.getProperty("rest.learnmicroservice.lastUpdates.field.description")).optional()
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value("")
                        ),
                fieldWithPath("lastUpdates.source")
                        .description(env.getProperty("rest.learnmicroservice.lastUpdates.source.field.description"))
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value("")
                        ),
                fieldWithPath("lastUpdates.timestamp")
                        .description(env.getProperty("rest.learnmicroservice.lastUpdates.timestamp.field.description"))
                        .attributes(
                                key("mandatory").value(env.getProperty("rest.mandatory.no")),
                                key("pattern_domain").value(env.getProperty("rest.timestamp.pattern"))
                        )
        );
    }

    protected abstract void init() throws Exception;

}
