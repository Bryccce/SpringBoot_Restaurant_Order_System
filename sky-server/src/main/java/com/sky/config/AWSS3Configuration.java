package com.sky.config;

import com.sky.properties.AwsS3Properties;
import com.sky.utils.AwsS3Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AWSS3Configuration {

    @Bean
    @ConditionalOnMissingBean
    public AwsS3Util awsS3Util(AwsS3Properties awsS3Properties){
        log.info("create AWS S3 class: {}", awsS3Properties);
        return new AwsS3Util(awsS3Properties.getAccessKey(), awsS3Properties.getSecretKey(),
                awsS3Properties.getRegion(), awsS3Properties.getName());
    }
}
