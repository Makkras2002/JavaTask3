package com.makkras.task3.validator;

import com.makkras.task3.exception.InteractionException;

public interface CustomXmlValidator {
    boolean validateXmlAccordingToScheme(String xmlPath,String schemePath) throws InteractionException;
}
