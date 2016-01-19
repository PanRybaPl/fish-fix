/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.panryba.mc.fix;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 *
 * @author PanRyba.pl
 */
class FixListener implements Listener {
   
    
    @EventHandler
    public void onPlayerStrenght(PlayerInteractEvent event)
    {
        if(event == null) {
            return;
        }
        
        Player player = event.getPlayer();
        
        if(player == null) {
            return;
        }
        
        if(!event.getAction().equals(Action.RIGHT_CLICK_AIR) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            return;
        }
        
        ItemStack item = event.getItem();
        if(item == null) {
            return;
        }
        
        if(!item.getType().equals(Material.POTION)) {
            return;
        }
        
        if(item.getDurability() != 8233) {
            return;
        }        
        
        event.setCancelled(true);
        player.sendMessage(ChatColor.RED + "Napoje sily II zostaly wylaczone na serwerze");
    }
    
    @EventHandler
    public void onPotionSplash(PotionSplashEvent event)
    {
        if(event == null) {
            return;
        }
        
        if(event.isCancelled()) {
            return;
        }
        
        ThrownPotion potion = event.getPotion();
        if(potion == null)
            return;
        
        for(PotionEffect effect : potion.getEffects()) {
            if(effect.getType().equals(PotionEffectType.INCREASE_DAMAGE) && effect.getAmplifier() >= 1) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
