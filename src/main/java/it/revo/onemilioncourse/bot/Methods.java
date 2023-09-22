package it.revo.onemilioncourse.bot;

import it.revo.onemilioncourse.entity.Category;
import it.revo.onemilioncourse.entity.Product;
import it.revo.onemilioncourse.repository.ProductRepository;
import it.revo.onemilioncourse.repository.rest.CategoryRepository;
import it.revo.onemilioncourse.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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

    public Methods(CategoryRepository categoryRepository, ProductService productService) {
        super(categoryRepository, productService);
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
            back.setText("Orqaga");
            keyboardRow.add(keyboardButton);
            keyboardRow.add(back);
            keyboardRows.add(keyboardRow);
        } else {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton back = new KeyboardButton();
            back.setText("Orqaga");
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
                keyboardButton.setWebApp(new WebAppInfo("https://main--fabulous-mousse-ed6ff2.netlify.app/product/" + products.get(tr).getId()));
                keyboardRow.add(keyboardButton);
                tr++;
            }
            keyboardRows.add(keyboardRow);
        }
        if (products.size() % 2 == 1) {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton keyboardButton = new KeyboardButton();
            keyboardButton.setText(products.get(products.size() - 1).getName());
            keyboardButton.setWebApp(new WebAppInfo("https://main--fabulous-mousse-ed6ff2.netlify.app/product/" + products.get(products.size() - 1).getId()));
            KeyboardButton back = new KeyboardButton();
            back.setText("Orqaga");
            keyboardRow.add(keyboardButton);
            keyboardRow.add(back);
            keyboardRows.add(keyboardRow);
        } else {
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton back = new KeyboardButton();
            back.setText("Orqaga");
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
}
