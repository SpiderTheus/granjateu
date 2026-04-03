package com.spider.granjateu.controllers.excptions;

import java.net.URI;

import org.springframework.http.HttpStatus;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spider.granjateu.services.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler{

  @ExceptionHandler(NotFoundException.class)
  public ProblemDetail handleResourceNotFoundException(NotFoundException ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problemDetail.setTitle("Resource Not Found");
    problemDetail.setType(URI.create(request.getRequestURI()));
    problemDetail.setDetail(ex.getMessage());
    return problemDetail;
  }

}
