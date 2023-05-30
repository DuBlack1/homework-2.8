package com.example.homework28.Service;

import com.example.homework28.Exception.IncorrectFirstNameException;
import com.example.homework28.Exception.IncorrectLastNameException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service
public class ValidatorService {
    public String validateFirstName(String firstName){
        if(!StringUtils.isAlpha(firstName)) throw new IncorrectFirstNameException();
        return StringUtils.capitalize(firstName.toLowerCase());
    }

    public String validateLastName(String lastName){
        if(!StringUtils.isAlpha( lastName)) throw new IncorrectLastNameException();
        return StringUtils.capitalize(lastName.toLowerCase());
    }
}
