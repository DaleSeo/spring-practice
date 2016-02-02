package seo.dale.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ToDo extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    public void update(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {

        private ToDo built;

        public Builder() {
            built = new ToDo();
        }

        public ToDo build() {
            return built;
        }

        public Builder title(String title) {
            built.title = title;
            return this;
        }

        public Builder description(String description) {
            built.description = description;
            return this;
        }

    }

}
