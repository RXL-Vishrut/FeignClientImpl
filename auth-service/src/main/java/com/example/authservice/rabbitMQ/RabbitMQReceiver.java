package com.example.authservice.rabbitMQ;

import com.example.authservice.domain.User;
import com.example.authservice.userRepository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RabbitMQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Autowired
    UserRepo userRepo;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessages(Payload payload) {
        logger.info("The user id received is: {}", payload.getUserId());
        Long userId = payload.getUserId();
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()) {
            User savedUser = user.get();
            User referrer = userRepo.findByCouponCode(savedUser.getReferrerCouponCode());
            savedUser.setRewardAmount(100.00);
            referrer.setRewardAmount(100.00);
            userRepo.save(savedUser);
            userRepo.save(referrer);
        }

    }
}
