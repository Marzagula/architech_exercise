package com.architech.user_registration.utils;

import com.architech.user_registration.model.UserAccount;
import com.architech.user_registration.model.dtos.UserAccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccountMapper {
    UserAccountMapper INSTANCE = Mappers.getMapper(UserAccountMapper.class);

    UserAccount toUser(UserAccountDTO userAccountDTO);

    UserAccountDTO toUserDTO(UserAccount userAccount);
}
