package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.CardsDTO;
import com.eazybytes.cards.entity.Cards;

public class CardsMapper {

    public static Cards mapToCards(CardsDTO cardsDTO){
        Cards cards = new Cards();
        cards.setCardNumber(cardsDTO.getCardNumber());
        cards.setCardType(cardsDTO.getCardType());
        cards.setAmountUsed(cardsDTO.getAmountUsed());
        cards.setMobileNumber(cardsDTO.getMobileNumber());
        cards.setAvailableAmount(cardsDTO.getAvailableAmount());
        cards.setTotalLimit(cardsDTO.getTotalLimit());
        return cards;
    }

    public static CardsDTO mapToCardsDTO(Cards cards){
        CardsDTO cardsDTO = new CardsDTO();
        cardsDTO.setCardNumber(cards.getCardNumber());
        cardsDTO.setCardType(cards.getCardType());
        cardsDTO.setAmountUsed(cards.getAmountUsed());
        cardsDTO.setAvailableAmount(cards.getAvailableAmount());
        cardsDTO.setMobileNumber(cards.getMobileNumber());
        cardsDTO.setTotalLimit(cards.getTotalLimit());
        return cardsDTO;
    }


}
