package it.revo.onemilioncourse.repository;

import it.revo.onemilioncourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByPhoneNumber(String username);

    User findUserByChatId(Long chatId);

    @Query(value = "select * from users where likeProducts=?1", nativeQuery = true)
    boolean getProductLike(UUID id);
}
