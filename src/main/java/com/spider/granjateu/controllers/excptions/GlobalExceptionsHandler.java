package com.spider.granjateu.controllers.excptions;

import java.net.URI;

import org.springframework.http.HttpStatus;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spider.granjateu.services.exceptions.AttributeMandatoryException;
import com.spider.granjateu.services.exceptions.NotFoundException;
import com.spider.granjateu.services.exceptions.StatusInvalidException;

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

  @ExceptionHandler(StatusInvalidException.class)
  public ProblemDetail handleStatusInvalidException(StatusInvalidException ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setTitle("Invalid Status");
    problemDetail.setType(URI.create(request.getRequestURI()));
    problemDetail.setDetail(ex.getMessage());
    return problemDetail;
  }

  @ExceptionHandler(AttributeMandatoryException.class)
  public ProblemDetail handleAttributeMandatoryException(AttributeMandatoryException ex, HttpServletRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    problemDetail.setTitle("Attribute Mandatory");
    problemDetail.setType(URI.create(request.getRequestURI()));
    problemDetail.setDetail(ex.getMessage() + "Atributo obrigatório não pode ser nulo ou vazio.");
    return problemDetail; 
  }

  
}
