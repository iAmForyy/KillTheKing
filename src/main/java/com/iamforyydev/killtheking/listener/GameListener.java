package com.iamforyydev.killtheking.listener;

import com.iamforyydev.killtheking.KillTheKing;
import com.iamforyydev.killtheking.game.Game;
import com.iamforyydev.killtheking.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Arrays;

import static com.iamforyydev.killtheking.utils.Utils.sendSound;

public class GameListener implements Listener {

    private final KillTheKing plugin = KillTheKing.getPlugin(KillTheKing.class);

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player player = e.getEntity().getPlayer();
        if(!plugin.getGame().isEnabled()) return;

        Game game = plugin.getGame();
        if(player != game.getKing()) return;

        Player king = game.getKing();
        Player killer = player.getKiller();

        if(killer == null) {
            plugin.getGame().getCountdown().stopGame();
            return;
        }

        king.spigot().respawn();

        killer.sendMessage(Utils.toLegacyColor("&eThe player &6"+killer.getName()+" &ehas won the game!"));
        sendSound(Sound.ENDERDRAGON_DEATH, 1, 1);

        Arrays.asList(
                "give <player> diamond 1",
                "give <player> stone 1").forEach(s -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("<player>", killer.getName())));

        plugin.getGame().getCountdown().stopGame();

    }



}
