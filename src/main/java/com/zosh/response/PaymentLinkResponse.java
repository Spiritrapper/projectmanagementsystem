package com.zosh.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class PaymentLinkResponse {

    private String payment_Link_url;
    private String payment_Link_id;

    public PaymentLinkResponse(String payment_Link_url, String payment_Link_id) {}

}
