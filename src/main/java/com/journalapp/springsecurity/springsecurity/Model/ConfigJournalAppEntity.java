package com.journalapp.springsecurity.springsecurity.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users_for_journal")
@Data
public class ConfigJournalAppEntity {

    public String key;
    public String value;
}
