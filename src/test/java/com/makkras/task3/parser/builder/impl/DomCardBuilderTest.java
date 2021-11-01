package com.makkras.task3.parser.builder.impl;

import com.makkras.task3.exception.InteractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class DomCardBuilderTest {
    private  DomCardBuilder domCardBuilder;
    private static Logger logger = LogManager.getLogger();
    @BeforeMethod
    public void setUp(){
        try {
            domCardBuilder = new DomCardBuilder();
        } catch (InteractionException e) {
            logger.error(e);
        }
    }
    @Test
    public void testDomParsingCorrectness(){
        try {
            domCardBuilder.buildCardSet("sourcefile/pcard.xml");
            assertEquals(domCardBuilder.getCardSet().size(),8);
        } catch (InteractionException e) {
            logger.error(e);
        }
    }
}