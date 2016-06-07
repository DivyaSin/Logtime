package example.divyasingh.logtime.Models;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class ClientDetailsResponse implements Serializable{

    @Getter @Setter
    private String id;

    @Getter @Setter
    private String updated_at;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String created_at;

}
