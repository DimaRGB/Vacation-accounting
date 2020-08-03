package com.github.dmytr0.vacation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "users")
@EqualsAndHashCode(exclude = "_id")
@Accessors(chain = true)
public class User {

    @JsonProperty("id")
    private String _id;
    private String name;
    private String team;
    private String userpic;
    private String email;
    private Map<String, String> extData = new HashMap<>();
    private String gender;
    private String locale;
    private String password;
    private LocalDateTime lastVisit;
    private boolean expired = false;
    private boolean locked = false;
    private boolean credentialExpired = false;
    private boolean enabled = true;
    private Set<String> authorities;

    public static User fromMap(Map<String, Object> map) {
        User newUser = new User();
        newUser.set_id((String) map.get("sub"));
        newUser.setName((String) map.get("name"));
        newUser.setEmail((String) map.get("email"));
        newUser.setGender((String) map.get("gender"));
        newUser.setLocale((String) map.get("locale"));
        newUser.setUserpic((String) map.get("picture"));
        newUser.setPassword(UUID.randomUUID().toString());
        return newUser;
    }
}
