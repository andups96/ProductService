package com.project.productservice.dto;

import lombok.Data;

@Data
public class ExceptionDto {
    Long exceptionId;
    String exceptionMessage;
    String exceptionSolution;

}
