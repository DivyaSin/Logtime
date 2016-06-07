package example.divyasingh.logtime.Models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class UserRequest {

    @SerializedName("user")
    @Getter @Setter
    private User user;

    public static class User {

        @Getter @Setter
        private String email;

        @Getter @Setter
        private String password;

    }
}
