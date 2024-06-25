package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.CardsDTO;

public interface ICardsService {

    boolean updateCards(CardsDTO cardsDTO);

    void createCards(String mobileNumber);


    CardsDTO fetchCardsByMobileNumber(String mobileNumber);

    boolean deleteCards(String mobileNumber);
}
