package com.makkras.task3.parser.builder;
import com.makkras.task3.exception.InteractionException;

public interface CustomCardBuilder {
    void buildCardSet(String xmlPath) throws InteractionException;
}
