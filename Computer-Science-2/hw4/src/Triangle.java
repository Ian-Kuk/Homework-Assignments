public class Triangle implements Polygon{
    private double a;
    private double b;
    private double c;
    public Triangle(){
        a = 3;
        b = 4;
        c = 5;
    }
    public Triangle(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public String toString(){
        return ("Triangle edge lengths: " + a +", " + b +", " + c);
    }
    @Override
    public double area(){
        double s = (a+b+c)/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }
    @Override
    public double perimeter(){
        return (a + b +c);
    }


}
