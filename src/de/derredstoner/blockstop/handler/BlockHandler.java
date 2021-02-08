package de.derredstoner.blockstop.handler;

import de.derredstoner.blockstop.mysql.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BlockHandler {

    private static List<String> topbroken = new LinkedList<>();
    private static Map<String,Integer> topbrokenamount = new ConcurrentHashMap<>();

    public static void calculateTopBroken() {
        List<Integer> brokenBlocks = new ArrayList<>();
        Map<Integer,List<String>> players = new ConcurrentHashMap<>();

        topbroken.clear();
        topbrokenamount.clear();

        try {
            ResultSet resultSet = MySQL.getEntries();

            while(resultSet.next() != false) {
                int blocksMined = resultSet.getInt("blocksmined");
                brokenBlocks.add(blocksMined);
                List<String> prevPlayers;
                if(players.containsKey(blocksMined)) {
                    prevPlayers = players.get(blocksMined);
                } else {
                    prevPlayers = new ArrayList<>();
                }
                prevPlayers.add(resultSet.getString("uuid"));
                players.put(blocksMined, prevPlayers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(brokenBlocks);
        int size = brokenBlocks.size();

        for(int i = size-1; i > size-11; i--) {
            if(size == 0 || i >= size || i < 0 || topbroken.size() >= 10) {
                return;
            }
            int blocksBrokenValue = brokenBlocks.get(i);
            List<String> uuidList = players.get(blocksBrokenValue);
            for(String uuid : uuidList) {
                String playername = Bukkit.getServer().getOfflinePlayer(UUID.fromString(uuid)).getName();
                topbroken.add(playername);
                topbrokenamount.put(playername, blocksBrokenValue);
            }
        }
    }

    public static List<String> getTopPlayers() {
        return topbroken;
    }

    public static int getBlocksBroken(String playername) {
        return topbrokenamount.get(playername);
    }

}
