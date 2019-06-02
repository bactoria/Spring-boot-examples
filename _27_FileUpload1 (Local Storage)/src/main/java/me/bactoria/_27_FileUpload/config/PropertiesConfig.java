package me.bactoria._27_FileUpload.config;

import me.bactoria._27_FileUpload.domain.upload.UploadProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        UploadProperties.class
})
public class PropertiesConfig {
}
