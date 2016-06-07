package example.divyasingh.logtime.Models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

public class CreateTimesheetRequest {

        @Getter @Setter
        private Timesheet timesheet;

        public class Timesheet {

                @Getter @Setter
                private String hours;

                @Getter @Setter
                private String project_id;

                @Getter @Setter
                private String standup_detail;

                @Getter @Setter
                private String date;
        }
}