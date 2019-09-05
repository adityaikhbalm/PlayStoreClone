package budiluhur.ac.id.tugas2;

import android.util.Log;

/**
 * Created by ipin on 10/1/2018.
 */

public class SingleItemModel {


    private String name, size, description;
    int image;


    public SingleItemModel() {
    }

    public SingleItemModel(String name, String size, int image) {
        this.name = name;
        this.size = size;
        this.image = image;
    }


    public int getImage() {
        return image;
    }

    public void setImage(String url) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}