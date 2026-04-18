package com.journalapp.springsecurity.springsecurity.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

// Store the data manully into DB just make a structure over here
// mongotTempleteImpl + UserController + ConfigJournalAppEntity + AppCache
@Document(collection = "users_for_journal")
@Data
public class ConfigJournalAppEntity {

    public String key;
    public String value;
}
