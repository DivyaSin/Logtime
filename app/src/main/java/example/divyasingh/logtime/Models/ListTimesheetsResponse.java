package example.divyasingh.logtime.Models;

import lombok.Getter;
import lombok.Setter;

public class ListTimesheetsResponse {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String hours;

    @Getter
    @Setter
    private String project_id;

    @Getter
    @Setter
    private String standup_detail;

    @Getter
    @Setter
    private String user_id;

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private String url;

    public ListTimesheetsResponse(String project_id, String hours, String date, String standup_detail, String user_id) {
        this.project_id = project_id;
        this.hours = hours;
        this.date = date;
        this.standup_detail = standup_detail;
        this.user_id = user_id;
    }
}
