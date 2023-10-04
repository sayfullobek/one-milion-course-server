package it.revo.onemilioncourse.config;

import it.revo.onemilioncourse.entity.Location;

import java.util.*;

public interface BotConfig {
    //    String BOT_USERNAME = "web_3_new_jsp_bot";
    String BOT_USERNAME = "firstWebApp_bot";
    String BOT_TOKEN = "6480156096:AAHkSd4AqtyeJ7VC4k2qO3FkE2rHDq5_vyM";
    //    String BOT_TOKEN = "6154937793:AAET9G8JINyvH79I0kH8RebhZsfDQdS5nPc";
    List<String> getStartBtn = Arrays.asList("Qidirish", "Bo'limlar", "Savatcha", "Sevimlilar", "Sotib olinganlar", "Bot haqida");
    List<String> CONFIRM = Arrays.asList("Xa", "Yo'q");
    Map<String, String> IS = new HashMap<>();
    Map<String, UUID> productId = new HashMap<>();
    Map<String, Integer> SIZE = new HashMap<>();
    Map<String, Location> LOCATIONS = new HashMap<>();
}
