package com.myapp.card.service;

import com.myapp.card.model.CardDetails;
import com.myapp.card.util.Luhn;
import com.myapp.card.util.exception.CreditCardValidationException;
import com.myapp.card.util.exception.DataNotFoundException;
import com.myapp.card.util.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Biju Pillai
 */

@Service
public class CreditCardService {
    List<CardDetails> cardDetailsList = new ArrayList<CardDetails>();
    public void add(CardDetails cardDetails){
        if(cardDetails.getCardNumber().length()<16){
            throw new CreditCardValidationException("Please use valid credit card");
        }else if(!Luhn.Check(cardDetails.getCardNumber())){
            throw new CreditCardValidationException("Please use valid credit card");
        }else if(cardDetailsList.contains(cardDetails)){
            throw new ResourceNotFoundException("This card already registered!");
        }
        cardDetailsList.add(cardDetails);

    }
    public List<CardDetails> getAll(){
        if(cardDetailsList.isEmpty()){
            throw new DataNotFoundException("Data not found, please add card details ");
        }
        return cardDetailsList;
    }
}
