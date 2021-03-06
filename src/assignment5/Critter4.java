/* CRITTERS 
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * Alfonso Galindo
 * ag49477
 * 16450
 * Nicole Muzquiz
 * ngm339
 * 16460
 * Slip days used: <0>
 * Fall 2016
 */
package assignment5;
/*
 * @author Alfonso Galindo
 * 
 * This critter always walks in the UP positino,
 * if and only if its energy is the same as that 
 * needed to reproduce, it will reporduce sacrificing 
 * itself.
 * always look up
 * This is a mean critter, it always wants to figt
 */

import javafx.scene.paint.Color;

public class Critter4 extends Critter{
	@Override
	public String toString() { return "4"; }
	@Override
	public void doTimeStep() {
		//will always walk up
		walk(0);
		if(getEnergy() == Params.min_reproduce_energy){
			reproduce(new Critter4(), 0);
		}
	}

	@Override
	public boolean fight(String oponent) {
		look(0,true);
		return true;
	}
	@Override
	public CritterShape viewShape() {
		// TODO Auto-generated method stub
		return CritterShape.DIAMOND;
	}
	@Override
	public Color viewFillColor() { return Color.AQUA; }

}
