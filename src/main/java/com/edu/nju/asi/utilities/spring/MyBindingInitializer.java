package com.edu.nju.asi.utilities.spring;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/5/20.
 */
public class MyBindingInitializer implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                LocalDate thisDate = LocalDate.parse(text);
                setValue(thisDate);
            }
        });
    }
}
