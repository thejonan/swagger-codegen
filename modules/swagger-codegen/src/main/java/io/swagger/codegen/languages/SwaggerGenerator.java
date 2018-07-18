package io.swagger.codegen.languages;

import java.io.File;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.codegen.v3.CodegenConfig;
import io.swagger.codegen.v3.CodegenType;
import io.swagger.codegen.v3.DefaultCodegen;
import io.swagger.codegen.v3.SupportingFile;
import io.swagger.v3.core.util.Json;

public class SwaggerGenerator extends DefaultCodegen implements CodegenConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerGenerator.class);

    public SwaggerGenerator() {
        super();
        embeddedTemplateDir = templateDir = "swagger";
        outputFolder = "generated-code/swagger";

        supportingFiles.add(new SupportingFile("README.md", "", "README.md"));
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.DOCUMENTATION;
    }

    @Override
    public String getName() {
        return "swagger";
    }

    @Override
    public String getHelp() {
        return "Creates a static swagger.json file.";
    }

    @Override
    public void processOpenAPI(OpenAPI openAPI) {
        String swaggerString = Json.pretty(openAPI);

        try {
            String outputFile = outputFolder + File.separator + "swagger.json";
            FileUtils.writeStringToFile(new File(outputFile), swaggerString);
            LOGGER.debug("wrote file to " + outputFile);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public String escapeQuotationMark(String input) { 
        // just return the original string
        return input;
    } 

    @Override
    public String escapeUnsafeCharacters(String input) { 
        // just return the original string
        return input;
    }  
}
