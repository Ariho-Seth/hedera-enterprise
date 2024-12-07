package com.openelements.hiero.spring.test;

import com.openelements.hiero.base.HederaException;
import com.openelements.hiero.base.NetworkRepository;
import com.openelements.hiero.base.mirrornode.ExchangeRates;
import com.openelements.hiero.base.mirrornode.NetworkFee;
import com.openelements.hiero.base.mirrornode.NetworkStake;
import com.openelements.hiero.base.mirrornode.NetworkSupplies;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestConfig.class)
public class NetworkRepositoryTest {
    @Autowired
    private NetworkRepository networkRepository;

    @Test
    void findExchangeRates() throws HederaException {
        Optional<ExchangeRates> result = networkRepository.exchangeRates();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void findNetworkFees() throws HederaException {
        List<NetworkFee> result = networkRepository.fees();

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void findNetworkStake() throws HederaException {
        Optional<NetworkStake> result = networkRepository.stake();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @Disabled
    void findNetworkSupplies() throws HederaException {
        Optional<NetworkSupplies> result = networkRepository.supplies();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isPresent());
    }
}
