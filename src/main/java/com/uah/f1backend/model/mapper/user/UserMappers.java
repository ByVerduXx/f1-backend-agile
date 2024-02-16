package com.uah.f1backend.model.mapper.user;

import com.uah.f1backend.model.UserModel;
import com.uah.f1backend.model.dto.user.UserDTORequest;
import com.uah.f1backend.model.dto.user.UserDTOResponse;
import com.uah.f1backend.model.mapper.role.RoleMappers;
import java.util.List;

public class UserMappers {

    public static UserDTOResponse toUserDTOResponse(UserModel um) {
        return new UserDTOResponse(
                um.getId(),
                um.getEmail(),
                um.getName(),
                um.getLastname(),
                um.getUsername(),
                RoleMappers.toRoleDTOResponse(um.getRole()),
                um.getValidated());
    }

    public static List<UserDTOResponse> toUserDTOResponse(List<UserModel> cm) {
        return cm.stream().map(UserMappers::toUserDTOResponse).toList();
    }

    public static UserModel toUserModel(UserDTORequest udr) {
        final var um = new UserModel();
        um.setEmail(udr.getEmail());
        um.setPassword(udr.getPassword());
        um.setName(udr.getName());
        um.setLastname(udr.getLastname());
        um.setUsername(udr.getUsername());

        return um;
    }
}
