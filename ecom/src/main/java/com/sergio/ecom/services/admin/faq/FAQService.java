package com.sergio.ecom.services.admin.faq;

import com.sergio.ecom.dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
