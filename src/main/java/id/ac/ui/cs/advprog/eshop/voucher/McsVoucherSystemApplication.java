package id.ac.ui.cs.advprog.eshop.voucher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class McsVoucherSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(McsVoucherSystemApplication.class, args);
    }
}
