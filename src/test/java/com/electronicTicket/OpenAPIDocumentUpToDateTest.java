package com.electronicTicket;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.nio.file.Files;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OpenAPIDocumentUpToDateTest {

    private static final String API_DOCS_PATH = "/v3/api-docs.yaml";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Check if OpenAPI spec doc is up to date")
    void test_generateOpenAPIDefinitions() throws Exception {
        final MvcResult response = mockMvc.perform(get(API_DOCS_PATH))
                .andExpect(status().isOk())
                .andReturn();
        final String pathToFile = "src\\main\\resources\\apidocs\\openapi.yaml";
        final byte[] openApiSpec = response.getResponse().getContentAsByteArray();
        File openAPIFile = new File(pathToFile);

        final byte[] fileContent = Files.readAllBytes(openAPIFile.toPath());

        Assertions.assertArrayEquals(openApiSpec, fileContent, "OpenAPI yaml should be up to date");
    }

}