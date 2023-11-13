package ru.headhunter.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.HttpResource;
import ru.headhunter.api.model.ErrorResponse;
import ru.headhunter.api.service.VacancyService;

import java.net.http.HttpResponse;


@RestController
public class HeadHunterRestController {

    @Autowired
    private VacancyService vacancyService;

    @GetMapping("/load")
    public ResponseEntity<HttpStatus> load(){
        vacancyService.saveVacancy();
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> hadleException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse("Какая то ошибка");

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
