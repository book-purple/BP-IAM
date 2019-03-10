package com.bookpurple.iam.converter;

import com.bookpurple.iam.bo.TempAuthBo;
import com.bookpurple.iam.entity.TempAuthEntity;
import org.mapstruct.Mapper;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Mapper(componentModel = "spring")
public interface TempAuthRequestMapper {

    TempAuthBo entityToBo(TempAuthEntity tempAuthEntity);

    TempAuthEntity BoToEntity(TempAuthBo tempAuthBo);
}
