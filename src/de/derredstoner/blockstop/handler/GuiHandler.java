package de.derredstoner.blockstop.handler;

import jdk.nashorn.internal.ir.Block;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GuiHandler {

    private static Integer[] positions = {4, 12, 14, 19, 20, 21, 22, 23, 24, 25};

    public static void sendMenu(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§b§lTop miners");

        int topPlayersSize = BlockHandler.getTopPlayers().size();
        for(int i = 0; i < topPlayersSize; i++) {
            int position = positions[i];

            String playername = BlockHandler.getTopPlayers().get(i);
            int blocksBroken = BlockHandler.getBlocksBroken(playername);
            ItemStack skull = getSkull(playername, i+1, blocksBroken);

            inventory.setItem(position, skull);
        }

        for(int i = 0; i < inventory.getSize(); i++) {
            boolean isLeaderboard = false;
            for(int position : positions) {
                if(position == i) {
                    isLeaderboard = true;
                }
            }
            if(!isLeaderboard) {
                inventory.setItem(i, getItem(Material.STAINED_GLASS_PANE, (byte) 3, 1));
            }
        }

        player.openInventory(inventory);
    }

    public static ItemStack getSkull(String playername, int position, int blocksBroken) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1 , (byte)3);
        SkullMeta itemMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);

        itemMeta.setOwner(playername);
        itemMeta.setDisplayName("§3§lTop #"+position);

        List<String> lore = new ArrayList<>();
        lore.add("§f");
        lore.add("§ePlayer: "+playername);
        lore.add("§eBlocks broken: "+blocksBroken);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack getItem(Material material, byte data, int amount) {
        ItemStack itemStack = new ItemStack(material, amount , data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§f");
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
