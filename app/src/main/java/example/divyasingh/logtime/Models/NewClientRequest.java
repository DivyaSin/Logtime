package example.divyasingh.logtime.Models;

import lombok.Getter;
import lombok.Setter;

public class NewClientRequest {

    @Getter @Setter
    private Client client;

    public static class Client {
        @Getter
        @Setter
        private String name;
    }
}
