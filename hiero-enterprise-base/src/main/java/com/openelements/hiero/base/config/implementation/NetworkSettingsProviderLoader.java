package com.openelements.hiero.base.config.implementation;

import com.openelements.hiero.base.config.NetworkSettings;
import com.openelements.hiero.base.config.NetworkSettingsProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NetworkSettingsProviderLoader {

    private final static Logger logger = LoggerFactory.getLogger(NetworkSettingsProviderLoader.class);

    private final static NetworkSettingsProviderLoader instance = new NetworkSettingsProviderLoader();

    private final Set<NetworkSettings> settings;

    private NetworkSettingsProviderLoader() {
        final Set<NetworkSettings> loaded = new HashSet<>();
        ServiceLoader<NetworkSettingsProvider> loader = ServiceLoader.load(NetworkSettingsProvider.class);
        loader.stream().forEach(provider -> {
            final NetworkSettingsProvider networkSettingsProvider = provider.get();
            logger.info("Loading network settings from provider: {}", networkSettingsProvider.getName());
            final Set<NetworkSettings> networkSettingsFromProvider = networkSettingsProvider.createNetworkSettings();
            logger.debug("Loaded {} network settings from provider {}", networkSettingsFromProvider.size(),
                    networkSettingsProvider.getName());
            networkSettingsFromProvider.forEach(setting -> {
                if (loaded.stream().anyMatch(
                        existing -> Objects.equals(existing.getNetworkIdentifier(), setting.getNetworkIdentifier()))) {
                    throw new IllegalStateException(
                            "Network settings with identifier " + setting.getNetworkIdentifier() + " already loaded");
                } else {
                    loaded.add(setting);
                }
            });
        });
        this.settings = Collections.unmodifiableSet(loaded);
    }

    public Set<NetworkSettings> all() {
        return settings;
    }

    public Optional<NetworkSettings> forIdentifier(String identifier) {
        return all().stream().filter(settings -> Objects.equals(settings.getNetworkIdentifier(), identifier))
                .findFirst();
    }

    public static NetworkSettingsProviderLoader getInstance() {
        return instance;
    }
}
