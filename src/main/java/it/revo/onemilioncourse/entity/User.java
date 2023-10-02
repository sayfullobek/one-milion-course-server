package it.revo.onemilioncourse.entity;

import it.revo.onemilioncourse.entity.template.AbsEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
@Builder
public class User extends AbsEntity {

    private Long chatId;

    @Column(nullable = false)
    private String firstName; //ismi

    private String lastName; //familiyasi

    private String phoneNumber; //nomeri

    private String password; //paroli

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles; //roli

    @Column(nullable = false, unique = true)
    private String referralCode; //referral code

    private boolean enabled = true; //ushbu account ishlayaptimi yoki yo'q

    private boolean credentialsNonExpired = true; //ushbu accountning ma'lumotlarining muddati tugaganmi yoki yo'qmi

    private boolean accountNonLocked = true; //ushbu account ochiq yoki yo'qligi
    private boolean accountNonExpired = true; //ushbu accountning muddati tugaganmi yoki yo'qmi


    public User(Long chatId, String firstName, String lastName, String phoneNumber, List<Role> roles, String referralCode, boolean enabled, boolean credentialsNonExpired, boolean accountNonLocked, boolean accountNonExpired) {
        this.chatId = chatId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.referralCode = referralCode;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
    }

    public User(String firstName, String lastName, String phoneNumber, String password, List<Role> roles, String referralCode, boolean enabled, boolean credentialsNonExpired, boolean accountNonLocked, boolean accountNonExpired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.roles = roles;
        this.referralCode = referralCode;
        this.enabled = enabled;
        this.credentialsNonExpired = credentialsNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
    }

}
