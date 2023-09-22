package it.revo.onemilioncourse.repository;

import it.revo.onemilioncourse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByPhoneNumber(String username);

    Optional<User> findUserByReferralCode(String referralCode);

    List<User> findUsersByRolesId(Integer role_id);

    boolean existsUserByPhoneNumber(String phoneNumber);

    boolean existsUserByReferralCode(String referralCode);

}
