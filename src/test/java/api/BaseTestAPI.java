package api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import generated.openApi.api.petStore.PetApi;
import groovy.json.StringEscapeUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import ui.pages.BaseTest;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static pages.BasePO.getBaseUrl;

import static com.fasterxml.jackson.core.JsonToken.*;
import static com.fasterxml.jackson.core.JsonToken.VALUE_TRUE;

public abstract class BaseTestAPI extends BaseTest {
    protected static final String INCORRECT_STATUS_CODE_MESSAGE_200 = "The response code is not 200!";

    @BeforeTest
    public void beforeTest() {
        System.out.println("Base URL: " + getBaseUrl());
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory
                ((cls, charset) -> getCommonObjectMapper()));
    }

    @AfterTest
    public void afterTest() {
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        StringBuilder toString = new StringBuilder();

        List<String> consoleOutput = Reporter.getOutput(result);
        for (String list : consoleOutput) {
            list = list + " \n";
            toString.append(list);
        }

        Allure.addAttachment("Output in console", StringEscapeUtils.unescapeJava(toString.toString()));
    }

    public static class CustomStringDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
            if (Set.of(VALUE_NUMBER_INT, VALUE_NUMBER_FLOAT, VALUE_FALSE, VALUE_TRUE).contains(jsonParser.getCurrentToken())) {
                throw new IllegalArgumentException();
            }
            return jsonParser.getValueAsString();
        }
    }

    public static ObjectMapper getCommonObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new CustomStringDeserializer());

        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
                .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(module)
                .registerModule(new JsonNullableModule());
    }

    //base settings HTTP requests
    public static Supplier<RequestSpecBuilder> getRequestSpecBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(getBaseUrl());
        //requestSpecBuilder.setProxy(8888); //for debugging via Fiddler
        requestSpecBuilder.addFilter(new AllureRestAssured());
        return () -> requestSpecBuilder;
    }

    public Function<Response, Response> getHandler() {
        return response -> response;
    }

    protected PetApi createPetApi() {
        return PetApi.pet(getRequestSpecBuilder());
    }
}
