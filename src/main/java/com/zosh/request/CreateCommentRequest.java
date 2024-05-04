package com.zosh.request;

import lombok.Data;

@Data
public class CreateCommentRequest {

    private String content;

    private Long issueId;
}
