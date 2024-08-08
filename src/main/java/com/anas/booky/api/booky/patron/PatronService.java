package com.anas.booky.api.booky.patron;

import com.anas.booky.api.booky.patron.exceptions.PatronAlreadyExistsExeption;
import com.anas.booky.api.booky.patron.exceptions.PatronNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PatronService {
    private final PatronRepository repository;

    /**
     * Get all patrons
     * @return List of all patrons
     */
    public List<Patron> getPatrons() {
        return repository.findAll();
    }

    /**
     * Save a new patron
     * @param patron The patron to save
     * @return The saved patron
     * @throws PatronAlreadyExistsExeption If a patron with the same email already exists
     */
    public Patron savePatron(final Patron patron) throws PatronAlreadyExistsExeption {
        // Check if there is a patron with the same email already in the database
        if (repository.findByEmail(patron.getEmail()).isPresent()) {
            throw new PatronAlreadyExistsExeption(patron.getEmail());
        }
        return repository.save(patron);
    }

    /**
     * Get a patron by id
     * @param id The id of the patron
     * @return The patron with the given id
     * @throws PatronNotFoundException If the patron with the given id does not exist
     */
    public Optional<Patron> getPatron(final Long id) {
        return repository.findById(id);
    }

    /**
     * Update a patron
     * @param id The id of the patron to update
     * @param patron The new patron details
     * @return The updated patron
     * @throws PatronNotFoundException If the patron with the given id does not exist
     * @throws PatronAlreadyExistsExeption If a patron with the same email already exists
     */
    @Transactional
    public Patron updatePatron(final Long id, final Patron patron) throws PatronNotFoundException, PatronAlreadyExistsExeption {
        // Check if the patron exists
        var existingPatron = repository.findById(id).orElseThrow(() -> new PatronNotFoundException(id));

        // Check if there is another patron with the same email
        var existingPatronWithSameEmail = repository.findByEmail(patron.getEmail());
        if (existingPatronWithSameEmail.isPresent() && !existingPatronWithSameEmail.get().getId().equals(id)) {
            throw new PatronAlreadyExistsExeption(patron.getEmail());
        }

        // Update the patron details
        if (patron.getFirstName() != null && !patron.getFirstName().isEmpty()) {
            existingPatron.setFirstName(patron.getFirstName());
        }
        if (patron.getLastName() != null && !patron.getLastName().isEmpty()) {
            existingPatron.setLastName(patron.getLastName());
        }
        if (patron.getEmail() != null && !patron.getEmail().isEmpty()) {
            existingPatron.setEmail(patron.getEmail());
        }
        if (patron.getPhoneNumber() != null && !patron.getPhoneNumber().isEmpty()) {
            existingPatron.setPhoneNumber(patron.getPhoneNumber());
        }
        if (patron.getAddress() != null && !patron.getAddress().isEmpty()) {
            existingPatron.setAddress(patron.getAddress());
        }

        return existingPatron;
    }

    /**
     * Delete a patron
     * @param id The id of the patron to delete
     * @throws PatronNotFoundException If the patron with the given id does not exist
     */
    public void deletePatron(final Long id) throws PatronNotFoundException {
        if (!repository.existsById(id)) {
            throw new PatronNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
