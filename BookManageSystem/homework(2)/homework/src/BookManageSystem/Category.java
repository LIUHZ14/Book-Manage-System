package BookManageSystem;

import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private String categoryId;
    private String categoryName;
    private String description;

    public Category(String categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-30s", categoryId, categoryName, description);
    }
}