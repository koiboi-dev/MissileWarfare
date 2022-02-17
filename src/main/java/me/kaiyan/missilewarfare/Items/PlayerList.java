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
import me.kaiyan.missilewarfare.Translations;
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
            player.sendMessage(Translations.get("messages.playerlist.resetkey"));
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
                    event.getPlayer().sendMessage(Translations.get("messages.playerlist.addedkey")+cont.get(key, PersistentDataType.STRING)+"");
                    return;
                }
            }
        } catch (NullPointerException e){
            event.getPlayer().sendMessage(Translations.get("messages.playerlist.noid"));
            return;
        }
        if (!event.getSlimefunBlock().isPresent()) {
            Prompt askToWrite = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return Translations.get("messages.playerlist.askwriteprompt");
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    try {
                        List<OfflinePlayer> players = PlayerID.players.get(cont.get(key, PersistentDataType.STRING));
                        players.add(MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        PlayerID.players.put(cont.get(key, PersistentDataType.STRING), players);
                        conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.addedplayer") + MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        return END_OF_CONVERSATION;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.invalidplayer"));
                        return END_OF_CONVERSATION;
                    }
                }
            };
            Prompt askToRemove = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return Translations.get("messages.playerlist.removeplayer");
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    try {
                        List<OfflinePlayer> players = PlayerID.players.get(cont.get(key, PersistentDataType.STRING));
                        players.remove(MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        PlayerID.players.put(cont.get(key, PersistentDataType.STRING), players);
                        conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.removedplayer") + MissileWarfare.getInstance().getServer().getPlayerExact(s));
                        return END_OF_CONVERSATION;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.removeplayer"));
                        return END_OF_CONVERSATION;
                    }
                }
            };

            Prompt askToRead = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return Translations.get("messages.playerlist.asktoread");
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    String read = Translations.get("messages.playerlist.inputs.read");
                    String add = Translations.get("messages.playerlist.inputs.add");
                    String remove = Translations.get("messages.playerlist.inputs.remove");
                    if (s.equals(read)) {
                        String out = "Players: ";
                        List<String> players = new ArrayList<>();
                        for (OfflinePlayer player : PlayerID.players.get(cont.get(key, PersistentDataType.STRING))) {
                            players.add(player.getName());
                        }
                        out += players;
                        conversationContext.getForWhom().sendRawMessage(out);
                        return END_OF_CONVERSATION;
                    }else if (s.equals(add)) {
                        return askToWrite;
                    }else if (s.equals(remove)){
                        return askToRemove;
                    }
                    conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.incorrectinput"));
                    return END_OF_CONVERSATION;
                }
            };

            Prompt askForID = new StringPrompt() {
                @Override
                public String getPromptText(ConversationContext conversationContext) {
                    return Translations.get("messages.playerlist.askinputid");
                }

                @Override
                public Prompt acceptInput(ConversationContext conversationContext, String s) {
                    if (cont.get(key, PersistentDataType.STRING) != null) {
                        return askToRead;
                    }
                    if (PlayerID.players.get(s) == null) {
                        PlayerID.players.put(s, new ArrayList<>());
                        conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.createdid").replace("{id}", s));
                        cont.set(key, PersistentDataType.STRING, s);
                        event.getItem().setItemMeta(meta);
                        return END_OF_CONVERSATION;
                    } else {
                        conversationContext.getForWhom().sendRawMessage(Translations.get("messages.playerlist.gottenid"));
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
