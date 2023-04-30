package com.allocdalloc.colormemorybook.config.apple;

import com.allocdalloc.colormemorybook.ColorMemoryBookApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackageClasses = ColorMemoryBookApplication.class)
public class FeignClientConfig {
}
