package com.journalapp.springsecurity.springsecurity.Model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Document("journals")
public class journals {
    @Id
    private String id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
