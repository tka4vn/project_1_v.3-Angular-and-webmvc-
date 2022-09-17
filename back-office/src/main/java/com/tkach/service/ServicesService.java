package com.tkach.service;

import com.tkach.model.Roles;
import com.tkach.model.Services;
import com.tkach.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    public void addRoles(Services services, Roles roles
    ) {
        services.getIdSerRol().add(roles);

        servicesRepository.save(services);
    }

    public void removeRoles(Services services, Roles roles
    ) {
        services.getIdSerRol().remove(roles);

        servicesRepository.save(services);
    }

    @Transactional
    public void delete(Services services
    ) {
        servicesRepository.delete(services);
    }
}
