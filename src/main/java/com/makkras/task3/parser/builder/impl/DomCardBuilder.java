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
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class DomCardBuilder implements CustomCardBuilder {
    private Set<CustomPostCard> cardSet;
    private DocumentBuilder documentBuilder;
    public DomCardBuilder() throws InteractionException{
        this.cardSet = new HashSet<CustomPostCard>();
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw  new InteractionException(e.getMessage());
        }
    }
    public Set<CustomPostCard> getCardSet() {
        return cardSet;
    }
    public void buildCardSet(String xmlPath) throws InteractionException{
        Document document;
        try {
            document = documentBuilder.parse(xmlPath);
            Node root = document.getDocumentElement();
            NodeList cards = root.getChildNodes();
            for (int i = 0; i < cards.getLength(); i++) {
                Node card = cards.item(i);
                if (card.getNodeType() != Node.TEXT_NODE) {
                    if(card.getNodeName().equals(CardTagEnum.COMPACT_POST_CARD.getValue())){
                        CompactPostCard compactPostCard = buildCompactPostCard(card);
                        cardSet.add(compactPostCard);
                    }else {
                        ExtendedPostCard extendedPostCard = buildExtendedPostCard(card);
                        cardSet.add(extendedPostCard);
                    }
                }
            }
        } catch (IOException e) {
            throw new InteractionException(e.getMessage());
        } catch (SAXException e) {
            throw new InteractionException(e.getMessage());
        }
    }
    private CompactPostCard buildCompactPostCard(Node card) {
        CompactPostCard resCard = new CompactPostCard();
        String id;
        Boolean wasSent;
        wasSent = true;
        if(card.getAttributes().getNamedItem(CardTagEnum.WAS_SENT.getValue()) != null){
            wasSent = false;
        }
        resCard.setId(card.getAttributes().getNamedItem(CardTagEnum.ID.getValue()).getNodeValue());
        resCard.setWasSent(wasSent);
        resCard.setThemeType(ThemeType.valueOf(getNodeTextContent(card, CardTagEnum.THEME.getValue())));
        resCard.setYear(Year.parse(getNodeTextContent(card,CardTagEnum.YEAR.getValue())));
        resCard.setValuableType(ValuableType.valueOf(getNodeTextContent(card,CardTagEnum.VALUABLE.getValue())));
        resCard.setCompactPostCode(getNodeTextContent(card,CardTagEnum.COMPACT_POST_CODE.getValue()));
        return resCard;
    }
    private ExtendedPostCard buildExtendedPostCard(Node card) {
        ExtendedPostCard resCard = new ExtendedPostCard();
        Boolean wasSent;
        wasSent = true;
        if(card.getAttributes().getNamedItem(CardTagEnum.WAS_SENT.getValue()) != null){
            wasSent = false;
        }
        resCard.setId(card.getAttributes().getNamedItem(CardTagEnum.ID.getValue()).getNodeValue());
        resCard.setWasSent(wasSent);
        resCard.setThemeType(ThemeType.valueOf(getNodeTextContent(card,CardTagEnum.THEME.getValue())));
        resCard.setYear(Year.parse(getNodeTextContent(card,CardTagEnum.YEAR.getValue())));
        resCard.setValuableType(ValuableType.valueOf(getNodeTextContent(card,CardTagEnum.VALUABLE.getValue())));
        resCard.setCardType(CardType.valueOf(getNodeTextContent(card,CardTagEnum.CARD_TYPE.getValue())));
        resCard.setCountryName(CountryName.valueOf(getNodeTextContent(card,CardTagEnum.COUNTRY.getValue())));
        resCard.setAuthor(getNodeTextContent(card,CardTagEnum.AUTHOR.getValue()));
        return resCard;
    }
    private String getNodeTextContent(Node parentNode,String tagName) {
        NodeList cardContent = parentNode.getChildNodes();
        int j = 0;
        String text = "error";
        while (j < cardContent.getLength()) {
            Node cardElem = cardContent.item(j);
            if(cardElem.getNodeName().equals(tagName)){
                text = cardElem.getChildNodes().item(0).getTextContent();
                break;
            }
            j++;
        }
        return text;
    }
}
