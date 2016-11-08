package assignment5;
import javafx.scene.shape.Circle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Painter {
	
	
	
	public static Polygon createTriangle(double x, double y, double side) {
	    Polygon triangle = new Polygon();
	    triangle.setLayoutX(x);
	    triangle.setLayoutY(y);
	    //3 points
	    triangle.getPoints().addAll(
	            x, (-(Math.sqrt((side*side)-((side/2)*(side/2)))/2)),
	            (side/2), Math.sqrt((side*side)-((side/2)*(side/2)))/2,
	            (-(side/2)), Math.sqrt((side*side)-((side/2)*(side/2)))/2);
	    return triangle;
	}
	
	public static Polygon createDiamond(double x, double y, double side) {
	    Polygon diamond = new Polygon();
	    diamond.setLayoutX(x);
	    diamond.setLayoutY(y);
	    //4 points
	    diamond.getPoints().addAll(
	            x, (-(Math.sqrt((side*side)-((side/2)*(side/2)))/2)),
	            (side/2), Math.sqrt((side*side)-((side/2)*(side/2)))/2,
	            x, Math.sqrt((side*side)-((side/2)*(side/2))/2),
	            (-(side/2)), Math.sqrt((side*side)-((side/2)*(side/2)))/2
	            );
	    return diamond;
	}
	
	public static Polygon createStar(double x, double y, double side) {
	    Polygon star = new Polygon();
	    star.setLayoutX(x);
	    star.setLayoutY(y);
	    //10 points
	    star.getPoints().addAll( 
	    		x, side,
	    		side/2, x,
	    		side, side,
	    		x, side/2,
	    		side, side/2
	            );
	    return star;
	}
}
