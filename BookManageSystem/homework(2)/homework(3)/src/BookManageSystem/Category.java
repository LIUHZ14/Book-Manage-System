package BookManageSystem;

import java.io.Serializable;

public class Category implements Serializable {//Serializable version ID for data compatibility
    private static final long serialVersionUID = 1L;

    private String categoryId;//Unique category identifier
    private String categoryName;//Category display name
    private String description;//Brief description of the category

    public Category(String categoryId, String categoryName, String description) {//Constructor
        this.categoryId = categoryId;//Set category ID
        this.categoryName = categoryName;//Set category name
        this.description = description;//Set category description
    }

    //---Getter methods---

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
    }//Return a string representation of the category
}