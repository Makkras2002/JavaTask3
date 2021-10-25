package com.makkras.task3.parser.builder.impl;

import com.makkras.task3.entity.CustomPostCard;
import com.makkras.task3.exception.InteractionException;
import com.makkras.task3.parser.builder.CustomCardBuilder;
import com.makkras.task3.parser.util.saxhandler.SaxHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import java.io.IOException;
import java.util.Set;

public class SaxCardBuilder implements CustomCardBuilder {
    private Set<CustomPostCard> cards;
    private XMLReader reader;
    private SaxHandler handler;
    public SaxCardBuilder() throws InteractionException{
        try {
            handler = new SaxHandler();
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            throw  new InteractionException(e.getMessage());
        }
    }
    public Set<CustomPostCard> getCards() {
        return cards;
    }
    public void buildCardSet(String xmlPath) throws InteractionException {
        try {
            reader.parse(xmlPath);
        } catch (SAXException e) {
            throw new InteractionException(e.getMessage());
        } catch (IOException e) {
           throw  new InteractionException(e.getMessage());
        }
        cards = handler.getParsedSet();
    }
}
