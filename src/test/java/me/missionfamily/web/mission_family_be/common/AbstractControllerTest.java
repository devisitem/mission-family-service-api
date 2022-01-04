package me.missionfamily.web.mission_family_be.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.missionfamily.web.mission_family_be.business.account.controller.AccountController;
import me.missionfamily.web.mission_family_be.common.exception.MissionStatus;
import me.missionfamily.web.mission_family_be.common.exception.ServiceException;
import me.missionfamily.web.mission_family_be.common.util.PropertyUtils;
import me.missionfamily.web.mission_family_be.common.util.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.restdocs.RestDocsAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.headers.RequestHeadersSnippet;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsAutoConfiguration.class)
@WebMvcTest(value = AccountController.class)
public abstract class AbstractControllerTest {

    private MockMvc mockMvc;

    private RestDocumentationResultHandler handler;
    private RequestHeadersSnippet headerSnippets;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider docContext) {
        this.handler = document("{class-name}/{method-name}",
                preprocessRequest(modifyUris().port(8364),prettyPrint()),
                preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(this.handler)
                .addFilter(new CharacterEncodingFilter(PropertyUtils.UTF_8, true))
                .apply(documentationConfiguration(docContext).snippets().withEncoding(PropertyUtils.UTF_8))
                .build();

        this.headerSnippets = requestHeaders(
                headerWithName("MISSION_APP_TOKEN")
                        .description("인증서버에서 부여받은 기기 고유 앱 토큰").attributes(Attr.type(String.class))
        );

    }

    protected RestDocumentationResultHandler documentation(RequestFieldsSnippet request, ResponseFieldsSnippet response) {
        return this.handler.document(headerSnippets, request, response);
    }


    protected ResultActions perform(HttpMethod method, String uri, Object request) throws Throwable {
        return this.mockMvc.perform(provideAppropriateMethod(uri, method)
                .headers(defaultHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)));

    }

    protected void settingPreparedData(Supplier<ServiceException> tryExecute, Runnable catchExecute , MissionStatus status) throws ServiceException {
        try {
            ServiceException thrown = tryExecute.get();
            if( ! Utils.isNull(thrown))
                throw thrown;
        } catch (ServiceException e) {
            if(e.getResultCode() == status.getCode())
                catchExecute.run();
            else
                throw new ServiceException(MissionStatus.TEST_DATA_CHANGED);
        }
    }

    protected FieldDescriptor field(String name, String description, Class<?> clazz) {
        return field(name, description, clazz, false);
    }

    private HttpHeaders defaultHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("MISSION_APP_TOKEN", "imjks1SA1mcdSDm1lwsDScds213dsa");

        return httpHeaders;
    }

    protected FieldDescriptor field(String name, String description, Class<?> clazz, boolean optional) {
        FieldDescriptor descriptor = fieldWithPath(name)
                .description(description).type(clazz.getSimpleName());

        if(optional)
            descriptor.optional();

        return descriptor;
    }

    private static class Attr {

        private static final String TYPE = "TYPE";

        public static Attributes.Attribute type(Class<?> clazz) {
            return new Attributes.Attribute(TYPE, clazz.getSimpleName());
        }
    }

    private MockHttpServletRequestBuilder provideAppropriateMethod(String uri, HttpMethod method) throws Throwable {

        MockHttpServletRequestBuilder performMethod;

        switch (method) {
            case GET:
                performMethod = get(uri);
                break;
            case POST:
                performMethod = post(uri);
                break;
            case PUT:
                performMethod = put(uri);
                break;
            case DELETE:
                performMethod = delete(uri);
                break;
            case PATCH:
                performMethod = patch(uri);
                break;
            default:
                throw new ServiceException(MissionStatus.NON_DECLARED_METHOD);
        }

        return performMethod;
    }
}
