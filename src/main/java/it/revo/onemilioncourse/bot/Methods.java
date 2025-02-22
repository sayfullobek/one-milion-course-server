package it.revo.onemilioncourse.bot;

import it.revo.onemilioncourse.entity.Category;
import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.repository.RoleRepository;
import it.revo.onemilioncourse.repository.UserRepository;
import it.revo.onemilioncourse.repository.rest.CategoryRepository;
import it.revo.onemilioncourse.service.AttachmentService;
import it.revo.onemilioncourse.service.ProductService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class Methods extends Bot {
    public Methods(CategoryRepository categoryRepository, ProductService productService, AttachmentService attachmentService, UserRepository userRepository, RoleRepository roleRepository) {
        super(categoryRepository, productService, attachmentService, userRepository, roleRepository);
    }

    public void getCategoryButtons(String chatId, String text, List<Category> categories) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboardButtons = new ArrayList<>();
        for (Category category : categories) {
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setCallbackData(category.getId().toString());
            button.setText(category.getName());
            inlineKeyboardButtons.add(button);
            keyboardButtons.add(inlineKeyboardButtons);
        }
        inlineKeyboardMarkup.setKeyboard(keyboardButtons);
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("button error");
        }
    }

    public void getCategory(String chatId, String text, List<Category> categories) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        int tr = 0;
        for (int i = 0; i < categories.size() / 2; i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (int j = 0; j < 2; j++) {
                KeyboardButton keyboardButton = new KeyboardButton();
                keyboardButton.setText(categories.get(tr).getName());
                keyboardRow.add(keyboardButton);
                tr++;
            }
            keyboardRows.add(keyboardRow);
        }
        if (categories.size() % 2 == 1) {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText(categories.get(categories.size() - 1).getName());
            KeyboardButton back = new KeyboardButton();
            back.setText("Asosiy bo'limga qaytish");
            keyboardRow.add(keyboardButton);
            keyboardRow.add(back);
            keyboardRows.add(keyboardRow);
        } else {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton back = new KeyboardButton();
            back.setText("Asosiy bo'limga qaytish");
            keyboardRow.add(back);
            keyboardRows.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        try {
            execute(
                    SendMessage.builder()
                            .chatId(chatId)
                            .text(text)
                            .replyMarkup(replyKeyboardMarkup)
                            .build()
            );
        } catch (TelegramApiException e) {
            System.err.println("not btn");
        }
    }

    public void getProduct(String chatId, String text, List<Product> products) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        int tr = 0;
        for (int i = 0; i < products.size() / 2; i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (int j = 0; j < 2; j++) {
                KeyboardButton keyboardButton = new KeyboardButton();
                keyboardButton.setText(products.get(tr).getName());
                keyboardButton.setWebApp(new WebAppInfo("https://main--fabulous-mousse-ed6ff2.netlify.app/product/" + products.get(tr).getId() + "/" + chatId));
                keyboardRow.add(keyboardButton);
                tr++;
            }
            keyboardRows.add(keyboardRow);
        }
        if (products.size() % 2 == 1) {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText(products.get(products.size() - 1).getName());
            keyboardButton.setWebApp(new WebAppInfo("https://main--fabulous-mousse-ed6ff2.netlify.app/product/" + products.get(products.size() - 1).getId() + "/" + chatId));
            KeyboardButton back = new KeyboardButton();
            back.setText("Bo'limlarga qaytish");
            keyboardRow.add(keyboardButton);
            keyboardRow.add(back);
            keyboardRows.add(keyboardRow);
        } else {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton back = new KeyboardButton();
            back.setText("Bo'limlarga qaytish");
            keyboardRow.add(back);
            keyboardRows.add(keyboardRow);
        }
        System.out.println("");
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        try {
            execute(
                    SendMessage.builder()
                            .chatId(chatId)
                            .text(text)
                            .replyMarkup(replyKeyboardMarkup)
                            .build()
            );
        } catch (TelegramApiException e) {
            System.err.println("not btn");
        }
    }

    public void getKeyboardBtnList(String chatId, String text, List<String> btns) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        int tr = 0;
        for (int i = 0; i < btns.size() / 2; i++) {
            KeyboardRow keyboardRow = new KeyboardRow();
            for (int j = 0; j < 2; j++) {
                KeyboardButton keyboardButton = new KeyboardButton();
                keyboardButton.setText(btns.get(tr));
                keyboardRow.add(keyboardButton);
                tr++;
            }
            keyboardRows.add(keyboardRow);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        try {
            execute(
                    SendMessage.builder()
                            .chatId(chatId)
                            .text(text)
                            .replyMarkup(replyKeyboardMarkup)
                            .build()
            );
        } catch (TelegramApiException e) {
            System.err.println("not btn");
        }
    }

    public void shareContactBtn(String chatId, String text) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Kontaktni ulashish");
        keyboardButton.setRequestContact(true);
        keyboardButtons.add(keyboardButton);
        keyboardRows.add(keyboardButtons);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        SendMessage sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .replyMarkup(replyKeyboardMarkup)
                .build();
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.err.println("btn error");
        }
    }

    public void sendMsg(String chatId, String text) {
        try {
            execute(
                    SendMessage.builder()
                            .chatId(chatId)
                            .text(text)
                            .build()
            );
        } catch (TelegramApiException e) {
            System.err.println("not btn");
        }
    }

    public void sendSearch(String chatId, List<Product> searchs, AttachmentService attachmentService) {
        for (Product search : searchs) {
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> inlines = new ArrayList<>();
            List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
            InlineKeyboardButton like = new InlineKeyboardButton();
            InlineKeyboardButton basket = new InlineKeyboardButton();
            InlineKeyboardButton buy = new InlineKeyboardButton();
            like.setText("Sevimlilarga saqlash");
            like.setCallbackData("like:" + search.getId());

            basket.setText("Savatga saqlash");
            basket.setCallbackData("basket:" + search.getId());

            buy.setText("Sotib olish");
            buy.setCallbackData("buy:" + search.getId());

            inlineKeyboardButtons.add(like);
            inlineKeyboardButtons.add(basket);
            inlineKeyboardButtons.add(buy);

            inlines.add(inlineKeyboardButtons);

            inlineKeyboardMarkup.setKeyboard(inlines);

            try {
                execute(
                        SendPhoto.builder()
                                .chatId(chatId)
                                .caption("Mashulot nomi " + search.getName() + "\nNarxi " + search.getPrice() + "$\nMahsulot haqida " + search.getDescription())
                                .photo(new InputFile("https://one-milion-course-server-b9404f6d4bf4.herokuapp.com/api/attachment/download?id=" + search.getPhotoId()))
//                                .photo(new InputFile("https://images.pexels.com/photos/59821/lamb-spring-nature-animal-59821.jpeg?cs=srgb&dl=pexels-pixabay-59821.jpg&fm=jpg"))
                                .replyMarkup(inlineKeyboardMarkup)
                                .build()
                );
            } catch (TelegramApiException e) {
                System.err.println("not btn");
            }
        }
    }
}
