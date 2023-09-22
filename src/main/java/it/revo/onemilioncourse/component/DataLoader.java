package it.revo.onemilioncourse.component;

import it.revo.onemilioncourse.entity.Role;
import it.revo.onemilioncourse.entity.User;
import it.revo.onemilioncourse.entity.enums.RoleName;
import it.revo.onemilioncourse.repository.AttachmentRepository;
import it.revo.onemilioncourse.repository.RoleRepository;
import it.revo.onemilioncourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final AttachmentRepository attachmentRepository;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String initMode;

    @Override
    public void run(String... args) {
        if (initMode.equals("create-drop") || initMode.equals("create")) {
            for (RoleName value : RoleName.values()) {
                roleRepository.save(
                        new Role(
                                value
                        )
                );
            }
            User save = userRepository.save(
                    new User(
                            "Sayfullo",
                            "To'xtayev",
                            "990763246",
                            "admin123",
                            Collections.singletonList(roleRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("getRole"))),
                            "qozi123",
                            true,
                            true,
                            true,
                            true
                    )
            );
        }
    }
}