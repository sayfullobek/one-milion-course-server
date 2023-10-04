package it.revo.onemilioncourse.bot;

import it.revo.onemilioncourse.config.BotConfig;
import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.exception.ResourceNotFoundException;
import it.revo.onemilioncourse.payload.ApiResponse;
import it.revo.onemilioncourse.repository.RoleRepository;
import it.revo.onemilioncourse.repository.UserRepository;
import it.revo.onemilioncourse.repository.rest.CategoryRepository;
import it.revo.onemilioncourse.service.AttachmentService;
import it.revo.onemilioncourse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class Bot extends TelegramWebhookBot implements LongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(Bot.class);
    private final CategoryRepository categoryRepository;
    private final ProductService productService;
    private final AttachmentService attachmentService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
        Methods methods = new Methods(categoryRepository, productService, attachmentService, userRepository, roleRepository);
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String text = message.getText();
            User from = message.getFrom();
            System.err.println(from);
            if (message.hasText()) {
                if (text.equals("/start")) {
                    methods.shareContactBtn(chatId, "Assalomu aleykum " + from.getFirstName() + " bizning botimizga hush kelibsiz iltmios kontaktingizni ulashing");
                } else if (text.equals("Qidirish")) {
                    methods.sendMsg(chatId, "Qidirayotgan mahsulotingizni kiriting");
                    BotConfig.IS.put(chatId, "search");
                } else if (text.equals("Savatcha")) {
                    List<Product> baskets = userRepository.findUserByChatId(Long.parseLong(chatId)).getBaskets();
                    if (baskets.size() == 0) {
                        methods.sendMsg(chatId, "Savatcha bo'sh...");
                    } else {
                        methods.sendSearch(chatId, baskets, attachmentService);
                    }
                } else if (text.equals("Sevimlilar")) {
                    List<Product> likeProducts = userRepository.findUserByChatId(Long.parseLong(chatId)).getLikeProducts();
                    if (likeProducts.size() == 0) {
                        methods.sendMsg(chatId, "Sevimlilar bo'sh...");
                    } else {
                        methods.sendSearch(chatId, likeProducts, attachmentService);
                    }
                } else if (text.equals("Bo'limlar")) {
                    methods.getCategory(chatId, "tanlang", categoryRepository.findAll());
                    BotConfig.IS.put(chatId, "category");
                } else if (BotConfig.IS.get(chatId).equals("confirm") && text.equals("Xa")) {
                    ApiResponse<?> apiResponse = productService.buyProduct(Long.parseLong(chatId), BotConfig.productId.get(chatId), BotConfig.SIZE.get(chatId), BotConfig.LOCATIONS.get(chatId).getLatitude(), BotConfig.LOCATIONS.get(chatId).getLongitude());
                    methods.getKeyboardBtnList(chatId, apiResponse.getMessage(), BotConfig.getStartBtn);
                    BotConfig.IS.remove(chatId);
                    BotConfig.productId.remove(chatId);
                    BotConfig.SIZE.remove(chatId);
                    BotConfig.LOCATIONS.remove(chatId);
                } else if (BotConfig.IS.get(chatId).equals("confirm") && text.equals("Yo'q")) {
                    methods.getKeyboardBtnList(chatId, "Buyurtma bekor qilindi", BotConfig.getStartBtn);
                    BotConfig.IS.remove(chatId);
                    BotConfig.productId.remove(chatId);
                    BotConfig.SIZE.remove(chatId);
                    BotConfig.LOCATIONS.remove(chatId);
                } else if (BotConfig.IS.get(chatId).equals("product-size")) {
                    try {
                        int size = Integer.parseInt(text);
                        BotConfig.SIZE.put(chatId, size);
                        methods.sendMsg(chatId, "Manzilni tanlang");
                        BotConfig.IS.replace(chatId, "address");
                    } catch (NumberFormatException e) {
                        methods.sendMsg(chatId, "Raqam kiriting");
                    }
                } else if (BotConfig.IS.get(chatId).equals("search")) {
                    List<Product> search = productService.search(text);
                    if (search.size() == 0) {
                        methods.sendMsg(chatId, "Qidiruv natijasida mahsulot topilmadi");
                    } else {
                        methods.sendSearch(chatId, search, attachmentService);
                    }
                    BotConfig.IS.remove(chatId);
                } else if (BotConfig.IS.get(chatId).equals("category") && !text.equals("Asosiy bo'limga qaytish")) {
                    List<Product> products = productService.getProductByCategoryName(text);
                    methods.getProduct(chatId, "tanlang", products);
                    BotConfig.IS.replace(chatId, "product");
                } else if (BotConfig.IS.get(chatId).equals("product") && text.equals("Bo'limlarga qaytish")) {
                    methods.getCategory(chatId, "tanlang", categoryRepository.findAll());
                    BotConfig.IS.replace(chatId, "category");
                } else if (BotConfig.IS.get(chatId).equals("category") && text.equals("Asosiy bo'limga qaytish")) {
                    methods.getKeyboardBtnList(chatId, "Tanlang", BotConfig.getStartBtn);
                    BotConfig.IS.remove(chatId);
                }
            } else if (message.hasContact()) {
                Contact contact = message.getContact();
                methods.getKeyboardBtnList(chatId, "Tanlang", BotConfig.getStartBtn);
                userRepository.save(new it.revo.onemilioncourse.entity.User(Long.parseLong(chatId), contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), Collections.singletonList(roleRepository.findById(2).orElseThrow(() -> new ResourceNotFoundException(404, "getRole", "roleId", 2))), "a", true, true, true, true));
            } else if (message.hasLocation()) {
                Location location = message.getLocation();
                if (BotConfig.IS.get(chatId).equals("address")) {
                    BotConfig.LOCATIONS.put(chatId, new it.revo.onemilioncourse.entity.Location(location.getLongitude().toString(), location.getLatitude().toString()));
                    BotConfig.IS.replace(chatId, "confirm");
                    methods.getKeyboardBtnList(chatId, "Rozimisiz?", BotConfig.CONFIRM);
                }
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            String chatId = callbackQuery.getFrom().getId().toString();
            String[] split = data.split(":");
            String productId = split[1];
            if (data.startsWith("like")) {
                productService.likeAndBasketProducts(Long.parseLong(chatId), UUID.fromString(productId), "like");
                methods.sendMsg(chatId, "Sevimlilarga muvaffaqiyatli saqlandi...");
            } else if (data.startsWith("basket")) {
                productService.likeAndBasketProducts(Long.parseLong(chatId), UUID.fromString(productId), "basket");
                methods.sendMsg(chatId, "Savatga muvaffaqiyatli saqlandi...");
            } else if (data.startsWith("buy")) {
                BotConfig.productId.put(chatId, UUID.fromString(productId));
                methods.sendMsg(chatId, "Ushbu mahsulotdan nechta sotib olmoqchisiz?");
                BotConfig.IS.put(chatId, "product-size");
            }
        }
    }

    @Override
    public void clearWebhook() throws TelegramApiRequestException {

    }
}
