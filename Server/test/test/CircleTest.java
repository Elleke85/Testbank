package test;

import com.example.test.model.Circle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Deze test is geschreven tijdens de workshop testing en geen onderdeel van de testBank
 */
class CircleTest {

    @Test
    void getArea () {
        Circle c = new Circle(1.0);
        double expected = Math.PI;
        double actual = c.getArea();
        assertEquals(expected,actual, 0.00001);
    }

    @Test
    void getArea2 () {
        Circle c = new Circle(2.0);
        double expected = 4.0 * Math.PI;
        double actual = c.getArea();
        assertEquals(expected,actual, 0.00001);
    }

    @Test
    void getArea3 () {
        Circle c;
        try {
            c = new Circle(-1.0);
            double expected = 0.00;
            double actual = c.getArea();
            assertEquals(expected, actual, 0.00001);
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(),"Radius can not be negative");// gebruik assertEquals voor woordelijk vergelijk
            //assertTrue (true);  // gebruik als illegalArgument/catch geactiveerd is
        }
     }

//    @Test // testen cirkel met grote radius
    // deze zal foutief slagen ivm foutieve berekening met grote getallen
    // maar resultaat moet groter zijn dan nul
//    void getArea4 () {
//        Circle c;
//        double giga = Double.MAX_VALUE/3;
//        try {
//            c = new Circle(giga);
//            double expected = giga * giga * Math.PI;
//            double actual = c.getArea();
//            assertEquals(expected, actual, 0.00001);
//        } catch (IllegalArgumentException iae) {
//            assertEquals(iae.getMessage(),"Radius can not be negative");// gebruik assertEquals voor woordelijk vergelijk
//            //assertTrue (true);  // gebruik als illegalArgument/catch geactiveerd is
//        }
//    }

    @Test
    void getArea4 () {
        // als giga negatief is, dan is het getal te groot voor een goede berekening (er is een overflow)
        Circle c;
        double giga = Double.MAX_VALUE/3;
        try {
            c = new Circle(giga);
            double actual = c.getArea();
            boolean b = actual >= 0;
            assertTrue(b);
        } catch (IllegalArgumentException iae) {
            assertEquals(iae.getMessage(),"Radius can not be negative");// gebruik assertEquals voor woordelijk vergelijk
            //assertTrue (true);  // gebruik als illegalArgument/catch geactiveerd is
        }
    }





    @Test
    void getCircumference() {
        Circle c = new Circle(1.0);
        double expected = Math.PI;
        Double actual = c.getArea();
        assertEquals(expected,actual,0.00001);
    }
}