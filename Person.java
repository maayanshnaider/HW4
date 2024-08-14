public class Person implements Cloneable {
    private String name;
    private int id;
    private Person friend;

    public Person(String name, int id, Person friend) {
        this.name = name;
        this.id = id;
        this.friend = friend;
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
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
        return this.id==person.id;
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

    public Person getFriend() {
        return friend;
    }


    public boolean hasFriend() {
        if (friend != null) {
            return true;
        }
        else{
            return false;
        }
    }
}