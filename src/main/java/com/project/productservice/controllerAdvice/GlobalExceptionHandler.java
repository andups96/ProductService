package com.project.productservice.controllerAdvice;

import com.project.productservice.dto.ExceptionDto;
import com.project.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDto> handleArithmeticException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setExceptionMessage("Arithmetic Exception");
        exceptionDto.setExceptionSolution("Please check the arithmetic operation");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException() {
        return new ResponseEntity<>("Array Index Out Of Bounds Exception happened. Please check the array index", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setExceptionMessage(e.getMessage());
        exceptionDto.setExceptionSolution("Please check the product id");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

}
