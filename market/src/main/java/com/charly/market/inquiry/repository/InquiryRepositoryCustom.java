package com.charly.market.inquiry.repository;

import com.charly.market.inquiry.model.dto.InquirySearchRequest;
import com.charly.market.inquiry.model.entity.Inquiry;
import org.springframework.data.domain.Page;


public interface InquiryRepositoryCustom {
    Page<Inquiry> search (InquirySearchRequest request);


}
