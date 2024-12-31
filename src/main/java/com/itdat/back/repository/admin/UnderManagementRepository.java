package com.itdat.back.repository.admin;

import com.itdat.back.entity.admin.UnderManagement;
import com.itdat.back.entity.auth.User;
import com.itdat.back.entity.auth.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnderManagementRepository extends JpaRepository<UnderManagement, Integer> {
    UnderManagement findByuserId(User userId);

    @Query("SELECT um FROM UnderManagement um " +
            "JOIN um.user u " +
            "WHERE u.status <> 'ACTIVE'")
    List<Object> findAllByUserStatusNotACTIVE();


    UnderManagement findByUserId(User user);
}
