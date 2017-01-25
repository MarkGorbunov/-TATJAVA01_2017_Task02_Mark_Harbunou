/**
 * Class that contain information about one equipment
 *
 * @author Mark_Harbunou
 */
public class SportEquipment {
    private String category;
    private String title;
    private int price;

    SportEquipment(String category, String title, int price) {
        this.category = category;
        this.title = title;
        this.price = price;
    }

    /**
     *
     * @return return category of equipment
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @return title of equipment
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return price of equipment
     */
    public int getPrice() {
        return price;
    }
}

