package seo.dale.spring.todo.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import seo.dale.spring.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ToDo extends BaseEntity {

    private static final int MAX_LENGHTH_TITLE = 100;
    private static final int MAX_LENGHTH_DESCRIPTION = 500;

    @NotEmpty
    @Length(max = MAX_LENGHTH_TITLE)
    @Column(length = MAX_LENGHTH_TITLE)
    private String title;

    @Length(max = MAX_LENGHTH_DESCRIPTION)
    @Column(length = MAX_LENGHTH_DESCRIPTION)
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

        public Builder(String title) {
            built = new ToDo();
            built.title = title;
        }

        public ToDo build() {
            return built;
        }

        public Builder description(String description) {
            built.description = description;
            return this;
        }

    }

}
