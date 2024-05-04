package com.zosh.service;

import com.zosh.modal.PlanType;
import com.zosh.modal.Subscription;
import com.zosh.modal.User;

import java.util.concurrent.Flow;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId)throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
