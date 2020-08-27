package Tests;

import dataStructure.Node;
import org.junit.Before;
import org.junit.Test;
import utils.Point3D;
import static org.junit.Assert.*;

public class NodeTest {
    public static final double EPSILON = 0.00001;
    static Node[] arrNodes = new Node[10];
    static Point3D pointNotEquals=new Point3D(100,0,0);

    @Before
    public void BeforeEach(){
        arrNodes[0]=new Node(1,new Point3D(0, 0,0));
        arrNodes[1]=new Node(2,new Point3D(1, 0,0));
        arrNodes[2]=new Node(3,new Point3D(1, 2,0));
        arrNodes[3]=new Node(4,new Point3D(2, 1,0));
        arrNodes[4]=new Node(5,new Point3D(5, 4,0));
        arrNodes[5]=new Node(6,new Point3D(5, 7,0));
        arrNodes[6]=new Node(7,new Point3D(8, 9,0));
        arrNodes[7]=new Node(8,new Point3D(9, 10,0));
        arrNodes[8]=new Node(9,new Point3D(10, 10,0));
        arrNodes[9]=new Node(10,new Point3D(14, 90,0));
    }

    /**
     * Checks whether the value of the key returned from the function getKey() is the value we expect
     */
    @Test
    public void getKey() {
        for (int i = 0; i < arrNodes.length; i++) {
           assertEquals(i+1,arrNodes[i].getKey());
            assertNotEquals(100,arrNodes[i].getKey());
            assertNotNull(arrNodes[i].getKey());
        }
    }

    /**
     * Checks whether the function returns the Point3D correctly
     */
    @Test
    public void getLocation() {
        Point3D [] arrPointsExpected={new Point3D(0, 0,0),new Point3D(1, 0,0),new Point3D(1, 2,0),
                              new Point3D(2, 1,0),new Point3D(5, 4,0),new Point3D(5, 7,0),
                              new Point3D(8, 9,0),new Point3D(9, 10,0),new Point3D(10, 10,0),
                              new Point3D(14, 90,0)};

        for (int i = 0; i <arrNodes.length ; i++) {
            assertEquals(arrPointsExpected[i],arrNodes[i].getLocation());
            assertNotEquals(pointNotEquals,arrNodes[i].getLocation());
            assertNotNull(arrNodes[i].getLocation());
        }
    }

    /**
     * Checks whether the function determines the Point3D properly
     */
    @Test
    public void setLocation() {
        Point3D [] arrPointsExpected={new Point3D(1000, 100,0),new Point3D(18, 7,0),new Point3D(100, 2,0),
                new Point3D(772, 17,0),new Point3D(15, 14,0),new Point3D(51, 17,0),
                new Point3D(81, 91,0),new Point3D(110, 110,0),new Point3D(19, 100,0),
                new Point3D(114, 910,0)};

        for (int i = 0; i <arrNodes.length ; i++) {
            arrNodes[i].setLocation(arrPointsExpected[i]);
            assertEquals(arrPointsExpected[i],arrNodes[i].getLocation());
            assertNotEquals(pointNotEquals,arrNodes[i].getLocation());
            assertNotNull(arrNodes[i].getLocation());
        }
    }

    /**
     * Checks whether the function returns the Weight correctly
     */
    @Test
    public void getWeight() {

        for (int i = 0; i <arrNodes.length ; i++) {
            assertEquals(0.0,arrNodes[i].getWeight(),EPSILON);
            assertNotEquals(100.0,arrNodes[i].getWeight(),EPSILON);
            assertNotNull(arrNodes[i].getWeight());
        }
    }

    /**
     * Checks whether the function determines the Weight properly
     */
    @Test
    public void setWeight() {
        double[] expected ={100,99,91,98,97,1000,222,345,1111,345,677};
        for (int i = 0; i <arrNodes.length ; i++) {
            arrNodes[i].setWeight(expected[i]);
            assertEquals(expected[i],arrNodes[i].getWeight(),EPSILON);
            assertNotEquals(1009.0,arrNodes[i].getWeight(),EPSILON);
            assertNotNull(arrNodes[i].getWeight());
        }
    }

    /**
     * Checks whether the function returns the Info correctly
     */
    @Test
    public void getInfo() {
        for (int i = 0; i <arrNodes.length ; i++) {
            assertEquals("",arrNodes[i].getInfo());
            assertNotEquals("100",arrNodes[i].getInfo());
            assertNotNull(arrNodes[i].getInfo());
        }
    }

    /**
     * Checks whether the function determines the Info properly
     */
    @Test
    public void setInfo() {

        for (int i = 0; i <arrNodes.length ; i++) {
            arrNodes[i].setInfo("100,"+i);
            assertEquals("100,"+i, arrNodes[i].getInfo());
            assertNotEquals("",arrNodes[i].getInfo());
            assertNotNull(arrNodes[i].getInfo());
        }
    }

    /**
     * Checks whether the function returns the tag correctly
     */
    @Test
    public void getTag() {
        for (int i = 0; i <arrNodes.length ; i++) {
            assertEquals(0,arrNodes[i].getTag());
            assertNotEquals(100,arrNodes[i].getTag());
            assertNotNull(arrNodes[i].getTag());
        }
    }

    /**
     * Checks whether the function determines the Tag properly
     */
    @Test
    public void setTag() {
        for (int i = 0; i <arrNodes.length ; i++) {
            arrNodes[i].setTag(100);
            assertEquals(100, arrNodes[i].getTag());
            assertNotEquals(0,arrNodes[i].getTag());
            assertNotNull(arrNodes[i].getTag());
        }
    }

    /**
     * Checks whether the function prints according to the print format�
     * Because the tests we have BeforeEach the Node are created 60 times before and so when we run everything together
     * the Nodes keys will start from 61.Note (!) that if we run it alone the test will NOT work because the key starts from 1
     */
    @Test
    public void toStringTest()
    {
        String [] expected ={
        		"1, 0.0,0.0, 0.0,0.0, , 0 @",
        		"2, 1.0,0.0,0.0,0.0, ,0 @",
                "3,1.0,2.0,0.0,0.0, ,0 @"
        		,"4,2.0,1.0,0.0,0.0, ,0 @",
               "5,5.0,4.0,0.0,0.0, ,0 @",
               "6,5.0,7.0,0.0,0.0, ,0 @",
                "7,8.0,9.0,0.0,0.0, ,0 @",
                "8,9.0,10.0,0.0,0.0, ,0 @",
               "9,10.0,10.0,0.0,0.0, ,0 @",
               "10,14.0,90.0,0.0,0.0, ,0 @"
               };
        for (int i = 0; i <arrNodes.length ; i++) {
        	String test=arrNodes[i].toString().replace(" ", "");
        	String ex=expected[i].replace(" ", "");
            assertEquals(ex,test);
            assertNotEquals("100",arrNodes[i].toString());
        }
    }

}
