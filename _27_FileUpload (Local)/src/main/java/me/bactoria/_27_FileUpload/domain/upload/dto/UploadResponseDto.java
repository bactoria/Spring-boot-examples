package me.bactoria._27_FileUpload.domain.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResponseDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
