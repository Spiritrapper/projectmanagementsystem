package com.zosh.controller;

import com.zosh.modal.PlanType;
import com.zosh.modal.Subscription;
import com.zosh.modal.User;
import com.zosh.service.SubscriptionService;
import com.zosh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.getUserSubscription(user.getId());

        return  new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<Subscription> upgradeSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestParam PlanType planType
            ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        Subscription subscription = subscriptionService.upgradeSubscription(user.getId(), planType);

        return  new ResponseEntity<>(subscription, HttpStatus.OK);
    }
}
