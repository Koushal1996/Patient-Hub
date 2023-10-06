package com.kp.PatientHub.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {

    private String message;
    private int code;

    public SuccessResponse() {
    }

    public SuccessResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
