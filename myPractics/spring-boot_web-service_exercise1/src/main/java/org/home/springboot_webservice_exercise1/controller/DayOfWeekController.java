package org.home.springboot_webservice_exercise1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

@RestController
public class DayOfWeekController {

    @GetMapping("/current-day")
    public ResponseEntity<String> getCurrenDay(@RequestParam(defaultValue = "en") String lang){
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String dayName = getDayName(dayOfWeek, lang);

        return new ResponseEntity<>(dayName, HttpStatus.OK);
    }

    private String getDayName(DayOfWeek dayOfWeek, String lang){
        Locale locale = new Locale(lang);
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        return messages.getString(dayOfWeek.name());
    }

}
