package apiTests.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {

    private String title;
    private String code;
    private Counts counts;

    @Data
    @Builder
    public static class Counts {

        private int cases, suites, milestones;
        private Runs runs;
        private Defects defects;

        @Data
        @Builder
        public static class Runs {

            private int total, active;

        }

        @Data
        @Builder
        public static class Defects {

            private int total, open;

        }

    }

}
