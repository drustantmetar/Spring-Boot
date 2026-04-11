package com.journalapp.springsecurity.springsecurity.Model;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;


@Data
@Document("users")
//@Builder // Add this for call into the @Test the loadUserByUsername method of the UserDeailsserviceImpls
public class users {

    @Id
    private String id;

    @Indexed(unique = true)
    @Nonnull
    private String username;

    @Nonnull
    private String password;

    private List<String> roles;

    @DBRef
    public List<journals> jounralList = new ArrayList<>(); // List structure created


}
