package es.datastructur.synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
//        for (int i = 0; i < 10; i++) {
//            arb.enqueue(i);
//            System.out.println(arb);
//        }
//        for (int i = 0; i < 10; i++)
//  {
//            arb.dequeue();
//            System.out.println(arb);
//        }

        arb.enqueue(0);
        for (int i = 0; i < 20; i++) {
            arb.dequeue();
            arb.enqueue(0);
            System.out.println(arb);
        }
    }
}
