package com.itdat.back.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itdat.back.entity.mywallet.Folder;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "business_card")
public class BusinessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardId;


    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "card_no", nullable = false)
    private int cardNo;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_number")
    private String companyNumber;

    @Column(name = "company_address")
    private String companyAddress;

    @Column(name = "company_fax")
    private String companyFax;

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    @Column(name = "app_template")
    private String appTemplate;

    @Column(name = "web_template")
    private String webTemplate;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_side")
    @JsonProperty("cardSide")
    private CardSide cardSide;

    @Column(name = "logo_url")
    private String logoUrl;

    @JsonIgnore
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic; // 명함 공개 여부

    @ManyToOne
    @JoinColumn(name = "folder_id") // "folder_id"는 폴더를 참조하는 컬럼 이름
    private Folder folder;

    @Column(name = "background_color")
    private String backgroundColor;

    @Column(name = "text_color")
    private String textColor;

    @Column(name = "font_family")
    private String fontFamily;

    @Column(name = "custom_text")
    private String customText;

    @JsonProperty("isTextEnabled")
    @Column(name = "is_text_enabled")
    private boolean isTextEnabled;

    @Column(name = "text_position")
    private String textPosition;

    public BusinessCard() {
    }

    public BusinessCard(Integer cardId, String userEmail, int cardNo, String userName, String phone, String email, String companyName, String companyNumber, String companyAddress, String companyFax, String department, String position, String appTemplate, String webTemplate, CardSide cardSide, String logoUrl, LocalDateTime createdAt, LocalDateTime updatedAt, boolean isPublic, Folder folder, String backgroundColor, String textColor, String fontFamily, String customText, boolean isTextEnabled, String textPosition) {
        this.cardId = cardId;
        this.userEmail = userEmail;
        this.cardNo = cardNo;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.companyName = companyName;
        this.companyNumber = companyNumber;
        this.companyAddress = companyAddress;
        this.companyFax = companyFax;
        this.department = department;
        this.position = position;
        this.appTemplate = appTemplate;
        this.webTemplate = webTemplate;
        this.cardSide = cardSide;
        this.logoUrl = logoUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isPublic = isPublic;
        this.folder = folder;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.fontFamily = fontFamily;
        this.customText = customText;
        this.isTextEnabled = isTextEnabled;
        this.textPosition = textPosition;
    }

    public String getCustomText() {
        return customText;
    }

    public void setCustomText(String customText) {
        this.customText = customText;
    }

    public boolean isTextEnabled() {
        return isTextEnabled;
    }

    public void setTextEnabled(boolean IsTextEnabled) {
        isTextEnabled = IsTextEnabled;
    }

    public String getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(String textPosition) {
        this.textPosition = textPosition;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAppTemplate() {
        return appTemplate;
    }

    public void setAppTemplate(String appTemplate) {
        this.appTemplate = appTemplate;
    }

    public String getWebTemplate() {
        return webTemplate;
    }

    public void setWebTemplate(String webTemplate) {
        this.webTemplate = webTemplate;
    }

    public CardSide getCardSide() {
        return cardSide;
    }

    public void setCardSide(CardSide cardSide) {
        this.cardSide = cardSide;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "cardId=" + cardId +
                ", userEmail='" + userEmail + '\'' +
                ", cardNo=" + cardNo +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyNumber='" + companyNumber + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", companyFax='" + companyFax + '\'' +
                ", department='" + department + '\'' +
                ", position='" + position + '\'' +
                ", appTemplate='" + appTemplate + '\'' +
                ", webTemplate='" + webTemplate + '\'' +
                ", cardSide=" + cardSide +
                ", logoUrl='" + logoUrl + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", isPublic=" + isPublic +
                '}';
    }

}