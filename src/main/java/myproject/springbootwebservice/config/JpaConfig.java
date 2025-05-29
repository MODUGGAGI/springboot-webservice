package myproject.springbootwebservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    /**
     * @EnableJpaAuditing 어노테이션이 @SpringBootApplication이 함께 있기 때문에
     * 테스트 시 @WebMvcTest에서도 스캔하게 된다.
     * 그런데 @EnableJpaAuditing을 사용하시 위해서는 최소 하나의 @Entity 클래스가 필요하다.
     * @WebMvcTest에는 @Entity 클래스가 당연히 없다.
     */
}
