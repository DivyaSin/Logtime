package example.divyasingh.logtime.Models;

import lombok.Getter;
import lombok.Setter;

public class FetchProjectsResponse {

    @Getter @Setter
    private String id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String active;

    @Getter @Setter
    private String project_detail;

    @Getter @Setter
    private String client_id;

    @Getter @Setter
    private String url;

    public FetchProjectsResponse(String name, String client_id, String project_detail, String active){
        this.name = name;
        this.client_id = client_id;
        this.project_detail = project_detail;
        this.active = active;
    }
}
