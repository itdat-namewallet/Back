package com.itdat.back.service.card;

import com.itdat.back.entity.auth.User;
import com.itdat.back.entity.card.BusinessCard;
import com.itdat.back.repository.auth.UserRepository;
import com.itdat.back.repository.card.BusinessCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BusinessCardService {

    private static final String LOGO_DIRECTORY = "logo/";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessCardRepository businessCardRepository;

    // 유저 정보 가져오기
    public User findByUserEmail(String userEmail) {

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserEmail(userEmail));

        if (!optionalUser.isPresent()) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }

        User user = optionalUser.get();
        return user;

    }

    // 앱 - 명함 저장
    public BusinessCard saveBusinessCard(BusinessCard card) {
        if (card.getUserEmail() == null) {
            throw new IllegalArgumentException("명함에 연결된 유효한 사용자가 필요합니다.");
        }

        // 명함 저장
        return businessCardRepository.save(card);
    }

    // 명함 가져오기
    public List<BusinessCard> getBusinessCardsByUserEmail(String userEmail) {
        return businessCardRepository.findByUserEmail(userEmail);
    }

    // 로고 파일 저장
    public String saveLogoFile(MultipartFile logo) throws IOException {
        if (logo.isEmpty()) {
            throw new IllegalArgumentException("로고 파일이 비어 있습니다.");
        }

        // 파일 저장 경로 생성
        String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
        Path filePath = Paths.get(LOGO_DIRECTORY, fileName);

        // 디렉토리가 없으면 생성
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // 파일 저장
        Files.write(filePath, logo.getBytes());

        // 파일 URL 반환
        return "/uploads/" + fileName;
    }
}