package example.divyasingh.logtime.Models;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {

    @Getter @Setter
    private String authentication_token;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private User user;

    public class User {
        @Getter @Setter
        private String email;

        @Getter @Setter
        private String id;

        @Getter @Setter
        private String created_at;

        @Getter @Setter
        private String updated_at;

        @Getter @Setter
        private String name;

        @Getter @Setter
        private String authentication_token;
    }
}