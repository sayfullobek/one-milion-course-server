package it.revo.onemilioncourse.bot;

import it.revo.onemilioncourse.config.BotConfig;
import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.repository.rest.CategoryRepository;
import it.revo.onemilioncourse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Bot extends TelegramWebhookBot implements LongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private final CategoryRepository categoryRepository;
    private final ProductService productService;

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return null;
    }

    @Override
    public String getBotPath() {
        return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Methods methods = new Methods(categoryRepository, productService);
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();
            User from = message.getFrom();
            System.err.println(from);
            if (message.hasText()) {
                if (text.equals("/start")) {
                    methods.shareContactBtn(chatId, "Assalomu aleykum " + from.getFirstName() + " bizning botimizga hush kelibsiz iltmios kontaktingizni ulashing");
                } else if (text.equals("Bo'limlar")) {
                    methods.getCategory(chatId, "tanlang", categoryRepository.findAll());
                    BotConfig.IS.put(chatId, "category");
                } else if (BotConfig.IS.get(chatId).equals("category")) {
                    List<Product> products = productService.getProductByCategoryName(text);
                    methods.getProduct(chatId, "tanlang", products);
                    BotConfig.IS.replace(chatId, "product");
                } else if (text.equals("Orqaga") && BotConfig.IS.get(chatId).equals("product")) {
                    methods.getCategory(chatId, "tanlang", categoryRepository.findAll());
                    BotConfig.IS.replace(chatId, "category");
                } else if (text.equals("Orqaga") && BotConfig.IS.get(chatId).equals("category")) {
                    methods.getKeyboardBtnList(chatId, "Tanlang", BotConfig.getStartBtn);
                    BotConfig.IS.remove(chatId);
                }
            } else if (message.hasContact()) {
                Contact contact = message.getContact();
                methods.getKeyboardBtnList(chatId, "Tanlang", BotConfig.getStartBtn);
            }
        }
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {

    }
}
