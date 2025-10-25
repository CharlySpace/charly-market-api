package com.charly.market.inquiry.repository;
import com.charly.market.inquiry.model.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, InquiryRepositoryCustom {
    //Inquiry findByInquiryId(Long inquiryId);
}