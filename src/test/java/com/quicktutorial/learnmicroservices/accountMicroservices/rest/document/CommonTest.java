package com.quicktutorial.learnmicroservices.accountMicroservices.rest.document;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.springframework.restdocs.JUnitRestDocumentation;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class CommonTest {

    static {
        log.info("Init");
        File currDir = new File(".").getAbsoluteFile();
        log.debug("Current directory is [{}]", currDir.toString());

        System.setProperty("conf.dir", currDir.toString() + "/target/config");
    }

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");


    public String readFile(final URI file) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }

    public String readFile(final String fileName) throws IOException, URISyntaxException {
        return new String(Files.readAllBytes(Paths.get(getClass().getResource(fileName).toURI())));
    }

}