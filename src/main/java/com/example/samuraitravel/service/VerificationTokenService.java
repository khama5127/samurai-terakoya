package com.example.samuraitravel.service;

import com.example.samuraitravel.entity.VerificationToken;
import com.example.samuraitravel.repository.VerificationTokenRepository;
import com.example.samuraitravel.entity.User;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {
    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    public void create(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();

        verificationToken.setUser(user);
        verificationToken.setToken(token);

        verificationTokenRepository.save(verificationToken);
    }

    //トークンの文字列で検索した結果を返す
    public VerificationToken getVerificationToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }
}
