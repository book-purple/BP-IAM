package com.bookpurple.iam.controller;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.dto.AuthRequestDto;
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

    @Autowired
    private AuthRequestMapper authRequestMapper;

    @PostMapping(value = "/otp/generate", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity generateOtp(AuthRequestDto authRequestDto) {
        AuthRequestBo authRequestBo = authRequestMapper.authRequestDtoToBo(authRequestDto);
        signupService.generateOtp(authRequestBo);

        return new ResponseEntity(HttpStatus.OK);
    }
}
