import java.util.Iterator;

class MyCloneable implements Cloneable {
    private int num;

    public MyCloneable(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "MyCloneable: " + num;
    }

    @Override
    public int hashCode() {
        return num;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyCloneable)) {
            return false;
        }
        MyCloneable other = (MyCloneable) obj;
        return num == other.num;
    }

    @Override
    public MyCloneable clone() {
        try {
            return (MyCloneable) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Test 1 starts");
            test1();
        } catch (Exception e) {
            System.out.println("exception " + e);
        } finally {
            System.out.println("Test 1 done");
            System.out.println("--------------------------------------------");
        }

        try {
            System.out.println("Test 2 starts");
            test2();
        } catch (Exception e) {
            System.out.println("exception " + e);
        } finally {
            System.out.println("Test 2 done");
            System.out.println("--------------------------------------------");
        }

        try {
            System.out.println("Test 3 starts");
            test3();
        } catch (Exception e) {
            System.out.println("exception " + e);
        } finally {
            System.out.println("Test 3 done");
            System.out.println("--------------------------------------------");
        }

        try {
            System.out.println("Test 4 starts");
            test4();
        } catch (Exception e) {
            System.out.println("exception " + e);
        } finally {
            System.out.println("Test 4 done");
            System.out.println("--------------------------------------------");
        }

        try {
            System.out.println("Test 5 starts");
            testComprehensive();
        } catch (Exception e) {
            System.out.println("exception " + e);
        } finally {
            System.out.println("Test 5 done");
            System.out.println("--------------------------------------------");
        }
    }

    public static void test1() {
        IsraeliQueue<MyCloneable> queue = new IsraeliQueue<>();
        queue.add(new MyCloneable(1));
        queue.add(new MyCloneable(2));
        queue.add(new MyCloneable(3));

        IsraeliQueue<MyCloneable> clonedQueue = queue.clone();

        Iterator<MyCloneable> iterator = queue.iterator();
        Iterator<MyCloneable> clonedIterator = clonedQueue.iterator();

        while (iterator.hasNext() && clonedIterator.hasNext()) {
            MyCloneable fromOrg = iterator.next();
            MyCloneable fromCloned = clonedIterator.next();

            System.out.println(fromOrg);
            System.out.println(fromCloned);
            System.out.println(fromOrg.equals(fromCloned));
            System.out.println(fromOrg == fromCloned);
        }
    }

    public static void test2() {
        IsraeliQueue<MyCloneable> queue = new IsraeliQueue<>();
        try {
            queue.add(null);
        } catch (InvalidInputException e) {
            System.out.println("Exception caught: " + e);
        }
    }

    public static void test3() {
        IsraeliQueue<MyCloneable> queue = new IsraeliQueue<>();
        queue.add(new MyCloneable(1), new MyCloneable(4));
        queue.add(new MyCloneable(2));
        queue.add(new MyCloneable(3));
        queue.add(new MyCloneable(4), new MyCloneable(2));

        for (MyCloneable element : queue) {
            System.out.println(element);
        }
        System.out.println("Second iteration");
        for (MyCloneable element : queue) {
            System.out.println(element);
        }
    }

    public static void test4() {
        Park park = new Park("Super-Park");
        AmusementRide ride1 = new AmusementRide("Ferris Wheel", 3);
        AmusementRide ride2 = new AmusementRide("Gravity Falls", 2);
        AmusementRide ride3 = new AmusementRide("Roller Coaster", 5);

        park.addRide(ride1);
        park.addRide(ride2);
        park.addRide(ride3);

        park.addPersonToRide(ride1, new Person("Ploni Almoni 1", 1));
        park.addPersonToRide(ride1, new Person("Ploni Almoni 2", 2));
        park.addPersonToRide(ride1, new Person("Ploni Almoni 3", 3, new Person("Ploni", 1)));
        park.addPersonToRide(ride1, new Person("Ploni Almoni 4", 4, new Person("Ploni Almoni", 1)));

        park.addPersonToRide(ride3, new Person("Ploni Almoni 5", 5));
        park.addPersonToRide(ride3, new Person("Ploni Almoni 6", 6));
        park.addPersonToRide(ride3, new Person("Ploni Almoni 7", 7));

        park.startRides();
        System.out.println("Starting yet again.");

        park.startRides();
        System.out.println("Again?");

        park.startRides();
    }

    public static void testComprehensive() {
        // Create the park and amusement rides
        Park park = new Park("Adventure Park");
        AmusementRide ferrisWheel = new AmusementRide("Ferris Wheel", 3);
        AmusementRide rollerCoaster = new AmusementRide("Roller Coaster", 5);
        AmusementRide bumperCars = new AmusementRide("Bumper Cars", 4);

        // Add rides to the park
        park.addRide(ferrisWheel);
        park.addRide(rollerCoaster);
        park.addRide(bumperCars);

        // Create the Israeli queue
        IsraeliQueue<Person> queue = new IsraeliQueue<>();

        // Create people
        Person person1 = new Person("Alice", 1);
        Person person2 = new Person("Bob", 2);
        Person person3 = new Person("Charlie", 3);
        Person person4 = new Person("David", 4, person2); // David has Bob as a friend
        Person person5 = new Person("Eve", 5);

        // Add people to the queue
        queue.add(person1);
        queue.add(person2);
        queue.add(person3);
        queue.add(person4);
        queue.add(person5);

        // Add people to rides in the park
        try {
            park.addPersonToRide(ferrisWheel, person1);
            park.addPersonToRide(ferrisWheel, person2);
            park.addPersonToRide(rollerCoaster, person3);
            park.addPersonToRide(rollerCoaster, person4);
            park.addPersonToRide(bumperCars, person5);
        } catch (InvalidInputException e) {
            System.out.println("Exception when adding person to ride: " + e);
        }

        // Remove a person from the queue and peek
        System.out.println("Removed person: " + queue.remove());
        System.out.println("Person at the front of the queue: " + queue.peek());
        System.out.println("Final Queue:");
        for (Person p : queue) {
            System.out.println(p);
        }

        // Clone the queue and verify
        IsraeliQueue<Person> clonedQueue = queue.clone();
        System.out.println("Original Queue:");
        for (Person p : queue) {
            System.out.println(p);
        }
        System.out.println("Cloned Queue:");
        for (Person p : clonedQueue) {
            System.out.println(p);
        }

        // Remove a ride and start rides
        park.removeRide(rollerCoaster);
        try {
            park.startRides();
        } catch (EmptyQueueException e) {
            System.out.println("Exception when starting rides: " + e);
        }

        // Try removing an empty queue
        queue.remove(); // This should work if the queue isn't empty
        System.out.println("Queue size after removal: " + queue.size());

        // Final state of the queue
        System.out.println("Final Queue:");
        for (Person p : queue) {
            System.out.println(p);
        }
    }
}
