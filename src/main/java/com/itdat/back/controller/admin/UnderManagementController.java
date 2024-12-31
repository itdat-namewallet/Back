package com.itdat.back.controller.admin;

import com.itdat.back.entity.admin.ReportUser;
import com.itdat.back.entity.admin.UnderManagement;
import com.itdat.back.entity.auth.User;
import com.itdat.back.model.dto.ReportUserDTO;
import com.itdat.back.repository.admin.UnderManagementRepository;
import com.itdat.back.repository.auth.UserRepository;
import com.itdat.back.service.admin.UnderManagementService;
import com.itdat.back.service.auth.UserService;
import com.itdat.back.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.itdat.back.entity.auth.Role.ADMIN;

@RestController
@RequestMapping("/admin")
public class UnderManagementController {

    @Autowired
    private UnderManagementService underManagementService;

    @Autowired
    private UserService userService;

    @Autowired
    private UnderManagementRepository underManagementRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/bring-reported-user-list-detail")
//    public ResponseEntity<Object> getReportedUserListDetail() {
//        System.out.println("-------------------------------- 신고된 유저의 상세 정보 리스트를 가져오는 컨트롤러 --------------------------------");
//
//            List<User> reportedUserList = underManagementService.reportedUserListDetail();
//
//            if(reportedUserList.isEmpty()) {
//                return ResponseEntity.status(500).body("신고된 유저가 없습니다.");
//            }
//            return ResponseEntity.ok(reportedUserList);
//    }

    /** 신고된 유저의 정보를 가져오는 컨트롤러 */
    @GetMapping("/bring-reported-user-list")
    public ResponseEntity<Object> getReportedUserListBrief() {
        List<Object> reportedUserList = underManagementService.getReportedUsers();

        if(reportedUserList.isEmpty()) {
            return ResponseEntity.status(500).body("신고된 유저가 없습니다.");
        }
        return ResponseEntity.ok(reportedUserList);
    }

    /** 사용자가 특정 유저를 신고하는 컨트롤러 */
    @PostMapping("/report-user")
    public ResponseEntity<Object> reportUser(@RequestBody ReportUserDTO reportUserDTO) {
        // 사용자로부터 특정 유저의 아이디와 설명(신고 이유) 그리고 신고 당사자의 아이디를 받아낸다.
        // 상기 정보들에 현재 시간을 추가해 ReportUser 엔티티에 추가한다.

        System.out.println("reportUserDTO = " + reportUserDTO);

        boolean result = underManagementService.reportUser(reportUserDTO);
        if (result){
            return ResponseEntity.ok("신고되었습니다.");
        }
        return ResponseEntity.status(500).body("신고에 실패하였습니다.");
    }

    /** 사용자들의 신고 기록을 가져오는 컨트롤러 */
    @GetMapping("/report-user-list")
    public ResponseEntity<Object> getReportUserList() {
        List<ReportUser> reportUserList = underManagementService.bringReportUserList();
        if(reportUserList.isEmpty()) {
            return ResponseEntity.status(500).body("아직 신고된 기록이 없습니.");
        }
        return ResponseEntity.ok(reportUserList);
    }

    /** 사용자로부터 token을 받아 해당 유저의 ADMIN 권한을 체크하는 컨트롤러 */
    @GetMapping("/users")
    public ResponseEntity<Object> getAdminUsers(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) { // 요청에 토큰이 담겨 있는지, 형식에 맞게 잘 왔는지 확인
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 없거나 유효하지 않습니다.");
        }

        String jwtToken = token.substring(7); // 대가리("Bearer ") 따고 토큰만 추출

        String email = jwtTokenUtil.extractEmail(jwtToken); // 해당 토큰에서 email 추출

        User user = userRepository.findByUserEmail(email); // email로 해당 유저의 정보를 추출(ADMIN 권한을 이제 알 수 있다.)
        if (user.getRole().equals(ADMIN)) {
            return ResponseEntity.ok(true); // 해당 유저의 권한이 ADMIN이면 true를 리턴
            // List<User> adminUsers = underManagementService.getAdminUsers();
        } else {
            return ResponseEntity.ok(false); // 해당 유저의 권한이 ADMIN이 아니면 false를 리턴
        }
    }

    @GetMapping("/detail-info")
    public ResponseEntity<Object> detailInfo(@RequestParam String userId) {
        System.out.println("userId = " + userId);



        UnderManagement detailInfo = underManagementService.findByUserId(userId);

       return ResponseEntity.ok("test");
    }

}
