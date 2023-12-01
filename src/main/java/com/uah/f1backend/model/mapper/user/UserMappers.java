package com.uah.f1backend.model.mapper.user;


import com.uah.f1backend.model.UserModel;
import com.uah.f1backend.model.dto.user.UserDTORequest;
import com.uah.f1backend.model.dto.user.UserDTOResponse;


import java.util.List;

public class UserMappers {

    public static UserDTORequest toUserDTORequest(UserModel um){
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

    public static UserDTOResponse toUserDTOResponse(UserModel um){
        try{
            return new UserDTOResponse(
                um.getId(),
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

    public static List<UserDTOResponse> toUserDTOResponse(List<UserModel> cm){
        return cm.stream().map(UserMappers::toUserDTOResponse).toList();
    }

    public static UserModel toUserModel(UserDTORequest udr){
        try{
            final var um = new UserModel();
            um.setEmail(udr.getEmail());
            um.setPassword(udr.getPassword());
            um.setName(udr.getName());
            um.setLastname(udr.getLastname());
            um.setUsername(udr.getUsername());
            um.setRole(udr.getRole());
            um.setValidated(udr.getValidated());
            return um;
        }catch (NullPointerException e){
            return null;
        }
    }




}
