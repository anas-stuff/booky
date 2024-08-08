package com.anas.booky.api.booky.admin;

import com.anas.booky.api.booky.admin.exceptions.AdminNotFoundException;
import com.anas.booky.api.booky.admin.exceptions.EmailAlreadyInUse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AdminService {
    private final AdminRepository repository;

    public Admin saveAdmin(final Admin admin) throws EmailAlreadyInUse {
        // Check if the email already in use
        if (repository.existsAdminByEmail(admin.getEmail())) {
            throw new EmailAlreadyInUse();
        }
        admin.setId(null); // Ensure that the ID is not set
        return repository.save(admin);
    }

    @Transactional
    public Admin updateAdmin(final Long id, final Admin admin) throws AdminNotFoundException {
        // Check if the admin exists
        if (repository.existsById(id)) {
            throw new AdminNotFoundException(id);
        }
        admin.setId(id); // Ensure that the user cannot modify the ID
        return repository.save(admin);
    }
}
