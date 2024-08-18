public class Person implements Cloneable {
    private final String name;
    private final int id;
    private Person friend;

    /**
     * this is the constructor for the person object, that has a friend
      * @param name
     * @param id
     * @param friend
     */
    public Person(String name, int id, Person friend) {
        this.name = name;
        this.id = id;
        this.friend = friend;
    }

    /**
     * this is the constructor for the person object, that doesn't have a friend
     * @param name
     * @param id
     */
    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * a method to allow us compare two objects using their memory location
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return this.id==person.id;
    }

    /**
     * clones a person. if the person has a friend it clones him as well, if the person is null it returns null.
     * @return
     */
    @Override
    public Person clone() {
        try {
            Person clonedPerson = (Person) super.clone();
            if (this.friend != null) {
                clonedPerson.friend = this.friend.clone();
            }
            return clonedPerson;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public Person getFriend() {
        return friend;
    }

    /**
     *
     * @return
     */
    public boolean hasFriend() {
        return friend != null;
    }
}