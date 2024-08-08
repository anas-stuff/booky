package com.anas.booky.api.booky.patron;

import com.anas.booky.api.booky.patron.exceptions.PatronAlreadyExistsExeption;
import com.anas.booky.api.booky.patron.exceptions.PatronNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatronController {
    private final PatronService service;

    @GetMapping
    public List<Patron> getPatrons() {
        return service.getPatrons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patron> getPatron(@PathVariable Long id) {
        return service.getPatron(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patron> savePatron(@RequestBody Patron patron) throws PatronAlreadyExistsExeption {
        return ResponseEntity.ok(service.savePatron(patron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> updatePatron(@PathVariable Long id, @RequestBody Patron patron) throws PatronNotFoundException, PatronAlreadyExistsExeption {
        return ResponseEntity.ok(service.updatePatron(id, patron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatron(@PathVariable Long id) throws PatronNotFoundException {
        service.deletePatron(id);
        return ResponseEntity.noContent().build();
    }
}