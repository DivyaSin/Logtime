package example.divyasingh.logtime.Models;

public class UserRequest {
    User user;

    private UserRequest userRequest;

    public UserRequest getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(UserRequest userRequest) {
        this.userRequest = userRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static class User {


        private String email;

        private String password;


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "ClassPojo [email = " + email + ", password = " + password + "]";
        }
    }

}
