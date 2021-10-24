package com.makkras.task3.parser.builder.impl;

import com.makkras.task3.entity.CompactPostCard;
import com.makkras.task3.entity.CustomPostCard;
import com.makkras.task3.entity.ExtendedPostCard;
import com.makkras.task3.entity.enumsource.CardType;
import com.makkras.task3.entity.enumsource.CountryName;
import com.makkras.task3.entity.enumsource.ThemeType;
import com.makkras.task3.entity.enumsource.ValuableType;
import com.makkras.task3.exception.InteractionException;
import com.makkras.task3.parser.builder.CustomCardBuilder;
import com.makkras.task3.parser.util.CardTagEnum;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class StaxCardBuilder implements CustomCardBuilder {
    private Set<CustomPostCard> cards;
    private XMLInputFactory inputFactory;
    public StaxCardBuilder() {
        this.cards = new HashSet<>();
        inputFactory = XMLInputFactory.newInstance();
    }
    public Set<CustomPostCard> getCards() {
        return cards;
    }
    public void buildCardSet(String xmlPath) throws InteractionException{
        XMLStreamReader reader = null;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(xmlPath))){
            reader = inputFactory.createXMLStreamReader(inputStream);
            int vc =0;
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(CardTagEnum.LOCAL_COMPACT_POST_CARD.getValue())) {
                        CompactPostCard card = buildCompactPostCard(reader);
                        cards.add(card);
                    }else if(name.equals(CardTagEnum.LOCAL_EXTENDED_POST_CARD.getValue())){
                        ExtendedPostCard card = buildExtendedPostCard(reader);
                        cards.add(card);
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw  new InteractionException(e.getMessage());
        } catch (FileNotFoundException e) {
            throw  new InteractionException(e.getMessage());
        } catch (IOException e) {
            throw  new InteractionException(e.getMessage());
        }
    }
    private CompactPostCard buildCompactPostCard(XMLStreamReader reader) throws InteractionException{
        CompactPostCard card = new CompactPostCard();
        card.setId(reader.getAttributeValue(null,CardTagEnum.ID.getValue()));
        if(reader.getAttributeValue(null,CardTagEnum.WAS_SENT.getValue()) == null){
            card.setWasSent(true);
        }else {
            card.setWasSent(false);
        }
        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:{
                        name = reader.getLocalName();
                        switch (CardTagEnum.valueOf(name.toUpperCase().replaceAll("-","_"))) {
                            case THEME:{
                                card.setThemeType(ThemeType.valueOf(getXMLText(reader)));
                                break;
                            }
                            case YEAR:{
                                card.setYear(Year.parse(getXMLText(reader)));
                                break;
                            }
                            case VALUABLE:{
                                card.setValuableType(ValuableType.valueOf(getXMLText(reader)));
                                break;
                            }
                            case COMPACT_POST_CODE:{
                                card.setCompactPostCode(getXMLText(reader));
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT:{
                        name = reader.getLocalName();
                        if (CardTagEnum.LOCAL_COMPACT_POST_CARD.getValue().equals(name)) {
                            return card;
                        }
                        break;
                    }
                }
            }
        }
        catch (XMLStreamException e) {
            throw new InteractionException(e.getMessage());
        }
        return card;
    }
    private ExtendedPostCard buildExtendedPostCard(XMLStreamReader reader) throws InteractionException{
        ExtendedPostCard card = new ExtendedPostCard();
        card.setId(reader.getAttributeValue(null,CardTagEnum.ID.getValue()));
        if(reader.getAttributeValue(null,CardTagEnum.WAS_SENT.getValue()) == null){
            card.setWasSent(true);
        }else {
            card.setWasSent(false);
        }
        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:{
                        name = reader.getLocalName();
                        switch (CardTagEnum.valueOf(name.toUpperCase().replaceAll("-","_"))) {
                            case THEME:{
                                card.setThemeType(ThemeType.valueOf(getXMLText(reader)));
                                break;
                            }
                            case YEAR:{
                                card.setYear(Year.parse(getXMLText(reader)));
                                break;
                            }
                            case VALUABLE:{
                                card.setValuableType(ValuableType.valueOf(getXMLText(reader)));
                                break;
                            }
                            case CARD_TYPE:{
                                card.setCardType(CardType.valueOf(getXMLText(reader)));
                                break;
                            }
                            case COUNTRY:{
                                card.setCountryName(CountryName.valueOf(getXMLText(reader)));
                                break;
                            }
                            case AUTHOR:{
                                card.setAuthor(getXMLText(reader));
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT:{
                        name = reader.getLocalName();
                        if (CardTagEnum.LOCAL_EXTENDED_POST_CARD.getValue().equals(name)) {
                            return card;
                        }
                        break;
                    }
                }
            }
        }
        catch (XMLStreamException e) {
            throw new InteractionException(e.getMessage());
        }
        return card;
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
