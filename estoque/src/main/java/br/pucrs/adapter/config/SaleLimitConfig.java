package br.pucrs.adapter.config;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.application.calculator.limit.MaxSale5LimitSaleLimiter;
import br.pucrs.application.calculator.limit.NoLimitSaleLimiter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleLimitConfig {

    @Bean
    @ConditionalOnProperty(name = "location", havingValue = "um", matchIfMissing = true)
    SaleLimiter saleLimiterNoLimit() {
        return new NoLimitSaleLimiter();
    }

    @Bean
    @ConditionalOnProperty(name = "location", havingValue = "dois")
    SaleLimiter saleMax5LimitSale() {
        return new MaxSale5LimitSaleLimiter();
    }
}
