package de.derredstoner.blockstop.commands;

import de.derredstoner.blockstop.BlocksTop;
import de.derredstoner.blockstop.handler.BlockHandler;
import de.derredstoner.blockstop.handler.ChatHandler;
import de.derredstoner.blockstop.handler.GuiHandler;
import de.derredstoner.blockstop.mysql.MySQL;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BlocksTopCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("force")) {
                if(sender.hasPermission("bt.force")) {
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §bUpdating top players...");
                    BlockHandler.calculateTopBroken();
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §bSuccessfully updated!");
                } else {
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §cYou are lacking the permission bt.force");
                }
            } else if(args[0].equalsIgnoreCase("reload")) {
                if(sender.hasPermission("bt.reload")) {
                    MySQL.closeConnection();
                    MySQL.connect();
                    BlocksTop.getInstance().reloadConfig();
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §bSuccessfully reloaded!");
                } else {
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §cYou are lacking the permission bt.reload");
                }
            } else {
                sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §cThat command does not exist.");
            }
        } else {
            if(sender instanceof Player) {
                if(sender.hasPermission("bt.top")) {
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §bOpening GUI...");
                    GuiHandler.sendMenu((Player) sender);
                } else {
                    sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §cYou are lacking the permission bt.top");
                }
            } else {
                sender.sendMessage(ChatHandler.color(BlocksTop.getInstance().getConfig().getString("prefix"))+" §cThis command can only be executed ingame.");
            }
        }

        return false;
    }

}
