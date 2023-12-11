package com.uah.f1backend.model.mapper.role;
import com.uah.f1backend.model.RoleModel;
import com.uah.f1backend.model.dto.role.RoleDTOResponse;

import java.util.List;


public class RoleMappers {

    public static RoleDTOResponse toRoleDTOResponse(RoleModel rm) {
        return new RoleDTOResponse(rm.getId(), rm.getRoleName());
    }

    public static RoleModel toRoleModel(RoleDTOResponse rdr) {
        return new RoleModel(rdr.getId(), rdr.getRoleName());
    }





}
