package com.electronicTicket;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.FileOutputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Ignore
@AutoConfigureMockMvc
@ActiveProfiles("test")
class OpenAPIDocumentGeneratorIT {

    private static final String API_DOCS_PATH = "/v3/api-docs.yaml";
    Logger logger = LoggerFactory.getLogger(OpenAPIDocumentGeneratorIT.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Generate YAML file with OpenAPI definitions")
    void test_generateOpenAPIDefinitions() throws Exception {
        final MvcResult response = mockMvc.perform(get(API_DOCS_PATH))
                .andExpect(status().isOk())
                .andReturn();
        final String pathToFile = "src\\main\\resources\\apidocs\\openapi.yaml";
        final byte[] file = response.getResponse().getContentAsByteArray();
        File openAPIFile = new File(pathToFile);
        if (openAPIFile.createNewFile()) {
            logger.debug("OpenAPI file was created at: " + pathToFile);
        }
        try (final FileOutputStream fos = new FileOutputStream(pathToFile)) {
            fos.write(file);
        }
    }

}