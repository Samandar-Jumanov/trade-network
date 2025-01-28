package com.trade.handler;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor


public class ExceptionResponse {

        private Integer businessErrorCode;
        private String businessErrorMessage;
        private String businessErrorDetail;
        private Set<String> validationErrors;
        private Map<String , String > errors;




}
