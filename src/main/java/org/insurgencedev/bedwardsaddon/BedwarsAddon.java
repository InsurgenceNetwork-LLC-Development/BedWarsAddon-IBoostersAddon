package org.insurgencedev.bedwardsaddon;

import org.insurgencedev.bedwardsaddon.listeners.BedwarsEventListener;
import org.insurgencedev.insurgenceboosters.api.addon.IBoostersAddon;
import org.insurgencedev.insurgenceboosters.api.addon.InsurgenceBoostersAddon;

@IBoostersAddon(name = "BedwarsAddon", version = "1.0.2", author = "InsurgenceDev", description = "Bedwars1058 Support")
public class BedwarsAddon extends InsurgenceBoostersAddon {

    @Override
    public void onAddonReloadAblesStart() {
        registerEvent(new BedwarsEventListener());
    }
}
