package com.openelements.hedera.microprofile;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.openelements.hedera.base.Account;
import com.openelements.hedera.base.AccountClient;
import com.openelements.hedera.base.ContractVerificationClient;
import com.openelements.hedera.base.FileClient;
import com.openelements.hedera.base.SmartContractClient;
import com.openelements.hedera.base.implementation.AccountClientImpl;
import com.openelements.hedera.base.implementation.FileClientImpl;
import com.openelements.hedera.base.implementation.HederaNetwork;
import com.openelements.hedera.base.implementation.ProtocolLayerClientImpl;
import com.openelements.hedera.base.implementation.SmartContractClientImpl;
import com.openelements.hedera.base.protocol.ProtocolLayerClient;
import com.openelements.hedera.microprofile.implementation.ContractVerificationClientImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jspecify.annotations.NonNull;

public class ClientProvider {

    @Inject
    @ConfigProperty(name = "hedera.accountId")
    private String accountIdAsString;

    @Inject
    @ConfigProperty(name = "hedera.privateKey")
    private String privateKeyAsString;

    @Inject
    @ConfigProperty(name = "hedera.network")
    private String network;

    private AccountId getAccountId() {
        if (network == null) {
            throw new IllegalStateException("accountId value is null");
        }
        try {
            return AccountId.fromString(accountIdAsString);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Can not parse 'hedera.newAccountId' property: '" + accountIdAsString + "'", e);
        }
    }

    private PrivateKey getPrivateKey() {
        if (network == null) {
            throw new IllegalStateException("privateKey value is null");
        }
        try {
            return PrivateKey.fromString(privateKeyAsString);
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "Can not parse 'hedera.privateKey' property: '" + privateKeyAsString + "'", e);
        }
    }

    private HederaNetwork getHederaNetwork() {
        if (network == null) {
            throw new IllegalStateException("network value is null");
        }
        return HederaNetwork.findByName(network)
                .orElseThrow(() -> new IllegalArgumentException(
                        "'hedera.network' property must be set to a valid value. Is '" + network + "'"));
    }

    private Client createClient() {
        final AccountId accountId = getAccountId();
        final PrivateKey privateKey = getPrivateKey();
        final HederaNetwork hederaNetwork = getHederaNetwork();
        return Client.forName(hederaNetwork.getName())
                .setOperator(accountId, privateKey);
    }

    @Produces
    @ApplicationScoped
    ProtocolLayerClient createProtocolLayerClient() {
        final Account operator = Account.of(getAccountId(), getPrivateKey());
        return new ProtocolLayerClientImpl(createClient(), operator);
    }

    @Produces
    @ApplicationScoped
    FileClient createFileClient(@NonNull final ProtocolLayerClient protocolLayerClient) {
        return new FileClientImpl(protocolLayerClient);
    }

    @Produces
    @ApplicationScoped
    SmartContractClient createSmartContractClient(@NonNull final ProtocolLayerClient protocolLayerClient,
            @NonNull final FileClient fileClient) {
        return new SmartContractClientImpl(protocolLayerClient, fileClient);
    }

    @Produces
    @ApplicationScoped
    AccountClient createAccountClient(@NonNull final ProtocolLayerClient protocolLayerClient) {
        return new AccountClientImpl(protocolLayerClient);
    }

    @Produces
    @ApplicationScoped
    ContractVerificationClient createContractVerificationClient(@NonNull final ProtocolLayerClient protocolLayerClient,
            @NonNull final FileClient fileClient) {
        return new ContractVerificationClientImpl(getHederaNetwork());
    }
}
