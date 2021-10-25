package com.makkras.task3.parser.util.saxhandler;

import com.makkras.task3.entity.CompactPostCard;
import com.makkras.task3.entity.CustomPostCard;
import com.makkras.task3.entity.ExtendedPostCard;
import com.makkras.task3.entity.enumsource.CardType;
import com.makkras.task3.entity.enumsource.CountryName;
import com.makkras.task3.entity.enumsource.ThemeType;
import com.makkras.task3.entity.enumsource.ValuableType;
import com.makkras.task3.parser.util.CardTagEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class SaxHandler extends DefaultHandler {
    private Set<CustomPostCard> parsedSet;
    private CompactPostCard currentCpc = null;
    private ExtendedPostCard currentEpc = null;
    private String currentInnerTag =null;
    private int compactOrExtended = 0;
    private static Logger logger = LogManager.getLogger();
    public SaxHandler(){
        parsedSet = new HashSet<>();
    }
    public Set<CustomPostCard> getParsedSet(){
        return parsedSet;
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes){
        if (CardTagEnum.LOCAL_COMPACT_POST_CARD.getValue().equals(localName)) {
            currentCpc = new CompactPostCard();
            currentCpc.setId(attributes.getValue(CardTagEnum.ID.getValue()));
            if(attributes.getValue(CardTagEnum.WAS_SENT.getValue()) == null){
                currentCpc.setWasSent(true);
            }else {
                currentCpc.setWasSent(false);
            }
            compactOrExtended = 1;
        } else if(CardTagEnum.LOCAL_EXTENDED_POST_CARD.getValue().equals(localName)){
            currentEpc = new ExtendedPostCard();
            currentEpc.setId(attributes.getValue(CardTagEnum.ID.getValue()));
            if(attributes.getValue(CardTagEnum.WAS_SENT.getValue()) == null){
                currentEpc.setWasSent(true);
            }else {
                currentEpc.setWasSent(false);
            }
            compactOrExtended = 2;
        }else {
           currentInnerTag = localName.toUpperCase().replaceAll("-","_");
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if(CardTagEnum.LOCAL_COMPACT_POST_CARD.getValue().equals(localName) && compactOrExtended ==1){
            parsedSet.add(currentCpc);
            compactOrExtended =0;
        }else if(CardTagEnum.LOCAL_EXTENDED_POST_CARD.getValue().equals(localName) && compactOrExtended ==2){
            parsedSet.add(currentEpc);
            compactOrExtended =0;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String str = new String(ch,start,length).trim();
        if(currentInnerTag!= null){
            if(compactOrExtended == 1){
                switch (CardTagEnum.valueOf(currentInnerTag)) {
                    case THEME:{
                        currentCpc.setThemeType(ThemeType.valueOf(str));
                        break;
                    }
                    case YEAR:{
                        currentCpc.setYear(Year.parse(str));
                        break;
                    }
                    case VALUABLE:{
                        currentCpc.setValuableType(ValuableType.valueOf(str));
                        break;
                    }
                    case COMPACT_POST_CODE:{
                        currentCpc.setCompactPostCode(str);
                        break;
                    }
                    default:{
                        logger.error("Unknown tag found in xml.");
                        break;
                    }
                }
            }else if(compactOrExtended == 2){
                switch (CardTagEnum.valueOf(currentInnerTag)) {
                    case THEME:{
                        currentEpc.setThemeType(ThemeType.valueOf(str));
                        break;
                    }
                    case YEAR:{
                        currentEpc.setYear(Year.parse(str));
                        break;
                    }
                    case VALUABLE:{
                        currentEpc.setValuableType(ValuableType.valueOf(str));
                        break;
                    }
                    case CARD_TYPE:{
                        currentEpc.setCardType(CardType.valueOf(str));
                        break;
                    }
                    case COUNTRY:{
                        currentEpc.setCountryName(CountryName.valueOf(str));
                        break;
                    }
                    case AUTHOR:{
                        currentEpc.setAuthor(str);
                        break;
                    }
                    default:{
                        logger.error("Unknown tag found in xml.");
                        break;
                    }
                }
            }
        }
        currentInnerTag =null;
    }
}
