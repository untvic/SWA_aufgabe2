package programs;

import ringpuffer.RingklasseVU;

class main {
    public static void main(String[] args) {
        RingklasseVU<String> t = new RingklasseVU<String>(2,false,true);
        t.add("1");
        t.add("2");
        System.out.println("Array is:");
        t.show();
        t.peek();
        t.poll();
        System.out.println("Array is:");
        t.show();
        t.remove();
        t.add("3");
        t.add("4");
        System.out.println("Array is:");
        t.show();
        t.offer("5");
        t.offer("6");
        t.show();
    }
}