package de.derredstoner.blockstop.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventory(InventoryClickEvent event) {
        if(event.getWhoClicked().getOpenInventory() == null || event.getWhoClicked().getOpenInventory().getTitle() == null) return;
        if(event.getWhoClicked().getOpenInventory().getTitle().contains("miners")) {
            event.setCancelled(true);
        }
    }

}
