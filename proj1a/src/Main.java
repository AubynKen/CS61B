public class Main {
    public static void main(String[] args) {
        ArrayDeque<Integer> myDeque = new ArrayDeque<Integer>();
//        System.out.println("Should print out true : " + myDeque.isEmpty());
//        for (int i = 0; i < 20; i++) {
//            myDeque.addLast(i);
//        }
//        for (int i = -1; i > -20; i--) {
//            myDeque.addFirst(i);
//        }
//        myDeque.printDeque();
//        for (int i = 0; i < 30; i++) {
//            myDeque.removeFirst();
//        }
        for (int i = 0; i < 256; i++) {
            myDeque.addFirst(i);
        }
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.removeLast());
    }
}
