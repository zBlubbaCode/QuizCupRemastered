package de.zblubba.quizcupremastered;

import de.zblubba.quizcupremastered.commands.*;
import de.zblubba.quizcupremastered.fragesystem.AddAntwortCommand;
import de.zblubba.quizcupremastered.fragesystem.CreateFrageCommand;
import de.zblubba.quizcupremastered.fragesystem.StartFrageCommand;
import de.zblubba.quizcupremastered.util.*;
import de.zblubba.quizcupremastered.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class QuizCupRemastered extends JavaPlugin {

    public static QuizCupRemastered instance;
    public static int taskid;

    public static boolean areConfigsLoaded = false;
    public static final String getPrefix = "§eQuizCup §8| §7";
    public static final String getFinalPrefix = "§c§lFinale §8| §7";
    public static final String geNoPerms = getPrefix + "§cDazu hast du keine Berechtigungen!";

    // Command / Listener functions
    public Scoreboard scoreboard = new Scoreboard();

    public static ArrayList<String> playerList = new ArrayList<>();
    public static ArrayList<String> allowedList = new ArrayList<>();
    public static boolean isServerClosed;

    // initial the files
    public static File configFile = new File("plugins/QuizCupRemastered", "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    public static File frageFile = new File("plugins/QuizCupRemastered", "fragesystem.yml");
    public static FileConfiguration fragen = YamlConfiguration.loadConfiguration(frageFile);

    public static File allowedPlayersFile = new File("plugins/QuizCupRemastered", "allowedplayers.yml");
    public static FileConfiguration playersConfig = YamlConfiguration.loadConfiguration(allowedPlayersFile);

    public static File winnerFile = new File("plugins/QuizCupRemastered", "winnerlist.yml");
    public static FileConfiguration winnerConfig = YamlConfiguration.loadConfiguration(winnerFile);

    public static File mysqlFile = new File("plugins/QuizCupRemastered", "mysql.yml");
    public static FileConfiguration mysqlConfig = YamlConfiguration.loadConfiguration(mysqlFile);

    @Override
    public void onEnable() {
        instance = this;

        //load all the stuff needed
        saveDefaultConfig();
        createFiles();
        loadConfigFiles();

        registerListeners();
        registerCommands();

        recreatePlayerList();
        checkInvis();

        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§7Product§8: §bQuizCup Remastered");
        Bukkit.getConsoleSender().sendMessage("§7Status: §8: §aActive");
        Bukkit.getConsoleSender().sendMessage("§7Developer§8: §bzBlubba");
        Bukkit.getConsoleSender().sendMessage("§7Version§8: §b" + QuizCupRemastered.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§7OS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§7Java-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§7Product§8: §bQuizCup Remastered");
        Bukkit.getConsoleSender().sendMessage("§7Status: §8: §cDeactivated");
        Bukkit.getConsoleSender().sendMessage("§7Developer§8: §bzBlubba");
        Bukkit.getConsoleSender().sendMessage("§7Version§8: §b" + QuizCupRemastered.getInstance().getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage("§7OS§8: §b" + System.getProperty("os.name"));
        Bukkit.getConsoleSender().sendMessage("§7Java-Version§8: §b" + System.getProperty("java.version"));
        Bukkit.getConsoleSender().sendMessage("");
        Bukkit.getConsoleSender().sendMessage("§8--------===========--------");
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new GeneralListeners(), this);
        pm.registerEvents(new InteractionListener(), this);
        pm.registerEvents(new MotdListener(), this);
        pm.registerEvents(new JoinQuitListener(), this);
    }
    public void registerCommands() {
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("mysql").setExecutor(new MySQLCommand());
        getCommand("createfrage").setExecutor(new CreateFrageCommand());
        getCommand("addantwort").setExecutor(new AddAntwortCommand());
        getCommand("startfrage").setExecutor(new StartFrageCommand());
    }


    public static void createFiles() {
        if(!QuizCupRemastered.configFile.exists() || !QuizCupRemastered.frageFile.exists() || !QuizCupRemastered.allowedPlayersFile.exists() || !QuizCupRemastered.winnerFile.exists() || !QuizCupRemastered.mysqlFile.exists()) {
            QuizCupRemastered.getInstance().getLogger().info("One or more files were not found. Creating...");
            if(!QuizCupRemastered.configFile.exists()) {
                QuizCupRemastered.configFile.getParentFile().mkdirs();
                QuizCupRemastered.getInstance().saveResource("config.yml", false);
            }
            if(!QuizCupRemastered.frageFile.exists()) {
                QuizCupRemastered.frageFile.getParentFile().mkdirs();
                QuizCupRemastered.getInstance().saveResource("fragesystem.yml", false);
            }
            if(!QuizCupRemastered.allowedPlayersFile.exists()) {
                QuizCupRemastered.allowedPlayersFile.getParentFile().mkdirs();
                QuizCupRemastered.getInstance().saveResource("allowedplayers.yml", false);
            }
            if(!QuizCupRemastered.winnerFile.exists()) {
                QuizCupRemastered.winnerFile.getParentFile().mkdirs();
                QuizCupRemastered.getInstance().saveResource("winnerlist.yml", false);
            }
            if(!QuizCupRemastered.mysqlFile.exists()) {
                QuizCupRemastered.mysqlFile.getParentFile().mkdirs();
                QuizCupRemastered.getInstance().saveResource("mysql.yml", false);
            }

        }
    }

    public static void loadConfigFiles() {
        QuizCupRemastered.getInstance().getLogger().info("Loading the config files...");
        try {
            QuizCupRemastered.config.load(configFile);
            QuizCupRemastered.fragen.load(frageFile);
            QuizCupRemastered.playersConfig.load(allowedPlayersFile);
            QuizCupRemastered.winnerConfig.load(winnerFile);
            QuizCupRemastered.mysqlConfig.load(mysqlFile);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        } catch (InvalidConfigurationException exception) {
            throw new RuntimeException(exception);
        }
    }

    public static void saveFile(File file, FileConfiguration configuration) {
        try {
            configuration.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void recreatePlayerList() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            if(!p.hasPermission("quizcup.helper") && !p.hasPermission("quizcup.admin")) {
                playerList.add(p.getName());
            }
        }
    }

    public static void checkInvis() {
        for(Player players : Bukkit.getOnlinePlayers()) {
            for(int i = 0; i < playerList.size(); i++) {
                Player p = Bukkit.getPlayer(playerList.get(i));
                if(!players.hasPermission("quizcup.helper")) {
                    p.hidePlayer(players);
                }
            }
        }

        if(config.getBoolean("server_closed")) {
            isServerClosed = true;
        }
    }



    public static QuizCupRemastered getInstance() {return instance;}
}
