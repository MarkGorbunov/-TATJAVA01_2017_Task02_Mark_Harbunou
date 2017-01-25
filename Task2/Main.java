import java.io.IOException;

/**
 * @author Mark_Harbunou
 *
 * Class that contain entry point in programm
 */
public class Main {
    /**
     * method in which we input parameters for our shop
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Shop shop = new Shop();
        shop.setGoods();
        try {
            shop.createPerson("Q", "Q", shop.findSportEquipment("Balls", "ballsForBasket"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            shop.createPerson("Q", "Q", shop.findSportEquipment("Balls", "ballsForFootball"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        shop.takeGoods(shop.findPerson("Q", "Q"), shop.findSportEquipment("Balls", "ballsForBasket"), 2);
        shop.takeGoods(shop.findPerson("Q", "Q"), shop.findSportEquipment("Balls", "ballsForFootball"), 2);
        shop.PrintInShop(shop.findSportEquipment("Balls", "ballsForBasket"));
        shop.PrintInShop(shop.findSportEquipment("Balls", "ballsForFootball"));
        shop.PrintInRent(shop.findPerson("Q", "Q"));
    }
}
