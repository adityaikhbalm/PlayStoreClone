package budiluhur.ac.id.tugas2;

import java.util.ArrayList;

/**
 * Created by ipin on 10/1/2018.
 */

public class SectionDataModel {



    private String headerTitle, headerMore;
    private ArrayList<SingleItemModel> allItemsInSection;


    public SectionDataModel() {

    }
    public SectionDataModel(String headerTitle, String headerMore, ArrayList<SingleItemModel> allItemsInSection) {
        this.headerTitle = headerTitle;
        this.headerMore = headerMore;
        this.allItemsInSection = allItemsInSection;
    }



    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

    public ArrayList<SingleItemModel> getAllItemsInSection() {
        return allItemsInSection;
    }

    public void setAllItemsInSection(ArrayList<SingleItemModel> allItemsInSection) {
        this.allItemsInSection = allItemsInSection;
    }


    public String getHeaderMore() {
        return headerMore;
    }

    public void setHeaderMore(String headerMore) {
        this.headerMore = headerMore;
    }
}
