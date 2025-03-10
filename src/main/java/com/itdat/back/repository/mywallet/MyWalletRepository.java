package com.itdat.back.repository.mywallet;

import com.itdat.back.entity.card.BusinessCard;
import com.itdat.back.entity.mywallet.CardInfo;
import com.itdat.back.entity.nfc.MyWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyWalletRepository extends JpaRepository<MyWallet, Integer> {
    List<MyWallet> findByMyEmail(String myEmail);

    //    Optional<Object> findByUserEmailAndMyEmail(String userEmail, String myEmail);
    List<MyWallet> findByUserEmailAndMyEmail(String userEmail, String myEmail);

    @Query("SELECT m FROM MyWallet m WHERE m.myEmail = :myEmail AND NOT EXISTS (" +
            "SELECT 1 FROM FolderCard fc WHERE fc.cardId = m.id)")
    List<MyWallet> findCardsWithoutFolder(@Param("myEmail") String myEmail);

    @Query("SELECT b FROM BusinessCard b WHERE b.userEmail = :userEmail AND b.cardNo = :cardNo")
    List<BusinessCard> findByUserEmailAndCardNo(@Param("userEmail") String userEmail, @Param("cardNo") int cardNo);
}
