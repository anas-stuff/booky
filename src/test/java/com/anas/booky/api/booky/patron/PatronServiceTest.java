package com.anas.booky.api.booky.patron;

import com.anas.booky.api.booky.patron.exceptions.PatronAlreadyExistsExeption;
import com.anas.booky.api.booky.patron.exceptions.PatronNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class PatronServiceTest {

    @Mock
    private PatronRepository repository;

    @InjectMocks
    private PatronService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updatePatron_updatesPatronDetails_whenPatronExistsAndEmailIsUnique() throws PatronNotFoundException, PatronAlreadyExistsExeption {
        final var id = 1L;
        final var existingPatron = Patron.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .email("johnedoe@example.com")
                .phoneNumber("1234567890")
                .address("123 Street")
                .build();

        final var updatedPatron = Patron.builder()
                .id(id)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .phoneNumber("0987654321")
                .address("456 Avenue")
                .build();

        when(repository.findById(id)).thenReturn(Optional.of(existingPatron));
        when(repository.findByEmail(updatedPatron.getEmail())).thenReturn(Optional.empty());

        Patron result = service.updatePatron(id, updatedPatron);

        assertEquals(updatedPatron.getFirstName(), result.getFirstName());
        assertEquals(updatedPatron.getLastName(), result.getLastName());
        assertEquals(updatedPatron.getEmail(), result.getEmail());
        assertEquals(updatedPatron.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(updatedPatron.getAddress(), result.getAddress());
    }

    @Test
    void updatePatron_throwsPatronNotFoundException_whenPatronDoesNotExist() {
        final var id = 1L;
        final var updatedPatron = Patron.builder()
                .id(id)
                .firstName("Jane")
                .email("jane.doe@example.com")
                .phoneNumber("0987654321")
                .address("456 Avenue")
                .build();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(PatronNotFoundException.class, () -> service.updatePatron(id, updatedPatron));
    }

    @Test
    void updatePatron_throwsPatronAlreadyExistsExeption_whenEmailAlreadyExistsForAnotherPatron() {
        final var id = 1L;
        final var existingPatron = Patron.builder()
                .id(id)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phoneNumber("1234567890")
                .address("123 Street")
                .build();

        final var updatedPatron = Patron.builder()
                .id(id)
                .firstName("Jane")
                .lastName("Doe")
                .email("jane.doe@example.com")
                .phoneNumber("0987654321")
                .address("456 Avenue")
                .build();

        final var anotherPatronWithSameEmail = Patron.builder()
                .id(2L)
                .firstName("Alice")
                .lastName("Smith")
                .email("jane.doe@example.com")
                .phoneNumber("1112223333")
                .address("789 Boulevard")
                .build();
        when(repository.findById(id)).thenReturn(Optional.of(existingPatron));
        when(repository.findByEmail(updatedPatron.getEmail())).thenReturn(Optional.of(anotherPatronWithSameEmail));

        assertThrows(PatronAlreadyExistsExeption.class, () -> service.updatePatron(id, updatedPatron));
    }
}