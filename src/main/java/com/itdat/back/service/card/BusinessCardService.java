package com.itdat.back.service.card;

import com.itdat.back.entity.auth.User;
import com.itdat.back.entity.card.BusinessCard;
import com.itdat.back.repository.auth.UserRepository;
import com.itdat.back.repository.card.BusinessCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class BusinessCardService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusinessCardRepository businessCardRepository;


    public User findByUserEmail(String userEmail) {

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUserEmail(userEmail));

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }

        return optionalUser.get();
    }


    public BusinessCard saveBusinessCard(BusinessCard card) {
        if (card.getUserEmail() == null) {
            throw new IllegalArgumentException("명함에 연결된 유효한 사용자가 필요합니다.");
        }
        return businessCardRepository.save(card);
    }



    public void saveBusinessCardWithLogo(BusinessCard card) {
        System.out.println("서비스 로고");
        System.out.println(card);
        businessCardRepository.save(card);
    }



    public List<BusinessCard> getBusinessCardsByUserEmail(String userEmail) {
        return businessCardRepository.findByUserEmail(userEmail);
    }

    @Transactional
    public boolean deleteCard(String email) {
        List<BusinessCard> cards = businessCardRepository.findByUserEmail(email);
        if (cards.isEmpty()) {
            return false;
        } else {
            businessCardRepository.deleteByUserEmail(email);
            return true;
        }
    }

    public void updateCardPublicStatus(List<Map<String, Object>> cardData) {
        System.out.println("서비스");
        for (Map<String, Object> card : cardData) {
            String userEmail = (String) card.get("userEmail");
            Integer cardNo = (Integer) card.get("cardNo");
            boolean isPublic = (boolean) card.get("isPublic");

            // userEmail을 기반으로 명함을 찾아 공개 상태 업데이트
            BusinessCard businessCard = businessCardRepository.findByCardNoAndUserEmail(cardNo, userEmail);
            if (businessCard != null) {
                businessCard.setIsPublic(isPublic);
                businessCardRepository.save(businessCard);
            } else {
                // 명함을 찾지 못한 경우 예외 처리
                throw new IllegalArgumentException("명함을 찾을 수 없습니다. 카드 번호: " + cardNo + ", 이메일: " + userEmail);
            }
        }
    }


    public BusinessCard updateBusinessCard(BusinessCard businessCard) {
        BusinessCard existingCard = businessCardRepository.findByCardNoAndUserEmail(
                businessCard.getCardNo(), businessCard.getUserEmail());
        if (existingCard != null) {
            existingCard.setUserName(businessCard.getUserName());
            existingCard.setPhone(businessCard.getPhone());
            existingCard.setCompanyName(businessCard.getCompanyName());
            existingCard.setCompanyNumber(businessCard.getCompanyNumber());
            existingCard.setCompanyAddress(businessCard.getCompanyAddress());
            existingCard.setCompanyFax(businessCard.getCompanyFax());
            existingCard.setDepartment(businessCard.getDepartment());
            existingCard.setPosition(businessCard.getPosition());
            existingCard.setCardSide(businessCard.getCardSide());
            existingCard.setBackgroundColor(businessCard.getBackgroundColor());
            existingCard.setTextColor(businessCard.getTextColor());
            existingCard.setFontFamily(businessCard.getFontFamily());
            return businessCardRepository.save(existingCard);
        }
        return null;
    }

    @Transactional
    public boolean deleteOnlyCard(Integer cardNo, String userEmail) {
        System.out.println(cardNo + userEmail);
        List<BusinessCard> cards = businessCardRepository.findAllByCardNoAndUserEmail(cardNo, userEmail);
        if (!cards.isEmpty()) {
            businessCardRepository.deleteAll(cards);
            return true;
        } else {
            return false;
        }
    }

    public BusinessCard updateBusinessCardWithLogo(BusinessCard updatedCard) {
        BusinessCard existingCard = businessCardRepository.findByCardNoAndUserEmail(
                updatedCard.getCardNo(), updatedCard.getUserEmail());
        System.out.println(updatedCard.getLogoUrl());
        if (existingCard != null) {
            existingCard.setUserName(updatedCard.getUserName());
            existingCard.setPhone(updatedCard.getPhone());
            existingCard.setCompanyName(updatedCard.getCompanyName());
            existingCard.setCompanyNumber(updatedCard.getCompanyNumber());
            existingCard.setCompanyAddress(updatedCard.getCompanyAddress());
            existingCard.setCompanyFax(updatedCard.getCompanyFax());
            existingCard.setDepartment(updatedCard.getDepartment());
            existingCard.setPosition(updatedCard.getPosition());
            existingCard.setCardSide(updatedCard.getCardSide());
            existingCard.setLogoUrl(updatedCard.getLogoUrl());
            existingCard.setBackgroundColor(updatedCard.getBackgroundColor());
            existingCard.setTextColor(updatedCard.getTextColor());
            existingCard.setFontFamily(updatedCard.getFontFamily());
            return businessCardRepository.save(existingCard);
        }
        return null;
    }
}
