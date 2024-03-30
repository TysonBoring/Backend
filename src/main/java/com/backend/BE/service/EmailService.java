package com.backend.BE.service;

import com.backend.BE.model.Email;
import com.backend.BE.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {

    private final EmailRepository emailRepository;

    @Autowired
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }

    public Optional<Email> getEmailById(Long id) {
        return emailRepository.findById(id);
    }

    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }

    public void deleteEmailById(Long id) {
        emailRepository.deleteById(id);
    }
}
