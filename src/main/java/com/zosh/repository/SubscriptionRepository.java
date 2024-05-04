package com.zosh.repository;

import com.zosh.modal.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    Subscription findByUserId(Long userId);
}
