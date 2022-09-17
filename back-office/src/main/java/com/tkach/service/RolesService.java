package com.tkach.service;

import com.tkach.model.Roles;
import com.tkach.model.Services;
import com.tkach.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    public void addServices(Roles roles, Services services
    ) {
        roles.getIdRolSer().add(services);

        rolesRepository.save(roles);
    }

    public void removeServices(Roles roles, Services services
    ) {
        roles.getIdRolSer().remove(services);

        rolesRepository.save(roles);
    }

    @Transactional
    public void delete(Roles roles
    ) {
        rolesRepository.delete(roles);
    }
}
