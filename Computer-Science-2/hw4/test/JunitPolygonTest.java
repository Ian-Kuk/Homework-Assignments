import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.Before;

public class JunitPolygonTest {

    Polygon[] shapes = new Polygon[2];

    @Before
    public void preparation() {
        shapes[0] = new Triangle(7.0, 9.0, 11.0);
        shapes[1] = new Rectangle(2.0, 4.0);
    }

    @Test
    public void triangleContents() {
        assertEquals("Checking if the triangle constructor works", shapes[0].toString(),
                "Triangle edge lengths: 7.0, 9.0, 11.0");
    }

    //Checking if the rectangle perimeter works
    @Test
    public void rectanglePerimeter() {
        assertEquals(12.0, shapes[1].perimeter(), 0.01);
    }

    //Checking if triangle area works
    @Test
    public void triangleArea() {
        assertEquals(31.41954009848012, shapes[0].area(), 0.01);
    }
}
    
