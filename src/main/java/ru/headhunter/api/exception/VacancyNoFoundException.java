package ru.headhunter.api.exception;

public class VacancyNoFoundException extends RuntimeException {

    public VacancyNoFoundException(String msg) {
        super(msg);
    }
}
