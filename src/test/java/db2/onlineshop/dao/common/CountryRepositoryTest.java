package db2.onlineshop.dao.common;

import db2.onlineshop.config.CommonJpaConfiguration;
import db2.onlineshop.entity.common.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableTransactionManagement
@ContextConfiguration(classes = CommonJpaConfiguration.class)
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    @Transactional
    public void whenCreatingCountry_thenCreated() {
        Country country = new Country(1, "Mexico");
        countryRepository.save(country);

        assertNotNull(countryRepository.findById(1));
    }
}
