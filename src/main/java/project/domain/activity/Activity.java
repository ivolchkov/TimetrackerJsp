package project.domain.activity;

import project.domain.Entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class Activity extends Entity {
    private String name;
    private Time spentTime;
    private Timestamp start;
    private Timestamp end;
    private String description;

    private Activity(ActivityBuilder builder) {
        super(builder.id);
        this.name = builder.name;
        this.spentTime = builder.spentTime;
        this.start = builder.start;
        this.end = builder.end;
        this.description= builder.description;
    }

    public String getName() {
        return name;
    }

    public Time getSpentTime() {
        return spentTime;
    }

    public Timestamp getStart() {
        return start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    public static ActivityBuilder builder() {
        return new ActivityBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }

        Activity activity = (Activity) o;

        return Objects.equals(name, activity.name) &&
                Objects.equals(spentTime, activity.spentTime) &&
                Objects.equals(start, activity.start) &&
                Objects.equals(end, activity.end) &&
                Objects.equals(description, activity.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, spentTime, start, end, description);
    }

    public static class ActivityBuilder {
        private Long id;
        private String name;
        private Time spentTime;
        private Timestamp start;
        private Timestamp end;
        private String description;

        private ActivityBuilder() {}

        public ActivityBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ActivityBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ActivityBuilder withSpentTime(Time spentTime) {
            this.spentTime = spentTime;
            return this;
        }

        public ActivityBuilder withStart(Timestamp start) {
            this.start = start;
            return this;
        }

        public ActivityBuilder withEnd(Timestamp end) {
            this.end = end;
            return this;
        }

        public ActivityBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Activity build() {
            return new Activity(this);
        }
    }
}
