package it.revo.onemilioncourse.service;

import it.revo.onemilioncourse.entity.User;
import it.revo.onemilioncourse.exception.ResourceNotFoundException;
import it.revo.onemilioncourse.payload.ApiResponse;
import it.revo.onemilioncourse.repository.RoleRepository;
import it.revo.onemilioncourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AttachmentService attachmentService;
    private final RoleRepository roleRepository;

    public ApiResponse<?> login(String phoneNumber, String password) {
        User user = userRepository.findUserByPhoneNumber(phoneNumber).orElseThrow(() -> new ResourceNotFoundException(404, "getUser", "phoneNumber", phoneNumber));
        if (user.getPassword().equals(password)) {
            return new ApiResponse<>("Muvaffaqiyatli xisobga kirildi", true, 200, user);
        }
        return new ApiResponse<>("Parolingiz xato", false, 403);
    }
}
