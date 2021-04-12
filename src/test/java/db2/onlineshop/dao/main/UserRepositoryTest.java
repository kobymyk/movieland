package db2.onlineshop.dao.main;

import db2.onlineshop.config.PersistenceMainConfiguration;
import db2.onlineshop.entity.main.User;
import db2.onlineshop.service.security.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ContextConfiguration(classes = PersistenceMainConfiguration.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void whenCreatingUser_thenCreated() {
        User user = new User(1, "nickname", "123@123.com", "123", Role.USER);
        userRepository.save(user);

        assertNotNull(userRepository.findById(1));
    }

    @Test
    public void shouldFindByEmail() {
        Optional<User> user = userRepository.findByEmail("ronald.reynolds66@example.com");
        assertNotNull(user);
    }
}
