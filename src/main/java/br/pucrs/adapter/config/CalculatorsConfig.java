package br.pucrs.adapter.config;

import br.pucrs.application.calculator.CostCalculator;
import br.pucrs.application.calculator.fee.FirstLocationFeeCalculator;
import br.pucrs.application.calculator.fee.SecondLocationFeeCalculator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculatorsConfig {

    @Bean
    @ConditionalOnProperty(name = "location", havingValue = "um", matchIfMissing = true)
    CostCalculator createFeeCalculatorOfFirstLocation() {
        return new FirstLocationFeeCalculator();
    }

    @Bean
    @ConditionalOnProperty(name = "location", havingValue = "dois")
    CostCalculator createFeeCalculatorOfSecondLocation() {
        return new SecondLocationFeeCalculator();
    }
}
