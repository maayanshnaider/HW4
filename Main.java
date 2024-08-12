import java.util.Iterator;

class MyCloneable implements Cloneable{
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
        if (!(obj instanceof MyCloneable)){
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
        }catch (Exception e){
            System.out.println("exception " + e);
        }finally {
            System.out.println("Test 1 done");
            System.out.println("--------------------------------------------");
        }
    }

    public static void test1(){
        IsraeliQueue<MyCloneable> queue = new IsraeliQueue<>();
        queue.add(new MyCloneable(1));
        queue.add(new MyCloneable(2));
        queue.add(new MyCloneable(3));

        IsraeliQueue<MyCloneable> clonedQueue = queue.clone();

        Iterator<MyCloneable> iterator = queue.iterator();
        Iterator<MyCloneable> clonedIterator = clonedQueue.iterator();

        while (iterator.hasNext() && clonedIterator.hasNext()){
            MyCloneable fromOrg = iterator.next();
            MyCloneable fromCloned = clonedIterator.next();

            System.out.println(fromOrg);
            System.out.println(fromCloned);
            System.out.println(fromOrg.equals(fromCloned));
            System.out.println(fromOrg == fromCloned);
        }
    }
}
