package com.zosh.request;

import com.zosh.modal.Chat;
import com.zosh.modal.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CreateMessageRequest {


    private Long senderId;

    private Long projectId;

    private String content;


}
