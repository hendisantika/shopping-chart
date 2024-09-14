package com.hendisantika.shoppingchart;

import com.hendisantika.shoppingchart.dao.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@SpringBootTest(
        properties = {
                "management.endpoint.health.show-details=always",
                "spring.datasource.url=jdbc:tc:mysql:8.4.0:///shoppingCart"
        },
        webEnvironment = RANDOM_PORT
)
public class ShoppingChartApplicationTests {

    @Autowired
    private ProductDAO productDAO;

    @BeforeEach
    void deleteAll() {
        productDAO.deleteProduct("one");
    }

}
