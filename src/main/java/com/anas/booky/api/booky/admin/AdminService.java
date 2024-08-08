package com.anas.booky.api.booky.admin;

import com.anas.booky.api.booky.admin.exceptions.AdminNotFoundException;
import com.anas.booky.api.booky.admin.exceptions.EmailAlreadyInUse;
import com.anas.booky.api.booky.security.config.JwtService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
public class AdminService {
    private final AdminRepository repository;
    private  final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Admin saveAdmin(final Admin admin) throws EmailAlreadyInUse {
        log.info("Saving admin {}", admin);
        // Check if the email already in use
        if (repository.existsAdminByEmail(admin.getEmail())) {
            throw new EmailAlreadyInUse();
        }
        admin.setId(null); // Ensure that the ID is not set
        final var savedAdmin = repository.save(admin);
        log.info("Admin saved with ID {}", savedAdmin.getId());
        return savedAdmin;
    }

    @Transactional
    public Admin updateAdmin(final Long id, final Admin admin) throws AdminNotFoundException {
        log.info("Updating admin with ID {}", id);
        // Check if the admin exists
        if (repository.existsById(id)) {
            log.error("Admin with ID {} not found", id);
            throw new AdminNotFoundException(id);
        }
        admin.setId(id); // Ensure that the user cannot modify the ID
        return repository.save(admin);
    }

    public Optional<String > checkPassword(final String email, final String password) {
        log.info("Checking password for admin with email {}", email);
        final Admin admin = repository.findByEmail(email).orElseThrow(() -> new AdminNotFoundException(email));
        if (passwordEncoder.matches(password, admin.getPasswordHash())) {
            log.info("Password for admin with email {} is correct, admin ID: {}", email, admin.getId());
            return Optional.of(jwtService.generateToken(admin));
        }
        return Optional.empty();
    }

    public void deleteAdmin(final Long id) {
        log.info("Deleting admin with ID {}", id);
        repository.deleteById(id);
    }
}
