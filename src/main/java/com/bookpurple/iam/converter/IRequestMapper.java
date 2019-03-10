package com.bookpurple.iam.converter;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.TempAuthBo;
import com.bookpurple.iam.bo.UserAccessCodeBo;
import com.bookpurple.iam.bo.UserDeviceBo;
import com.bookpurple.iam.dto.AuthRequestDto;
import com.bookpurple.iam.entity.TempAuthEntity;
import com.bookpurple.iam.entity.UserAccessCodeEntity;
import com.bookpurple.iam.entity.UserDeviceEntity;
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

    UserDeviceBo userDeviceBoToEntity(UserDeviceEntity userDeviceEntity);

    UserDeviceEntity userDeviceEntityToBo(UserDeviceBo userDeviceBo);

    UserAccessCodeBo userDevicAccessCodeBoToEntity(UserAccessCodeEntity userAccessCodeEntity);

    UserAccessCodeEntity userAccessCodeEntityToBo(UserAccessCodeBo userAccessCodeBo);
}
