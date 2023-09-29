package it.revo.onemilioncourse;

import it.revo.onemilioncourse.bot.Bot;
import it.revo.onemilioncourse.bot.Methods;
import it.revo.onemilioncourse.repository.ProductRepository;
import it.revo.onemilioncourse.repository.RoleRepository;
import it.revo.onemilioncourse.repository.UserRepository;
import it.revo.onemilioncourse.repository.rest.CategoryRepository;
import it.revo.onemilioncourse.service.AttachmentService;
import it.revo.onemilioncourse.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class OneMilionCourseApplication {
    public static void main(String[] args) throws TelegramApiException {
        ConfigurableApplicationContext run = SpringApplication.run(OneMilionCourseApplication.class, args);
        Methods methods = run.getBean(Methods.class);
        CategoryRepository categoryRepository = run.getBean(CategoryRepository.class);
        ProductService productService = run.getBean(ProductService.class);
        AttachmentService attachmentService = run.getBean(AttachmentService.class);
        UserRepository userRepository = run.getBean(UserRepository.class);
        RoleRepository roleRepository = run.getBean(RoleRepository.class);
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new Bot(categoryRepository, productService, attachmentService, userRepository, roleRepository));
    }
}
