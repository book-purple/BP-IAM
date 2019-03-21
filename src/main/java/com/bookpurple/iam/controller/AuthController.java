package com.bookpurple.iam.controller;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.SignUpRequestBo;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.dto.AuthRequestDto;
import com.bookpurple.iam.dto.SignUpRequestDto;
import com.bookpurple.iam.dto.SignUpResponseDto;
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
public class AuthController {

    @Autowired
    private ISignupService signupService;

    @Autowired
    private IRequestMapper requestMapper;

    @PostMapping(value = "/otp/generate", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity generateOtp(AuthRequestDto authRequestDto) {
        AuthRequestBo authRequestBo = requestMapper.authRequestDtoToBo(authRequestDto);
        signupService.generateOtp(authRequestBo);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<SignUpResponseDto> signUpUser(AuthRequestDto authRequestDto,
                                                        SignUpRequestDto signUpRequestDto) {
        AuthRequestBo authRequestBo = requestMapper.authRequestDtoToBo(authRequestDto);
        SignUpRequestBo signUpRequestBo = requestMapper.signUpRequestDtoToBo(signUpRequestDto);
        SignUpResponseDto signUpResponseDto = requestMapper
                .signUpResponseBoToDto(signupService
                        .doUserSignUp(authRequestBo, signUpRequestBo));
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
    }
}
