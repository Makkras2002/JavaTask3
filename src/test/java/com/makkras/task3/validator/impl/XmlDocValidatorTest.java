package com.makkras.task3.validator.impl;

import com.makkras.task3.exception.InteractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XmlDocValidatorTest {
    private static Logger logger = LogManager.getLogger();
    @Test
    public void testValidationCorrectness(){
        try {
            assertTrue(XmlDocValidator.getInstance().validateXmlAccordingToScheme("sourcefile/pcard.xml","sourcefile/schema.xsd"));
        } catch (InteractionException e) {
            logger.error(e);
        }
    }

}