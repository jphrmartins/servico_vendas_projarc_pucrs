package br.pucrs.adapter.config;

import br.pucrs.application.calculator.SaleLimiter;
import br.pucrs.application.calculator.limit.MaxSale15LimitSaleLimiter;
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
    SaleLimiter saleMax15LimitSale() {
        return new MaxSale15LimitSaleLimiter();
    }
}
