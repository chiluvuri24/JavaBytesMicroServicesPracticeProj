package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardsDTO;
import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.exception.ResourceNotFoundException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsService implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public boolean updateCards(CardsDTO cardsDTO) {
        boolean result = false;
        if (cardsDTO != null) {

            Cards cards = cardsRepository.findByCardNumber(cardsDTO.getCardNumber()).orElseThrow(() ->
                    new ResourceNotFoundException("Cards", "CardNumber", cardsDTO.getCardNumber()
                    ));


            cards = CardsMapper.mapToCards(cardsDTO);
            result = true;
        }

        return result;
    }

    @Override
    public void createCards(String mobileNumber) {

        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);

        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists with mobile number " + mobileNumber);
        }

        cardsRepository.save(createNewCard(mobileNumber));


    }

    @Override
    public CardsDTO fetchCardsByMobileNumber(String mobileNumber) {

        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);

        if (optionalCards.isEmpty()) {
            throw new ResourceNotFoundException("Cards", "MobileNumber", mobileNumber.toString());
        }

        Cards cards = optionalCards.get();

        CardsDTO cardsDTO = CardsMapper.mapToCardsDTO(cards);

        return cardsDTO;
    }

    @Override
    public boolean deleteCards(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        boolean result = false;
        if (optionalCards.isEmpty()) {
            throw new ResourceNotFoundException("Cards", "MobileNumber", mobileNumber.toString());
        }
        Cards cards = optionalCards.get();
        cardsRepository.deleteById(cards.getCardId());
        result = true;
        return result;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCard;
    }

}
