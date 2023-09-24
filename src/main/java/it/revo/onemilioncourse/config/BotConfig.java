package it.revo.onemilioncourse.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BotConfig {
    //    String BOT_USERNAME = "web_3_new_jsp_bot";
    String BOT_USERNAME = "firstWebApp_bot";
    String BOT_TOKEN = "6480156096:AAHkSd4AqtyeJ7VC4k2qO3FkE2rHDq5_vyM";
    //    String BOT_TOKEN = "6154937793:AAET9G8JINyvH79I0kH8RebhZsfDQdS5nPc";
    List<String> getStartBtn = Arrays.asList("Qidirish", "Bo'limlar", "Savatcha", "Sevimlilar", "Sotib olinganlar", "Bot haqida");
    Map<String, String> IS = new HashMap<>();
}
