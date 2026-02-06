package com.nagendra.springsection1.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    @GetMapping("/myCard")
    public String getCardDetails(){

        return "here are the details of card from DB";
    }
}

