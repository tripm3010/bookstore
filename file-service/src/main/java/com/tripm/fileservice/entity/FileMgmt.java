package com.tripm.fileservice.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "file_mgmt")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileMgmt {
    @MongoId
    String id;
    String ownerId;
    String contentType;
    long size;
    String md5Checksum;
    String path;
}
