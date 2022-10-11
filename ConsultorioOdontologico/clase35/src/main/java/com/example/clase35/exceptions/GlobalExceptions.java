package com.example.clase35.exceptions;


import org.apache.log4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptions {

    private static  final Logger logger= Logger.getLogger(GlobalExceptions.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> manejoErrorResourceNotFound(ResourceNotFoundException rnfe, Exception ex){
        logger.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ATENCIÓN: ERROR "+
                rnfe.getMessage()+"\n"+rnfe.getStackTrace());
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> manejoErrorResourceNotFound(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atención: ERROR" + bre.getMessage() +"\n"+bre.getStackTrace());
    }


}
