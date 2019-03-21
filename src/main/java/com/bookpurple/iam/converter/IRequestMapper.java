package com.bookpurple.iam.converter;

import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.dto.AuthRequestDto;
import com.bookpurple.iam.dto.SignUpRequestDto;
import com.bookpurple.iam.dto.SignUpResponseDto;
import com.bookpurple.iam.entity.TempAuthEntity;
import com.bookpurple.iam.entity.UserAccessCodeEntity;
import com.bookpurple.iam.entity.UserDeviceEntity;
import com.bookpurple.iam.entity.UserEntity;
import org.mapstruct.Mapper;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Mapper(componentModel = "spring")
public interface IRequestMapper {

    AuthRequestBo authRequestDtoToBo(AuthRequestDto authRequestDto);

    AuthRequestDto authRequestBoToDto(AuthRequestBo authRequestBo);

    TempAuthBo tempAuthEntityToBo(TempAuthEntity tempAuthEntity);

    TempAuthEntity tempAuthBoToEntity(TempAuthBo tempAuthBo);

    SignUpRequestBo signUpRequestDtoToBo(SignUpRequestDto signUpRequestDto);

    SignUpResponseDto signUpResponseBoToDto(SignUpResponseBo signUpResponseBo);

    UserBo userEntityToBo(UserEntity userEntity);

    UserEntity userBoToEntity(UserBo userBo);

    UserDeviceEntity userDeviceBoToEntity(UserDeviceBo userDeviceBo);

    UserDeviceBo userDeviceEntityToBo(UserDeviceEntity userDeviceEntity);

    UserAccessCodeBo userAccessCodeEntityToBo(UserAccessCodeEntity userAccessCodeEntity);

    UserAccessCodeEntity userAccessCodeBoToEntity(UserAccessCodeBo userAccessCodeBo);
}
