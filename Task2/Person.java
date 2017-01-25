/**
 * Class that contain information about one person
 *
 * @author Mark_Harbunou
 */

public class Person {
    private String name;
    private String surName;


    Person(String name, String surName) {
        this.name = name;
        this.surName = surName;
    }

    /**
     *
     * @return name of Person
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return surname of Person
     */
    public String getSurName() {
        return surName;
    }


}
