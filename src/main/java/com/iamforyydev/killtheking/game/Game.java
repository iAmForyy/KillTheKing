package com.iamforyydev.killtheking.game;

import com.iamforyydev.killtheking.runnable.Countdown;
import org.bukkit.entity.Player;

public class Game {

    private Player king;
    private boolean enabled;
    private Countdown countdown;

    public Game(){
        this(null);
    }

    public Game(Player king){
        this.king = king;
        this.enabled = false;
        this.countdown = new Countdown(this);
    }

    public void start(Player player){
        getCountdown().startGame();
        this.setEnabled(true);
        this.setKing(player);
    }

    public void stop(){
        this.countdown = new Countdown(this);
    }


    public Player getKing(){
        return this.king;
    }

    public void setKing(Player player){
        this.king = player;
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public Countdown getCountdown() {
        return countdown;
    }
}
