package db2.onlineshop.entity.main;

import db2.onlineshop.service.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private int id;
    @Column(name = "name")
    private String nickname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
