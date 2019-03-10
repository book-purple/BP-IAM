package com.bookpurple.iam.converter;

/*
 * Created by gauravsharma on 2019-03-10.
 */

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.dto.AuthRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRequestMapper {

    AuthRequestBo authRequestDtoToBo(AuthRequestDto authRequestDto);

    AuthRequestDto authRequestBoToDto(AuthRequestBo authRequestBo);
}
