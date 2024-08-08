package com.anas.booky.api.booky.admin;

import com.anas.booky.api.booky.admin.exceptions.EmailAlreadyInUse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired)  )
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    @PostMapping("/add")
    public Admin saveAdmin(@RequestBody  final Admin admin) throws EmailAlreadyInUse {
        admin.setId(null); // Ensure that the ID is not set
        return service.saveAdmin(admin);
    }

    @PostMapping("/login")
    public ResponseEntity<String > login(@RequestBody final  LoginRequest request) {
        final  var admin = service.checkPassword(request.getEmail(), request.getPassword());
        return admin.map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("update")
    public Admin updateAdmin(@RequestBody final Admin admin) {
        return service.updateAdmin(admin.getId(), admin);
    }

    @DeleteMapping("delete/{id}")
    public void deleteAdmin(@PathVariable  final Long id) {
        service.deleteAdmin(id);
    }
}
