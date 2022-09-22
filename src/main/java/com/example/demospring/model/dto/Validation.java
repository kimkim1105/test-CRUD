package com.example.demospring.model.dto;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Validation {
    private static Pattern pattern;
    private Matcher matcher;
    private static final String PHONE_REGEX = "(0[3|5|7|8|9])+([0-9]{8})\\b";
    private static final String INSTOCK_REGEX = "^[0-9]+$";
    public Validation(){
//        pattern = Pattern.compile(PHONE_REGEX);
    }
    public boolean validate(String regex){
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
    public boolean validateInStock(String regex){
        Pattern pattern = Pattern.compile(INSTOCK_REGEX);
        matcher = pattern.matcher(regex);
        return matcher.matches();
    }
}
