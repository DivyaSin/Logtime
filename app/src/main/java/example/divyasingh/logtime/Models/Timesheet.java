package example.divyasingh.logtime.Models;

import com.google.gson.annotations.SerializedName;

public class Timesheet {

        private String id;

        private String hours;

        @SerializedName("project_id")
        private String projectId;

        @SerializedName("standup_detail")
        private String standupDetail;

        @SerializedName("user_id")
        private String userId;

        private String date;

        private String url;

        public String getId() {
                return id;
        }

        public void setId(String id) {
                this.id = id;
        }

        public String getHours() {
                return hours;
        }

        public void setHours(String hours) {
                this.hours = hours;
        }

        public String getProjectId() {
                return projectId;
        }

        public void setProjectId(String projectId) {
                this.projectId = projectId;
        }

        public String getStandupDetail() {
                return standupDetail;
        }

        public void setStandupDetail(String standupDetail) {
                this.standupDetail = standupDetail;
        }

        public String getUserId() {
                return userId;
        }

        public void setUserId(String userId) {
                this.userId = userId;
        }

        public String getDate() {
                return date;
        }

        public void setDate(String date) {
                this.date = date;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        @Override
        public String toString()
        {
            return " [id = "+id+", hours = "+hours+", project_id = "+projectId+", standup_detail = "+standupDetail+", user_id = "+userId+", date = "+date+", url = "+url+"]";
        }
}
