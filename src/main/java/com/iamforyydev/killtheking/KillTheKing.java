package com.iamforyydev.killtheking;

import com.iamforyydev.killtheking.commands.Commands;
import com.iamforyydev.killtheking.game.Game;
import com.iamforyydev.killtheking.listener.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class KillTheKing extends JavaPlugin {

    private Game game;

    @Override
    public void onEnable() {
        game = new Game();

        commandHandler();
        eventHandler();

    }
    
    public Game getGame(){
        return this.game;
    }

    public void commandHandler(){
        getCommand("killtheking").setExecutor(new Commands());
    }

    public void eventHandler(){
        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
    }

    
}
