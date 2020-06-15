package pixelium.core;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Objects;

public class Core extends JavaPlugin implements Listener {

    private static final String OTHERWORLD_NAME = "otherworld";
    private static World otherworld;

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        loadRecipes();
        if (getServer().getWorld(OTHERWORLD_NAME) == null) {
            otherworld = new WorldCreator(OTHERWORLD_NAME)
                    .generator(new Otherworld())
                    .environment(World.Environment.NORMAL)
                    .createWorld();
        } else otherworld = getServer().getWorld(OTHERWORLD_NAME);
    }

    public final static String uraniumPickaxeName = ChatColor.GREEN + "Uranium Pickaxe";

    private void loadRecipes() {
        {
            ItemStack saddle = new ItemStack(Material.SADDLE);
            NamespacedKey key = new NamespacedKey(this, "saddle");
            ShapelessRecipe recipe = new ShapelessRecipe(key, saddle);
            recipe.addIngredient(6, Material.LEATHER);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack nethersoup = new ItemStack(Material.BEETROOT_SOUP);
            ItemMeta meta = nethersoup.getItemMeta();
            Objects.requireNonNull(meta).setDisplayName(ChatColor.RESET + "Nether Wart Soup");
            nethersoup.setItemMeta(meta);
            NamespacedKey key = new NamespacedKey(this, "nether_soup");
            ShapelessRecipe recipe = new ShapelessRecipe(key, nethersoup);
            recipe.addIngredient(3, Material.NETHER_WART);
            recipe.addIngredient(Material.BOWL);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack elytra = new ItemStack(Material.ELYTRA);
            NamespacedKey key = new NamespacedKey(this, "elytra");
            ShapedRecipe recipe = new ShapedRecipe(key, elytra);
            recipe.shape("rdr", "m m", "m m");
            recipe.setIngredient('r', Material.END_ROD);
            recipe.setIngredient('d', Material.DIAMOND);
            recipe.setIngredient('m', Material.PHANTOM_MEMBRANE);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack redsand = new ItemStack(Material.RED_SAND);
            NamespacedKey key = new NamespacedKey(this, "red_sand");
            ShapelessRecipe recipe = new ShapelessRecipe(key, redsand);
            recipe.addIngredient(Material.SAND);
            recipe.addIngredient(Material.RED_DYE);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack nametag = new ItemStack(Material.NAME_TAG);
            NamespacedKey key = new NamespacedKey(this, "name_tag");
            ShapedRecipe recipe = new ShapedRecipe(key, nametag);
            recipe.shape("   ", "p  ", "s  ");
            recipe.setIngredient('p', Material.PAPER);
            recipe.setIngredient('s', Material.STRING);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack ultimateWeapon = new ItemStack(Material.GOLDEN_AXE);
            ItemMeta meta = ultimateWeapon.getItemMeta();
            meta.addEnchant(Enchantment.FIRE_ASPECT, 1, true);
            meta.addEnchant(Enchantment.DIG_SPEED, 7, true);
            meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 2, true);
            meta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, true);
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            meta.setDisplayName(ChatColor.GREEN + "Uranium Axe");
            ultimateWeapon.setItemMeta(meta);
            NamespacedKey key = new NamespacedKey(this, "uranium_axe");
            ShapedRecipe recipe = new ShapedRecipe(key, ultimateWeapon);
            recipe.shape("aa ", "al ", " l ");
            recipe.setIngredient('a', Material.DIAMOND_BLOCK);
            recipe.setIngredient('l', Material.BLAZE_ROD);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack ultimatePick = new ItemStack(Material.GOLDEN_PICKAXE);
            ItemMeta meta = ultimatePick.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED, 4, true);
            meta.addEnchant(Enchantment.SILK_TOUCH, 1, true);
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            meta.setDisplayName(uraniumPickaxeName);
            ultimatePick.setItemMeta(meta);
            NamespacedKey key = new NamespacedKey(this, "uranium_pickaxe");
            ShapedRecipe recipe = new ShapedRecipe(key, ultimatePick);
            recipe.shape("aaa", " l ", " l ");
            recipe.setIngredient('a', Material.DIAMOND_BLOCK);
            recipe.setIngredient('l', Material.BLAZE_ROD);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack trident = new ItemStack(Material.TRIDENT);
            NamespacedKey key = new NamespacedKey(this, "trident");
            ShapedRecipe recipe = new ShapedRecipe(key, trident);
            recipe.shape("www", "dld", " l ");
            recipe.setIngredient('w', Material.PRISMARINE_WALL);
            recipe.setIngredient('d', Material.DIAMOND);
            recipe.setIngredient('l', Material.BLAZE_ROD);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack infinityShovel = new ItemStack(Material.DIAMOND_SHOVEL);
            ItemMeta meta = infinityShovel.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Infinity Shovel");
            meta.addEnchant(Enchantment.DIG_SPEED, 8, true);
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            infinityShovel.setItemMeta(meta);
            NamespacedKey key = new NamespacedKey(this, "infinity_shovel");
            ShapedRecipe recipe = new ShapedRecipe(key, infinityShovel);
            recipe.shape("ddd", "dsd", "ddd");
            recipe.setIngredient('d', Material.DIAMOND);
            recipe.setIngredient('s', Material.DIAMOND_SHOVEL);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack infinityHoe = new ItemStack(Material.DIAMOND_HOE);
            ItemMeta meta = infinityHoe.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Infinity Hoe");
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
            infinityHoe.setItemMeta(meta);
            NamespacedKey key = new NamespacedKey(this, "infinity_hoe");
            ShapedRecipe recipe = new ShapedRecipe(key, infinityHoe);
            recipe.shape("ddd", "dsd", "ddd");
            recipe.setIngredient('d', Material.DIAMOND);
            recipe.setIngredient('s', Material.DIAMOND_HOE);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack blackDye = new ItemStack(Material.BLACK_DYE);
            NamespacedKey key = new NamespacedKey(this, "carbon_black_dye");
            ShapelessRecipe recipe = new ShapelessRecipe(key, blackDye);
            recipe.addIngredient(Material.COAL);
            Bukkit.addRecipe(recipe);
        } {
            ItemStack blackDye = new ItemStack(Material.BLACK_DYE);
            NamespacedKey key = new NamespacedKey(this, "charcoal_black_dye");
            ShapelessRecipe recipe = new ShapelessRecipe(key, blackDye);
            recipe.addIngredient(Material.CHARCOAL);
            Bukkit.addRecipe(recipe);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (cmd.getName()) {
                case "rules": {
                    player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "RULES:");
                    player.sendMessage(ChatColor.GRAY + " - No hacked clients or auto-clickers");
                    player.sendMessage(ChatColor.GRAY + " - It's good to use Optifine and other quality of life mods");
                    player.sendMessage(ChatColor.GRAY + " - Be respectful!");
                    return true;
                }
                case "biome": {
                    if (player.isOp()) {
                        Location location = player.getLocation();
                        Biome biome;
                        try {
                            biome = Biome.valueOf(args[0].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            player.sendMessage(ChatColor.RED + args[0] + " isn't a valid biome name ;(");
                            return false;
                        }
                        int radius = 5;
                        try { radius = Integer.parseInt(args[1]); }
                        catch (IllegalArgumentException e) {
                            player.sendMessage(ChatColor.RED + args[1] + " isn't a valid number!");
                            return false;
                        } catch (IndexOutOfBoundsException ignore) {}
                        if (radius > 128) {
                            player.sendMessage(ChatColor.RED + "Maximum radius is 128!");
                            return true;
                        }
                        location.add(-radius, -radius, -radius);
                        ArrayList<Chunk> chunksToSend = new ArrayList<>();
                        for (int x = 0; x < radius << 1; x++) {
                            for (int z = 0; z < radius << 1; z++) {
                                location.getBlock().setBiome(biome);
                                if (!chunksToSend.contains(location.getChunk()))
                                    chunksToSend.add(location.getChunk());
                                location.add(0, 0, 1);
                            }
                            location.add(1, 0, -radius << 1);
                        }
                    } else player.sendMessage(ChatColor.RED + "Only the operator can change biomes!");
                    return true;
                }
                case "spawntraveller": if (player.isOp()) {
                    Villager traveller = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
                    traveller.setCustomName(theTravelerName);
                    traveller.setAI(false);
                    traveller.setGravity(false);
                    traveller.setInvulnerable(true);
                    traveller.setProfession(Villager.Profession.CLERIC);
                    traveller.setGlowing(true);
                    return true;
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        e.setMessage(e.getMessage()
                .replaceAll(":red:", ChatColor.RED+"")
                .replaceAll(":green:", ChatColor.GREEN+"")
                .replaceAll(":blue:", ChatColor.BLUE+"")
                .replaceAll(":yellow:", ChatColor.YELLOW+"")
                .replaceAll(":pink:", ChatColor.LIGHT_PURPLE+"")
                .replaceAll(":cyan:", ChatColor.AQUA+"")
                .replaceAll(":orange:", ChatColor.GOLD+"")
                .replaceAll(":grey:", ChatColor.DARK_GRAY +"")
                .replaceAll(":black:", ChatColor.BLACK+"")
                .replaceAll(":bold:", ChatColor.BOLD+"")
                .replaceAll(":italic:", ChatColor.ITALIC+"")
                .replaceAll(":magic:", ChatColor.MAGIC+"")
                .replaceAll(":r:", ChatColor.RESET+""));
        e.setFormat(((e.getPlayer().isOp()) ? ChatColor.AQUA : ChatColor.GREEN) + "" + ChatColor.BOLD + e.getPlayer().getName() + ": " + ChatColor.RESET + e.getMessage());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.GREEN + "" + ChatColor.BOLD + player.getName() + ChatColor.RESET + " joined the server");
        if (!player.hasPlayedBefore()) {
            player.sendMessage(ChatColor.BLUE + "" + ChatColor.BOLD + "RULES:");
            player.sendMessage(ChatColor.GRAY + " - No hacked clients or auto-clickers");
            player.sendMessage(ChatColor.GRAY + " - It's good to use Optifine and other quality of life mods");
            player.sendMessage(ChatColor.GRAY + " - Be respectful!");
        }
        setAttackSpeed(player, PLAYER_ATTACK_SPEED);
    }

    @EventHandler public void onPlayerWorldChange(PlayerChangedWorldEvent e) { setAttackSpeed(e.getPlayer(), PLAYER_ATTACK_SPEED); }
    @EventHandler public void onPlayerQuit(PlayerQuitEvent e) { setAttackSpeed(e.getPlayer(), 4); }

    public static final double PLAYER_ATTACK_SPEED = 12;
    void setAttackSpeed(Player player, double speed) {
        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        double baseValue = attribute.getBaseValue();
        if(baseValue != speed){
            attribute.setBaseValue(speed);
            player.saveData();
        }
    }

    @EventHandler
    public void onEntityDamaged(EntityDamageByEntityEvent e){
        if(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK.equals(e.getCause()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        switch (e.getBlock().getType()) {
            case IRON_BLOCK: {
                e.getPlayer().setFoodLevel(e.getPlayer().getFoodLevel() - 5);
                break;
            } case SPAWNER: {
                e.setDropItems(false);
                e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                e.setExpToDrop(5);
                break;
            } default: {
                ItemStack heldItem = e.getPlayer().getInventory().getItemInMainHand();
                if (uraniumPickaxeName.equals(heldItem.getItemMeta().getDisplayName()) && heldItem.getType() == Material.GOLDEN_PICKAXE) {
                    switch (e.getBlock().getType()) {
                        case COAL_ORE:
                        case DIAMOND_ORE:
                        case EMERALD_ORE:
                        case GOLD_ORE:
                        case IRON_ORE:
                        case LAPIS_ORE:
                        case NETHER_QUARTZ_ORE:
                        case REDSTONE_ORE:
                        case OBSIDIAN: {
                            e.setDropItems(false);
                            e.getBlock().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(e.getBlock().getType()));
                            break;
                        }
                    }
                } break;
            }
        }
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        e.setDroppedExp((int)(e.getDroppedExp() * (1 + Math.random()*2)));
        if (entity instanceof Bat) {
            e.getDrops().clear();
            e.setDroppedExp(0);
            ItemStack batmeat = new ItemStack(Material.ROTTEN_FLESH);
            ItemMeta meta = batmeat.getItemMeta();
            Objects.requireNonNull(meta).setDisplayName(ChatColor.RESET + "Bat Meat");
            batmeat.setItemMeta(meta);
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), batmeat);
        } else if (entity instanceof Illusioner) {
            e.getDrops().clear();
            e.setDroppedExp(36);
            ItemStack diamonds = new ItemStack(Material.DIAMOND, 1 + (int)(Math.random()*2));
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), diamonds);
            ItemStack enderpearls = new ItemStack(Material.ENDER_PEARL, 1 + (int)(Math.random()*2));
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), enderpearls);
            ItemStack gunpowder = new ItemStack(Material.GUNPOWDER, 1 + (int)(Math.random()*22));
            Objects.requireNonNull(entity.getLocation().getWorld()).dropItem(entity.getLocation(), gunpowder);
        } else if (entity instanceof Turtle) {
            Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), new ItemStack(Material.SCUTE, 1 + (int)(Math.random()*1)));
        } else if (entity instanceof Shulker) {
            e.getDrops().clear();
            Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), new ItemStack(Material.SHULKER_SHELL, 2));
        } else if (entity instanceof Enderman && (int)(Math.random()*10) == 0) {
            Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), new ItemStack(Material.SHULKER_SHELL, (int)(Math.random()*2) + 1));
        } else if (entity instanceof Drowned) {
            Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), new ItemStack(Material.PRISMARINE_CRYSTALS, 1 + (int)(Math.random()*2)));
        } else if (entity instanceof Witch) {
            if ((int)(Math.random() * 8) == 0) {
                ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta enchantedBookMeta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
                enchantedBookMeta.addStoredEnchant(Enchantment.DURABILITY, 6, true);
                enchantedBook.setItemMeta(enchantedBookMeta);
                Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), enchantedBook);
            } else if ((int)(Math.random() * 12) == 0) {
                ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta enchantedBookMeta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
                enchantedBookMeta.addStoredEnchant(Enchantment.DIG_SPEED, 5, true);
                enchantedBook.setItemMeta(enchantedBookMeta);
                Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), enchantedBook);
            } else if ((int)(Math.random() * 5) == 0) {
                ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
                EnchantmentStorageMeta enchantedBookMeta = (EnchantmentStorageMeta) enchantedBook.getItemMeta();
                enchantedBookMeta.addStoredEnchant(Enchantment.KNOCKBACK, 4, true);
                enchantedBook.setItemMeta(enchantedBookMeta);
                Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), enchantedBook);
            }
        } else if (entity instanceof WitherSkeleton && (int)(Math.random() * 6) == 0) {
            e.getDrops().clear();
            Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), new ItemStack(Material.WITHER_SKELETON_SKULL));
        } else if (entity instanceof Skeleton && (int)(Math.random() * 6) == 0) {
            e.getDrops().clear();
            Objects.requireNonNull(e.getEntity().getLocation().getWorld()).dropItem(e.getEntity().getLocation(), new ItemStack(Material.SKELETON_SKULL));
        }
    }

    private int playersSleeping = 0;
    @EventHandler public void onBedLeave(PlayerBedLeaveEvent e) { playersSleeping--; }
    @EventHandler public void onBedEnter(PlayerBedEnterEvent e) {
        if (e.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            playersSleeping++;
            if (playersSleeping >= getServer().getOnlinePlayers().size() / 2)
                e.getBed().getWorld().setTime(0);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (OTHERWORLD_NAME.equals(e.getPlayer().getWorld().getName()))
            e.setRespawnLocation(otherworld.getSpawnLocation());
    }

    @EventHandler
    public void onMobSpawn(EntitySpawnEvent e) {
        if (OTHERWORLD_NAME.equals(e.getLocation().getWorld().getName())) switch (e.getEntityType()) {
            case CREEPER: {
                switch ((int)(Math.random() * 5)) {
                    case 0: {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.ENDERMAN);
                        e.setCancelled(true);
                        break;
                    } case 1: if ((int) (Math.random() * 6) == 0) {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.ENDERMITE);
                        e.setCancelled(true);
                        break;
                    } case 2: if ((int) (Math.random() * 48) == 0) {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.BLAZE);
                        e.setCancelled(true);
                        break;
                    } case 3: if ((int)(Math.random() * 25) == 0) {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.ILLUSIONER);
                        e.setCancelled(true);
                        break;
                    } default: e.setCancelled(true);
                }
                break;
            } case SKELETON: {
                Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.ENDERMAN);
                e.setCancelled(true);
                break;
            }
            case ZOMBIE: e.setCancelled(true); break;
        } else switch (e.getEntityType()) {
            case CREEPER: {
                switch ((int)(Math.random()*5)) {
                    case 0: if ((int) (Math.random() * 4) == 0) {
                        Skeleton skeleton = (Skeleton) Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.SKELETON);
                        e.setCancelled(true);
                        ItemStack bow = new ItemStack(Material.BOW);
                        ItemMeta bowMeta = bow.getItemMeta();
                        bowMeta.setDisplayName(ChatColor.GOLD + "Fire Bow");
                        bowMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
                        bowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
                        bowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        bow.setItemMeta(bowMeta);
                        skeleton.getEquipment().setItemInMainHand(bow);
                        skeleton.getEquipment().setItemInMainHandDropChance(0.9f);
                        break;
                    } case 1: if ((int) (Math.random() * 12) == 0) {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.WITCH);
                        e.setCancelled(true);
                        break;
                    } case 3: if ((int)(Math.random() * 30) == 0) {
                        Sheep sheep = (Sheep) Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getEntity().getLocation(), EntityType.SHEEP);
                        sheep.setColor(DyeColor.CYAN);
                        e.setCancelled(true);
                        break;
                    } default: e.setCancelled(true);
                }
                break;
            } case PIG_ZOMBIE: {
                switch ((int) (Math.random() * 40)) {
                    case 0:
                    case 1:
                    case 2: {
                        for (int i = (int) (Math.random() * 6); i != 0; i--) {
                            Bat bat = (Bat) Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getLocation(), EntityType.BAT);
                            e.setCancelled(true);
                            bat.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 999999, 330, false, false));
                            bat.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 999999, 330, false, false));
                        } break;
                    } case 8: {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getLocation(), EntityType.SPIDER);
                        e.setCancelled(true);
                        break;
                    } case 9: {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getLocation(), EntityType.PIG);
                        e.setCancelled(true);
                        break;
                    } case 10: if ((int) (Math.random() * 30) == 0) {
                        Objects.requireNonNull(e.getEntity().getLocation().getWorld()).spawnEntity(e.getLocation(), EntityType.ZOMBIE_HORSE);
                        e.setCancelled(true);
                        break;
                    }
                }
                break;
            }
        }
    }

    public final static String theTravelerName = ChatColor.GREEN + "The traveler";

    @EventHandler
    public void onEntityRightClick(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.VILLAGER && theTravelerName.equals(e.getRightClicked().getCustomName())) {
            if (OTHERWORLD_NAME.equals(e.getPlayer().getWorld().getName()))
                e.getPlayer().teleport(getServer().getWorld("world").getSpawnLocation());
            else e.getPlayer().teleport(otherworld.getSpawnLocation());
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent e) {
        if (e.getViewers().isEmpty()) return;
        for (HumanEntity viewer : e.getViewers()) {
            e.getInventory().setRepairCost(Math.min(e.getInventory().getRepairCost(), 30));
            ((Player) viewer).updateInventory();
        }
    }
}
