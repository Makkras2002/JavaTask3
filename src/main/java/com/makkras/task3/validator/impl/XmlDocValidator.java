package com.makkras.task3.validator.impl;

import com.makkras.task3.exception.InteractionException;
import com.makkras.task3.validator.CustomXmlValidator;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlDocValidator implements CustomXmlValidator {
    private static XmlDocValidator instance;
    private XmlDocValidator(){
    }
    public static XmlDocValidator getInstance(){
        if(instance ==null){
            instance = new XmlDocValidator();
        }
        return instance;
    }
    public boolean validateXmlAccordingToScheme(String xmlPath,String schemePath) throws InteractionException {
        File schemaFile = new File(schemePath);
        Source xmlFile = new StreamSource(new File(xmlPath));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        boolean isValid = true;
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
        } catch (SAXException e) {
            isValid = false;
        } catch (IOException e) {
            throw new InteractionException(e.getMessage());
        }
        return isValid;
    }

}
