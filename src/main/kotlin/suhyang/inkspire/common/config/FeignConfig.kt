package suhyang.inkspire.common.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients(basePackages = [
    "suhyang.inkspire.infrastructure"
])
class FeignConfig {
}