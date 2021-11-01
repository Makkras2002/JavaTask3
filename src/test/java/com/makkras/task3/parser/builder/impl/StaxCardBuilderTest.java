package com.makkras.task3.parser.builder.impl;

import com.makkras.task3.exception.InteractionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StaxCardBuilderTest {
    private StaxCardBuilder staxCardBuilder;
    private static Logger logger = LogManager.getLogger();
    @BeforeMethod
    public void setUp() {
        staxCardBuilder = new StaxCardBuilder();
    }
    @Test(expectedExceptions = {InteractionException.class})
    public void testStaxParsingCorrectness() throws InteractionException {
        staxCardBuilder.buildCardSet("invalidPath.xml");
    }
}