package com.bookpurple.iam.controller;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.DeviceTokenRequestBo;
import com.bookpurple.iam.bo.SignUpRequestBo;
import com.bookpurple.iam.constant.Constants;
import com.bookpurple.iam.converter.IRequestMapper;
import com.bookpurple.iam.dto.AuthRequestDto;
import com.bookpurple.iam.dto.DeviceTokenRequestDto;
import com.bookpurple.iam.dto.SignUpRequestDto;
import com.bookpurple.iam.dto.SignUpResponseDto;
import com.bookpurple.iam.service.ISignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/iam/v1")
public class AuthController {

    @Autowired
    private ISignupService signupService;

    @Autowired
    private IRequestMapper requestMapper;

    @PostMapping(value = "/otp/generate", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity generateOtp(@RequestBody AuthRequestDto authRequestDto) {
        AuthRequestBo authRequestBo = requestMapper.authRequestDtoToBo(authRequestDto);
        signupService.generateOtp(authRequestBo);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<SignUpResponseDto> signUpUser(@RequestBody AuthRequestDto authRequestDto, HttpServletRequest servletRequest) {
        AuthRequestBo authRequestBo = requestMapper.authRequestDtoToBo(authRequestDto);
        SignUpRequestDto signUpRequestDto = buildSignUpRequestDto(servletRequest);
        SignUpRequestBo signUpRequestBo = requestMapper.signUpRequestDtoToBo(signUpRequestDto);
        SignUpResponseDto signUpResponseDto = requestMapper
                .signUpResponseBoToDto(signupService
                        .doUserSignUp(authRequestBo, signUpRequestBo));
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
    }

    private SignUpRequestDto buildSignUpRequestDto(HttpServletRequest servletRequest) {
        return SignUpRequestDto.builder()
                .appVersion(servletRequest.getHeader(Constants.RequestHeaders.APP_VERSION))
                .appVersionCode(servletRequest.getHeader(Constants.RequestHeaders.APP_VERSION_CODE))
                .deviceName(servletRequest.getHeader(Constants.RequestHeaders.device_name))
                .deviceType(servletRequest.getHeader(Constants.RequestHeaders.device_type))
                .osVersion(servletRequest.getHeader(Constants.RequestHeaders.os_version))
                .build();
    }

    @PostMapping(value = "/device", consumes = {APPLICATION_JSON_VALUE}, produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity saveUserDeviceToken(@RequestBody DeviceTokenRequestDto deviceTokenRequestDto) {
        DeviceTokenRequestBo deviceTokenRequestBo = requestMapper.deviceTokenRequestDtoToBo(deviceTokenRequestDto);
        signupService.handleDeviceTokenRegistration(deviceTokenRequestBo);
        return new ResponseEntity(HttpStatus.OK);
    }
}