package com.makkras.task3.main;
import com.makkras.task3.exception.InteractionException;
import com.makkras.task3.parser.builder.impl.DomCardBuilder;
import com.makkras.task3.parser.builder.impl.StaxCardBuilder;
import com.makkras.task3.validator.impl.XmlDocValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static Logger logger = LogManager.getLogger();
    public static void main(String[] args) {
        try {
            if(XmlDocValidator.getInstance().validateXmlAccordingToScheme("sourcefile/pcard.xml","sourcefile/schema.xsd")){
                try {
                    DomCardBuilder builder = new DomCardBuilder();
                    builder.buildCardSet("sourcefile/pcard.xml");
                    logger.info(builder.getCardSet().toString());
                    StaxCardBuilder staxCardBuilder =new StaxCardBuilder();
                    staxCardBuilder.buildCardSet("sourcefile/pcard.xml");
                    logger.info(staxCardBuilder.getCards().toString());
                } catch (InteractionException e) {
                    logger.error(e.getMessage());
                }
            }else {
                logger.error("Invalid XML format.");
            }
        } catch (InteractionException e) {
            logger.error(e.getMessage());
        }
    }
}
