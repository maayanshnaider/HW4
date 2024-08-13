public class Person implements Cloneable {
    private String name;
    private String id;
    private Person friend;

    public Person(String name, String id, Person friend) {
        this.name = name;
        this.id = id;
        this.friend = friend;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id.equals(person.id);
    }

    @Override
    public Person clone() {
        try {
            Person cloned = (Person) super.clone();
            if (this.friend != null) {
                cloned.friend = this.friend.clone();
            }
            return cloned;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getFriend() {
        return friend;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }
}