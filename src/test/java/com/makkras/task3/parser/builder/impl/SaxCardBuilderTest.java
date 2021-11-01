package com.makkras.task3.parser.builder.impl;

import com.makkras.task3.exception.InteractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SaxCardBuilderTest {
    private SaxCardBuilder saxCardBuilder;
    private static Logger logger = LogManager.getLogger();
    @BeforeMethod
    public void setUp() {
        try {
            saxCardBuilder = new SaxCardBuilder();
        } catch (InteractionException e) {
            logger.error(e);
        }
    }
    @Test
    public void testSaxParsingCorrectness(){
        try {
            saxCardBuilder.buildCardSet("sourcefile/pcard.xml");
            assertEquals(saxCardBuilder.getCards().size(),8);
        } catch (InteractionException e) {
            e.printStackTrace();
        }
    }
}