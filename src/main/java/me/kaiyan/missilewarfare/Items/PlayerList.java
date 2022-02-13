package me.kaiyan.missilewarfare.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.kaiyan.missilewarfare.MissileWarfare;
import me.kaiyan.missilewarfare.PlayerID;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.conversations.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends SlimefunItem {
    public PlayerList(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        ItemUseHandler itemUseHandler = this::itemUse;
        addItemHandler(itemUseHandler);

        ItemDropHandler itemDropHandler = this::itemDrop;
        addItemHandler(itemDropHandler);
    }

    private boolean itemDrop(PlayerDropItemEvent event, Player player, Item item) {
        if (SlimefunItem.getByItem(item.getItemStack()) == null){
            return true;
        }
        if (!player.isSneaking() && SlimefunItem.getByItem(item.getItemStack()).getId().equals("PLAYERLIST")){
            event.setCancelled(true);

            NamespacedKey key = new NamespacedKey(MissileWarfare.getInstance(), "id");
            ItemMeta meta = item.getItemStack().getItemMeta();
            PersistentDataContainer cont = meta.getPersistentDataContainer();

            cont.remove(key);
            player.sendMessage("Reset Key");
            return false;
        }
        return true;
    }

    private void itemUse(PlayerRightClickEvent event) {
        NamespacedKey key = new NamespacedKey(MissileWarfare.getInstance(), "id");
        ItemMeta meta = event.getItem().getItemMeta();
        PersistentDataContainer cont = meta.getPersistentDataContainer();

        try{
            if (event.getSlimefunBlock().isPresent()){
                if (event.getSlimefunBlock().get().getId().equals("ANTIELYTRALAUNCHER")){
                    event.getPlayer().sendMessage("Added ID "+cont.get(key, PersistentDataType.STRING)+"");
                    return;
                }
            }
        } catch (NullPointerException e){
            event.getPlayer().sendMessage("Couldn't add ID to the launcher, No ID assigned to this PlayerList");
            return;
        }
        if (!event.getSlimefunBlock().isPresent()) {
            Prompt askToWrite = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return "What player would you like to add? Say exit to exit";
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    try {
                        List<OfflinePlayer> players = PlayerID.players.get(cont.get(key, PersistentDataType.STRING));
                        players.add(MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        PlayerID.players.put(cont.get(key, PersistentDataType.STRING), players);
                        conversationContext.getForWhom().sendRawMessage("Added player : " + MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        return END_OF_CONVERSATION;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        conversationContext.getForWhom().sendRawMessage("INVALID PLAYER");
                        return END_OF_CONVERSATION;
                    }
                }
            };
            Prompt askToRemove = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return "What player would you like to remove?";
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    try {
                        List<OfflinePlayer> players = PlayerID.players.get(cont.get(key, PersistentDataType.STRING));
                        players.remove(MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        PlayerID.players.put(cont.get(key, PersistentDataType.STRING), players);
                        conversationContext.getForWhom().sendRawMessage("Removed player : " + MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        return END_OF_CONVERSATION;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        conversationContext.getForWhom().sendRawMessage("INVALID PLAYER");
                        return END_OF_CONVERSATION;
                    }
                }
            };

            Prompt askToRead = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return "Say 'read' to see the players associated with this ID, Say 'add' to add a player, Say 'remove' to remove a player, Say 'exit' to exit";
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    switch (s) {
                        case "read":
                            String out = "Players: ";
                            List<String> players = new ArrayList<>();
                            for (OfflinePlayer player : PlayerID.players.get(cont.get(key, PersistentDataType.STRING))) {
                                players.add(player.getName());
                            }
                            out += players;
                            conversationContext.getForWhom().sendRawMessage(out);
                            return END_OF_CONVERSATION;
                        case "add":
                            return askToWrite;
                        case "remove":
                            return askToRemove;
                    }
                    conversationContext.getForWhom().sendRawMessage("Closing Edit Mode...");
                    return END_OF_CONVERSATION;
                }
            };

            Prompt askForID = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return "Input ID to create, say exit to leave";
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    if (cont.get(key, PersistentDataType.STRING) != null) {
                        return askToRead;
                    }
                    if (PlayerID.players.get(s) == null) {
                        PlayerID.players.put(s, new ArrayList<>());
                        conversationContext.getForWhom().sendRawMessage("Created ID : " + s + ", You can now write to it");
                        cont.set(key, PersistentDataType.STRING, s);
                        event.getItem().setItemMeta(meta);
                        return END_OF_CONVERSATION;
                    } else {
                        conversationContext.getForWhom().sendRawMessage("Gotten ID");
                        cont.set(key, PersistentDataType.STRING, s);
                        List<String> lore = meta.getLore();
                        lore.add("ID: " + s);
                        event.getItem().setItemMeta(meta);
                        return askToRead;
                    }
                }
            };

            if (cont.get(key, PersistentDataType.STRING) == null) {
                ConversationFactory cf = new ConversationFactory(MissileWarfare.getInstance());
                Conversation conversation = cf.withFirstPrompt(askForID)
                        .withLocalEcho(false)
                        .withEscapeSequence("exit")
                        .withTimeout(20)
                        .buildConversation(event.getPlayer());
                conversation.begin();
                event.cancel();
            } else {
                ConversationFactory cf = new ConversationFactory(MissileWarfare.getInstance());
                Conversation conversation = cf.withFirstPrompt(askToRead)
                        .withLocalEcho(false)
                        .withEscapeSequence("exit")
                        .withTimeout(20)
                        .buildConversation(event.getPlayer());
                conversation.begin();
                event.cancel();
            }
        }
    }
}
