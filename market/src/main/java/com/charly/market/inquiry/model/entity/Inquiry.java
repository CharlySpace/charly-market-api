package com.charly.market.inquiry.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId;
    private String inquiryTitle;
    private String inquiryContent;
    private char inquiryStatus;
    private String inquiryAnswer;

    public void deactivatedInquiryStatus() {
        this.inquiryStatus = 'N';
    }

}
