package com.uah.f1backend.model.mapper.user;

import com.uah.f1backend.model.UserModel;
import com.uah.f1backend.model.dto.User.UserDTORequest;

import java.util.List;

public class UserMappers {

    public static UserDTORequest toUserDTORequestMapper(UserModel um){
        try{
            return new UserDTORequest(
                um.getEmail(),
                um.getPassword(),
                um.getName(),
                um.getLastname(),
                um.getUsername(),
                um.getRole(),
                um.getValidated()
            );
        }catch (NullPointerException e){
            return null;
        }
    }






}
