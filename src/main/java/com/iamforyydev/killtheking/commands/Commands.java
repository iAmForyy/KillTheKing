package com.iamforyydev.killtheking.commands;

import com.iamforyydev.killtheking.KillTheKing;
import com.iamforyydev.killtheking.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Random;

import static com.iamforyydev.killtheking.utils.Utils.*;

public class Commands implements CommandExecutor {

    private final KillTheKing plugin = KillTheKing.getPlugin(KillTheKing.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage(c("&cWrong command, use /ktk help"));
            return true;
        }

        switch (args[0].toLowerCase()){
            default:
                player.sendMessage(c("&cWrong command, use /ktk help"));
                break;
            case "start":
                if(!player.hasPermission("killtheking.start")){
                    player.sendMessage(c("&cYou do not have permission to execute this command!"));
                    return true;
                }

                if(plugin.getGame().isEnabled()){
                    player.sendMessage(c("&cThe game is already started!"));
                    return true;
                }

                Player random = (Player) Bukkit.getOnlinePlayers().toArray()[new Random().nextInt(Bukkit.getOnlinePlayers().size())];

                Game game = plugin.getGame();
                game.start(random);

                Player king = game.getKing();
                Location location = king.getLocation();

                int x = (int) location.getX();
                int y = (int) location.getY();
                int z = (int) location.getZ();

                Arrays.asList(
                        "",
                        "&6&l      Kill The King &7v1.0.0",
                        "",
                        "&fThe game of assassinating the &6King &fhas begun",
                        "&fGo and kill him to get a big &6reward!",
                        "",
                        "&fKing: &6<king> &8| &fLocation: &ex: &6<x> &ey: &6<y> &ez: &6<z>",
                        "&e¡Good luck in your adventure!",
                        "").forEach(s -> sendBroadcast(s
                        .replace("<king>", king.getName())
                        .replace("<x>", Integer.toString(x))
                        .replace("<y>", Integer.toString(y))
                        .replace("<z>", Integer.toString(z))));
                sendSound(Sound.ENDERDRAGON_GROWL, 1, 1);
                break;
            case "help":
                Arrays.asList(
                        "",
                        "&6&l      Kill The King &7v1.0.0",
                        "",
                        "&fuse &e'/ktk start' &fto start the game",
                        "&fuse &e'/ktk help' &fto see the help menu",
                        "",
                        "&ePlugin development by iAmForyyDev_",
                        "").forEach(s -> player.sendMessage(c(s)));
                break;
        }
        return true;
    }
}
