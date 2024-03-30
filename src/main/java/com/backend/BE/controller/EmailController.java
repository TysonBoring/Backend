package com.backend.BE.controller;

import com.backend.BE.model.Email;
import com.backend.BE.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/emails")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping
    public ResponseEntity<List<Email>> getAllEmails() {
        List<Email> emails = emailService.getAllEmails();
        return ResponseEntity.ok(emails);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Email> getEmailById(@PathVariable Long id) {
        Email email = emailService.getEmailById(id)
                .orElse(null); // Handle not found case in service layer
        return email != null ? ResponseEntity.ok(email) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Email> createEmail(@Valid @RequestBody Email email) {
        Email createdEmail = emailService.saveEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmail);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Email> updateEmail(@PathVariable Long id, @Valid @RequestBody Email email) {
        Email existingEmail = emailService.getEmailById(id)
                .orElse(null); // Handle not found case in service layer
        if (existingEmail == null) {
            return ResponseEntity.notFound().build();
        }
        email.setId(id);
        Email updatedEmail = emailService.saveEmail(email);
        return ResponseEntity.ok(updatedEmail);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(@PathVariable Long id) {
        emailService.deleteEmailById(id);
        return ResponseEntity.noContent().build();
    }
}
