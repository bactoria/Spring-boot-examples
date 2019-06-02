package me.bactoria._27_FileUpload.domain.upload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@NoArgsConstructor @Getter @Setter
@ConfigurationProperties(prefix = "file")
public class UploadProperties {
    private String uploadDir;
}
