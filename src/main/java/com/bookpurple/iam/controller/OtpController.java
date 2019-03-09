package com.bookpurple.iam.controller;

import com.bookpurple.iam.dto.OtpRequestDto;
import com.bookpurple.iam.service.ISignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/iam")
public class OtpController {

    @Autowired
    private ISignupService signupService;

    @PostMapping(value = "/otp/generate", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity generateOtp(OtpRequestDto otpRequestDto) {
        String otp = signupService.generateOtp();

        return new ResponseEntity(HttpStatus.OK);
    }
}
