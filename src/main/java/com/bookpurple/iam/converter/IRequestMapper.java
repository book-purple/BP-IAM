package com.bookpurple.iam.converter;

import com.bookpurple.iam.bo.*;
import com.bookpurple.iam.dto.AuthRequestDto;
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

    UserBo userEntityToBo(UserEntity userEntity);

    UserEntity userBoToEntity(UserBo userBo);

    UserDeviceBo userDeviceBoToEntity(UserDeviceEntity userDeviceEntity);

    UserDeviceEntity userDeviceEntityToBo(UserDeviceBo userDeviceBo);

    UserAccessCodeBo userDevicAccessCodeBoToEntity(UserAccessCodeEntity userAccessCodeEntity);

    UserAccessCodeEntity userAccessCodeEntityToBo(UserAccessCodeBo userAccessCodeBo);
}
