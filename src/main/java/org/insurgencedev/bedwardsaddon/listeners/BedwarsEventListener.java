package org.insurgencedev.bedwardsaddon.listeners;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.insurgencedev.insurgenceboosters.api.IBoosterAPI;
import org.insurgencedev.insurgenceboosters.models.booster.GlobalBoosterManager;
import org.insurgencedev.insurgenceboosters.settings.IBoostersPlayerCache;

public final class BedwarsEventListener implements Listener {

    @EventHandler
    public void onReceive(PlayerXpGainEvent event) {
        String type = "XP";
        final String NAMESPACE = "BEDWARS";
        double totalMulti = 1;
        Player player = event.getPlayer();

        IBoostersPlayerCache.BoosterFindResult pResult = IBoosterAPI.getCache(player).findActiveBooster(type, NAMESPACE);
        if (pResult instanceof IBoostersPlayerCache.BoosterFindResult.Success boosterResult) {
            totalMulti += boosterResult.getBooster().getMultiplier();
        }

        GlobalBoosterManager.BoosterFindResult gResult = IBoosterAPI.getGlobalBoosterManager().findBooster(type, NAMESPACE);
        if (gResult instanceof GlobalBoosterManager.BoosterFindResult.Success boosterResult) {
            totalMulti += boosterResult.getBooster().getMultiplier();
        }

        int amount = (int) (calculateAmount(event.getAmount(), totalMulti) - event.getAmount());
        BedWars.getAPI().getLevelsUtil().addXp(player, amount, PlayerXpGainEvent.XpSource.OTHER);
    }

    private long calculateAmount(double amount, double multi) {
        return (long) (amount * (multi < 1 ? 1 + multi : multi));
    }
}
