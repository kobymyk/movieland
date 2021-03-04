package db2.onlineshop.dao.main;

import db2.onlineshop.entity.main.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
