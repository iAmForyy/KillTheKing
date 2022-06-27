package com.iamforyydev.killtheking.runnable;

import com.iamforyydev.killtheking.KillTheKing;
import com.iamforyydev.killtheking.game.Game;
import org.bukkit.scheduler.BukkitRunnable;

import static com.iamforyydev.killtheking.utils.Utils.getTime;
import static com.iamforyydev.killtheking.utils.Utils.sendBroadcast;

public class Countdown extends BukkitRunnable {

    private final KillTheKing plugin = KillTheKing.getPlugin(KillTheKing.class);
    private final Game game;
    private int countdown;

    public Countdown(Game game){
        this.game = game;
        this.countdown = 300;
    }

    public void startGame(){
        if(game.isEnabled()) return;
        runTaskTimer(plugin, 0, 20);
    }

    public void stopGame(){
        game.setEnabled(false);
        cancel();
        game.stop();

    }


    @Override
    public void run() {
        if(countdown <= 0){
            sendBroadcast("&cTime is up and no one managed to kill the king!");
            stopGame();
            return;
        }

        if(countdown == 240 ||countdown == 180 || countdown == 120 || countdown == 60 || countdown == 30 || countdown <= 10 ){
            sendBroadcast("&cThey have "+getTime(countdown)+" to assassinate the king! ");
        }


        countdown--;
    }
}
