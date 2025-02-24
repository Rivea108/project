package com.letsgotravel.myapp.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/value.properties") // value.properties 파일 로드
public class KeyValue {
    // 추가적인 Bean 설정이 필요하면 이곳에 추가
}
