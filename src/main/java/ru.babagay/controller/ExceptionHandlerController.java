package ru.babagay.controller;

import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.babagay.config.AppException;

@Controller
public class ExceptionHandlerController {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(AppException.class)
    public @ResponseBody
    String handleException(AppException e) {
        LOG.error("AppError: " + e.getMessage(), e);
        return "AppError: " + e.getMessage();
    }
}