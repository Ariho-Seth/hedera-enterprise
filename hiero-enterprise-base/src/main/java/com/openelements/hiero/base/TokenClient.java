package com.openelements.hiero.base;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.TokenId;
import com.openelements.hiero.base.data.Account;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public interface TokenClient {

    /**
     * Create a new token. The operator account is used as suppler account and as treasury account for the token.
     *
     * @param name   the name of the token
     * @param symbol the symbol of the token
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    TokenId createToken(@NonNull String name, @NonNull String symbol) throws HieroException;

    /**
     * Create a new token. The operator account is used treasury account for the token.
     *
     * @param name      the name of the token
     * @param symbol    the symbol of the token
     * @param supplyKey the private key of the supplier account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull PrivateKey supplyKey)
            throws HieroException;


    /**
     * Create a new token. The operator account is used as treasury account for the token.
     *
     * @param name      the name of the token
     * @param symbol    the symbol of the token
     * @param supplyKey the private key of the supplier account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    default TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull String supplyKey)
            throws HieroException {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(symbol, "symbol must not be null");
        Objects.requireNonNull(supplyKey, "supplyKey must not be null");
        return createToken(name, symbol, PrivateKey.fromString(supplyKey));
    }


    /**
     * Create a new token.
     *
     * @param name              the name of the token
     * @param symbol            the symbol of the token
     * @param treasuryAccountId the ID of the treasury account
     * @param treasuryKey       the private key of the treasury account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull AccountId treasuryAccountId,
            @NonNull PrivateKey treasuryKey) throws HieroException;


    /**
     * Create a  new token.
     *
     * @param name              the name of the token
     * @param symbol            the symbol of the token
     * @param treasuryAccountId the ID of the treasury account
     * @param treasuryKey       the private key of the treasury account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    default TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull String treasuryAccountId,
            @NonNull String treasuryKey) throws HieroException {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(symbol, "symbol must not be null");
        Objects.requireNonNull(treasuryAccountId, "treasuryAccountId must not be null");
        Objects.requireNonNull(treasuryKey, "treasuryKey must not be null");
        return createToken(name, symbol, AccountId.fromString(treasuryAccountId),
                PrivateKey.fromString(treasuryKey));
    }

    /**
     * Create a new token.
     *
     * @param name            the name of the token
     * @param symbol          the symbol of the token
     * @param treasuryAccount the treasury account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    default TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull Account treasuryAccount)
            throws HieroException {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(symbol, "symbol must not be null");
        Objects.requireNonNull(treasuryAccount, "treasuryAccount must not be null");
        return createToken(name, symbol, treasuryAccount.accountId(), treasuryAccount.privateKey());
    }

    /**
     * Create a new token.
     *
     * @param name              the name of the token
     * @param symbol            the symbol of the token
     * @param treasuryAccountId the ID of the treasury account
     * @param treasuryKey       the private key of the treasury account
     * @param supplyKey         the private key of the supplier account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull AccountId treasuryAccountId,
            @NonNull PrivateKey treasuryKey, @NonNull PrivateKey supplyKey) throws HieroException;

    /**
     * Create a new token.
     *
     * @param name              the name of the token
     * @param symbol            the symbol of the token
     * @param treasuryAccountId the ID of the treasury account
     * @param treasuryKey       the private key of the treasury account
     * @param supplyKey         the private key of the supplier account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    default TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull String treasuryAccountId,
            @NonNull String treasuryKey, @NonNull String supplyKey) throws HieroException {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(symbol, "symbol must not be null");
        Objects.requireNonNull(treasuryAccountId, "treasuryAccountId must not be null");
        Objects.requireNonNull(treasuryKey, "treasuryKey must not be null");
        Objects.requireNonNull(supplyKey, "supplyKey must not be null");
        return createToken(name, symbol, AccountId.fromString(treasuryAccountId), PrivateKey.fromString(treasuryKey),
                PrivateKey.fromString(supplyKey));
    }

    /**
     * Create a new token.
     *
     * @param name            the name of the token
     * @param symbol          the symbol of the token
     * @param supplyKey       the private key of the supplier account
     * @param treasuryAccount the treasury account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    default TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull Account treasuryAccount,
            @NonNull PrivateKey supplyKey) throws HieroException {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(symbol, "symbol must not be null");
        Objects.requireNonNull(treasuryAccount, "treasuryAccount must not be null");
        Objects.requireNonNull(supplyKey, "supplyKey must not be null");
        return createToken(name, symbol, treasuryAccount.accountId(), treasuryAccount.privateKey(), supplyKey);
    }

    /**
     * Create a new token.
     *
     * @param name            the name of the token
     * @param symbol          the symbol of the token
     * @param supplyKey       the private key of the supplier account
     * @param treasuryAccount the treasury account
     * @return the ID of the new token
     * @throws HieroException if the token could not be created
     */
    default TokenId createToken(@NonNull String name, @NonNull String symbol, @NonNull Account treasuryAccount,
            @NonNull String supplyKey) throws HieroException {
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(symbol, "symbol must not be null");
        Objects.requireNonNull(treasuryAccount, "treasuryAccount must not be null");
        Objects.requireNonNull(supplyKey, "supplyKey must not be null");
        return createToken(name, symbol, treasuryAccount.accountId(), treasuryAccount.privateKey(),
                PrivateKey.fromString(supplyKey));
    }

    /**
     * Associate an account with token.
     *
     * @param tokenId    the ID of the token
     * @param accountId  the ID of the account
     * @param accountKey the private key of the account
     * @throws HieroException if the account could not be associated with the token
     */
    void associateToken(@NonNull TokenId tokenId, @NonNull AccountId accountId, @NonNull PrivateKey accountKey)
            throws HieroException;

    /**
     * Associate an account with token.
     *
     * @param tokenId    the ID of the token
     * @param accountId  the ID of the account
     * @param accountKey the private key of the account
     * @throws HieroException if the account could not be associated with the token
     */
    default void associateToken(@NonNull TokenId tokenId, @NonNull String accountId, @NonNull String accountKey)
            throws HieroException {
        Objects.requireNonNull(accountId, "accountId must not be null");
        Objects.requireNonNull(accountKey, "accountKey must not be null");
        associateToken(tokenId, AccountId.fromString(accountId), PrivateKey.fromString(accountKey));
    }

    /**
     * Associate an account with token.
     *
     * @param tokenId the ID of the token
     * @param account the account
     * @throws HieroException if the account could not be associated with the token
     */
    default void associateToken(@NonNull TokenId tokenId, @NonNull Account account) throws HieroException {
        Objects.requireNonNull(account, "account must not be null");
        associateToken(tokenId, account.accountId(), account.privateKey());
    }

}
