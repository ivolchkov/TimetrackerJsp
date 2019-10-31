package project.entity.sprint;

import project.entity.story.StoryEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class SprintEntity {
    private final Integer id;
    private final String name;
    private final LocalDate start;
    private final LocalDate end;
    private final String description;
    private final List<StoryEntity> stories;

    private SprintEntity(SprintBuilder builder) {
        id = builder.id;
        name = builder.name;
        start = builder.start;
        end = builder.end;
        description = builder.description;
        stories = builder.stories;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getDescription() {
        return description;
    }

    public List<StoryEntity> getStories() {
        return stories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SprintEntity sprint = (SprintEntity) o;

        return Objects.equals(id, sprint.id) &&
                Objects.equals(name, sprint.name) &&
                Objects.equals(start, sprint.start) &&
                Objects.equals(end, sprint.end) &&
                Objects.equals(description, sprint.description) &&
                Objects.equals(stories, sprint.stories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, description, stories);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Sprint № ").append(id).append(", ").
                append("name: ").append(name).append(", ").
                append("start: ").append(start).append(", ").
                append("end: ").append(end).append(", ").
                append("description: ").append(description);

        return stringBuilder.toString();
    }

    public static SprintBuilder builder() {
        return new SprintBuilder();
    }


    public static final class SprintBuilder {
        private Integer id;
        private String name;
        private LocalDate start;
        private LocalDate end;
        private String description;
        private List<StoryEntity> stories;

        private SprintBuilder() {
        }

        public SprintBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public SprintBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SprintBuilder withStart(LocalDate start) {
            this.start = start;
            return this;
        }

        public SprintBuilder withEnd(LocalDate end) {
            this.end = end;
            return this;
        }

        public SprintBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public SprintBuilder withStories(List<StoryEntity> stories) {
            this.stories = stories;
            return this;
        }

        public SprintEntity build() {
            return new SprintEntity(this);
        }
    }
}
