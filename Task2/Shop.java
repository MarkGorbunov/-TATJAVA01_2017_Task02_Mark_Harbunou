import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that contain information about all goods in the shop,information about persons who take goods
 *
 * @author Mark_Harbunou
 */
public class Shop {
    private HashMap<SportEquipment, Integer> goods = new HashMap<>();
    private HashMap<Person, HashMap<SportEquipment, Integer>> persons = new HashMap<>();

    /**
     * method that set goods to the shop from file
     *
     * @throws IOException
     */
    public void setGoods() throws IOException {
        Path pathToFile = Paths.get("C:/Users/Mark_Harbunou/IdeaProjects/task 2/equipment.txt");
        List<String> lines = Files.readAllLines(pathToFile, StandardCharsets.UTF_8);
        for (String line : lines) {
            String[] argumentInTxt = line.split(" ");
            goods.put(new SportEquipment(argumentInTxt[0], argumentInTxt[1],
                    Integer.parseInt(argumentInTxt[2])), Integer.parseInt(argumentInTxt[3]));
        }
    }

    /**
     * method that create person and push him to the HasMap persons
     *
     * @param name      name of pushing person
     * @param surName   surname of pushing person
     * @param equipment equipment that he want to take,with zero value
     */
    public void createPerson(String name, String surName, SportEquipment equipment) {
        Person person = new Person(name, surName);
        HashMap<SportEquipment, Integer> equipForPerson = new HashMap<>();
        equipForPerson.put(equipment, 0);
        persons.put(person, equipForPerson);
    }

    /**
     * method that find person, if him exist return person else throw exception
     *
     * @param name    of founded person
     * @param surName of founded person
     * @return founded person
     * @throws Exception
     */
    public Person findPerson(String name, String surName) throws Exception {
        for (HashMap.Entry<Person, HashMap<SportEquipment, Integer>> person : persons.entrySet()) {
            if (person.getKey().getName().equals(name) && person.getKey().getSurName().equals(surName)) {
                return person.getKey();
            }
        }
        throw new NullPointerException("needed person not found| " + name + "| " + surName);
    }

    /**
     * * method that find equipment, if it exist return person else throw exception
     *
     * @param category of founded equipment
     * @param title    of founded equipment
     * @return founded equipment
     */
    public SportEquipment findSportEquipment(String category, String title) {
        for (HashMap.Entry<SportEquipment, Integer> good : goods.entrySet()) {
            if (good.getKey().getCategory().equals(category) && good.getKey().getTitle().equals(title))
                return good.getKey();
        }
        throw new NullPointerException("needed equipment not found |" + category + "| " + title);
    }

    /**
     * method that take goods in the shop, if person take than 3 goods of same type print warning
     * if in the shop not enough goods for taking,print warning
     *
     * @param person    that take equipment
     * @param equipment equipment that person take
     * @param count     count of equipment that person take
     */
    public void takeGoods(Person person, SportEquipment equipment, int count) {
        int allCount = 0;
        HashMap<SportEquipment, Integer> equipForPerson = persons.get(person);
        for (Integer quant : equipForPerson.values()) {
            allCount += quant;
        }
        if (allCount + count > 3) {
            System.out.println("forbidden to take more than 3 amount of goods of same type |" + equipment.getCategory() + "|" + equipment.getTitle());
            return;
        } else {
            for (Map.Entry<SportEquipment, Integer> item : goods.entrySet()) {
                if (item.getKey().equals(equipment)) {
                    if (item.getValue() - count > 0) {
                        item.setValue(item.getValue() - count);
                    } else {
                        break;
                    }
                }
            }
            for (HashMap.Entry<Person, HashMap<SportEquipment, Integer>> man : persons.entrySet()) {
                if (man.getKey().getName().equals(person.getName()) &&
                        man.getKey().getSurName().equals(person.getSurName())) {
                    for (Map.Entry<SportEquipment, Integer> d : man.getValue().entrySet()) {
                        if (d.getKey().equals(equipment)) {
                            if (d.getValue() + count <= goods.get(equipment)) {
                                d.setValue(d.getValue() + count);
                            } else {
                                System.out.println("not enough goods for take| " + equipment.getCategory() + "|" + equipment.getTitle());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * method that print information about all goods that available in shop
     *
     * @param equipment
     */
    public void PrintInShop(SportEquipment equipment) {
        for (HashMap.Entry<SportEquipment, Integer> f : goods.entrySet()) {
            if (f.getKey().getCategory().equals(equipment.getCategory()) && f.getKey().getTitle().equals(equipment.getTitle())) {
                System.out.println("|Category: " + f.getKey().getCategory() + " |Title: " + f.getKey().getTitle()
                        + " |Price(USD): " + f.getKey().getPrice() + " |Count: " + f.getValue());
                break;
            }
        }
    }

    /**
     * method that print information about all goods that persons take
     *
     * @param person for that we print information
     */
    public void PrintInRent(Person person) {
        for (Map.Entry<Person, HashMap<SportEquipment, Integer>> f : persons.entrySet()) {
            if (f.getKey().getName().equals(person.getName()) && f.getKey().getSurName().equals(person.getSurName())) {
                for (Map.Entry<SportEquipment, Integer> d : f.getValue().entrySet()) {
                    if (d.getValue().equals(0)) {
                        break;
                    }
                    System.out.println("|Name: " + f.getKey().getName() + "|SurName: " + f.getKey().getSurName() +
                            "|Category: " + d.getKey().getCategory() + " |Title: " + d.getKey().getTitle()
                            + " |Price(USD): " + d.getKey().getPrice() + " |Count: " + d.getValue());
                }
            }

        }
    }
}
