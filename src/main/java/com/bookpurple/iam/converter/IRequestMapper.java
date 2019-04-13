package com.bookpurple.iam.converter;

import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.dto.AuthRequestDto;
import com.bookpurple.iam.dto.DeviceTokenRequestDto;
import com.bookpurple.iam.dto.SignUpRequestDto;
import com.bookpurple.iam.dto.SignUpResponseDto;
import com.bookpurple.iam.entity.*;
import com.bookpurple.iam.entity.mongo.MTempAuthEntity;
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

    TempAuthBo mTempAuthEntityToBo(MTempAuthEntity tempAuthEntity);

    MTempAuthEntity mTempAuthBoToEntity(TempAuthBo tempAuthBo);

    SignUpRequestBo signUpRequestDtoToBo(SignUpRequestDto signUpRequestDto);

    SignUpResponseDto signUpResponseBoToDto(SignUpResponseBo signUpResponseBo);

    UserBo userEntityToBo(UserEntity userEntity);

    UserEntity userBoToEntity(UserBo userBo);

    UserDeviceEntity userDeviceBoToEntity(UserDeviceBo userDeviceBo);

    UserDeviceBo userDeviceEntityToBo(UserDeviceEntity userDeviceEntity);

    UserAccessCodeBo userAccessCodeEntityToBo(UserAccessCodeEntity userAccessCodeEntity);

    UserAccessCodeEntity userAccessCodeBoToEntity(UserAccessCodeBo userAccessCodeBo);

    UserDeviceTokenBo userDeviceTokenEntityToBo(UserDeviceTokenEntity userDeviceTokenEntity);

    UserDeviceTokenEntity userDeviceTokenBoToEntity(UserDeviceTokenBo userDeviceTokenBo);

    DeviceTokenRequestBo deviceTokenRequestDtoToBo(DeviceTokenRequestDto deviceTokenRequestDto);
}
