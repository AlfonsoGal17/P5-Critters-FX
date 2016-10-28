package assignment5;

import java.util.List;





public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE, SQUARE, TRIANGLE, DIAMOND, STAR
	}

	/*
	 * the default color is white, which I hope makes critters invisible by
	 * default If you change the background color of your View component, then
	 * update the default color to be the same as you background
	 * 
	 * critters must override at least one of the following three methods, it is
	 * not proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a
	 * non-filled shape, at least, that's the intent. You can edit these default
	 * methods however you need to, but please preserve that intent as you
	 * implement them.
	 */
	public javafx.scene.paint.Color viewColor() {
		return javafx.scene.paint.Color.WHITE;
	}

	public javafx.scene.paint.Color viewOutlineColor() {
		return viewColor();
	}

	public javafx.scene.paint.Color viewFillColor() {
		return viewColor();
	}

	public abstract CritterShape viewShape();

	private static String myPackage;
	private static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name. This assumes that Critter and its subclasses are
	// all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	protected String look(int direction, boolean steps) {
		return "";
	}

	/* rest is unchanged from Project 4 */

	private static java.util.Random rand = new java.util.Random();

	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	/*
	 * a one-character long string that visually depicts your critter in the
	 * ASCII interface
	 */
	public String toString() {
		return "";
	}

	private int energy = 0;

	protected int getEnergy() {
		return energy;
	}

	private int x_coord;
	private int y_coord;
	// added boolean to check if it has walked/run in a timestep;
	private boolean moved;

	/**
	 * Removes the energy cost to walk from calling Critter's energy, whether it
	 * can move or not If Critter has already moved in the current timeStep,
	 * only the penalty listed above is applied and nothing else happens Else if
	 * the Critter has not moved yet, then the penalty is applied AND the
	 * Critter moves in given direction. Moved flag is changed to true after
	 * Bottom of the method handles wrap around, so Critter is displayed
	 * correctly by DisplayWorld()
	 * 
	 * @param direction
	 */
	protected final void walk(int direction) {
		if (this.moved) {
			this.energy -= Params.walk_energy_cost;
		} else if (!this.moved) {
			this.energy -= Params.walk_energy_cost;
			switch (direction) {// 0,1,2,3,4,5,6,7
			case 0: // right
				this.x_coord += 1;
				this.y_coord += 0;
				break;
			case 1: // right - up
				this.x_coord += 1;
				this.y_coord -= 1;
				break;
			case 2: // up
				this.x_coord += 0;
				this.y_coord += 1;
				break;
			case 3: // left - up
				this.x_coord -= 1;
				this.y_coord -= 1;
				break;
			case 4: // left
				this.x_coord -= 1;
				this.y_coord += 0;
				break;
			case 5: // left - down
				this.x_coord -= 1;
				this.y_coord += 1;
				break;
			case 6: // down
				this.x_coord += 0;
				this.y_coord += 1;
				break;
			case 7: // right - down
				this.x_coord += 1;
				this.y_coord += 1;
				break;
			}
		}
		this.moved = true;
		// cases for overshooting (wrap around)
		// x overshoots
		if (this.x_coord > Params.world_width - 1) {
			this.x_coord -= Params.world_width;
		} // x undershoots
		else if (this.x_coord < 0) {
			this.x_coord += Params.world_height;
		}
		// y overshoots
		if (this.y_coord > Params.world_height - 1) {
			this.y_coord -= Params.world_height;
		} // y undershoots
		else if (this.y_coord < 0) {
			this.y_coord += Params.world_height;
		}

	}

	/**
	 * Removes the energy cost to run from calling Critter's energy, whether it
	 * can move or not If Critter has already moved in the current timeStep,
	 * only the penalty listed above is applied and nothing else happens Else if
	 * the Critter has not moved yet, then the penalty is applied AND the
	 * Critter moves in given direction. Moved flag is changed to true after
	 * Bottom of the method handles wrap around, so Critter is displayed
	 * correctly by DisplayWorld()
	 * 
	 * @param direction
	 */
	protected final void run(int direction) {
		if (this.moved) {
			this.energy -= Params.run_energy_cost;
		} else if (!this.moved) {
			this.energy -= Params.run_energy_cost;
			switch (direction) {// 0,1,2,3,4,5,6,7
			case 0: // right
				this.x_coord += 2;
				this.y_coord += 0;
				break;
			case 1: // right - up
				this.x_coord += 2;
				this.y_coord -= 2;
				break;
			case 2: // up
				this.x_coord += 0;
				this.y_coord += 2;
				break;
			case 3: // left - up
				this.x_coord -= 2;
				this.y_coord -= 2;
				break;
			case 4: // left
				this.x_coord -= 2;
				this.y_coord += 0;
				break;
			case 5: // left - down
				this.x_coord -= 2;
				this.y_coord += 2;
				break;
			case 6: // down
				this.x_coord += 0;
				this.y_coord += 2;
				break;
			case 7: // right - down
				this.x_coord += 2;
				this.y_coord += 2;
				break;
			}
		}
		this.moved = true;
		// cases for overshooting (wrap around)
		// x overshoots
		if (this.x_coord > Params.world_width - 1) {
			this.x_coord -= Params.world_width;
		} // x undershoots
		else if (this.x_coord < 0) {
			this.x_coord += Params.world_height;
		}
		// y overshoots
		if (this.y_coord > Params.world_height - 1) {
			this.y_coord -= Params.world_height;
		} // y undershoots
		else if (this.y_coord < 0) {
			this.y_coord += Params.world_height;
		}

	}

	/**
	 * This method takes in a Critter object and direction number. The energy of
	 * the Critter calling this class will have their energy cut in half
	 * (rounded up) and the remaining half (rounded down) is given to its
	 * offspring. The offspring then is shifted 1 space away from its parent
	 * using the Walk() function. Before walk() is called the walk cost energy
	 * is added to the offspring so that energy remains half of parents energy
	 * when spawned
	 * 
	 * @param offspring
	 * @param direction
	 */
	protected final void reproduce(Critter offspring, int direction) {
		if (this.energy < Params.min_reproduce_energy) {
			return;
		} else {
			offspring.energy = (int) Math.floor(this.energy / 2);
			this.energy = (int) Math.ceil(this.energy / 2);
			offspring.x_coord = this.x_coord;
			offspring.y_coord = this.y_coord;
			offspring.energy += Params.walk_energy_cost;
			offspring.walk(direction);
			babies.add(offspring);
		}
	}

	public abstract void doTimeStep();

	public abstract boolean fight(String oponent);

	public static void worldTimeStep() {
		// FIRST do doTimeStep on each bug
				for (Critter a : population) {
					a.doTimeStep();
				}
				// SECOND resolve position conflicts
				for (Critter a : population) { // check all critters for conflicts
					for (Critter b : population) {
						if (a == b) { /// skip when a and b are the same bug
							continue;
						}
						// critters are in same point
						if (a.x_coord == b.x_coord && a.y_coord == b.y_coord) {
							// only if they're still alive after thier timeStep
							if (a.energy > 0 && b.energy > 0) {

								// int values used to set back critter to original
								// position if flee fails
								int prevA_XCoord = a.x_coord;
								int prevA_YCoord = a.y_coord;
								int prevB_XCoord = b.x_coord;
								int prevB_YCoord = b.y_coord;

								boolean aFight = a.fight(b.toString());// check whether
																		// they want to
																		// fight
								boolean bFight = b.fight(a.toString());
								int aRoll;
								int bRoll;

								// trying to escape
								if (!(aFight)) {
									int x = a.x_coord;
									int y = a.y_coord;
									for (Critter z : population) {
										if (a == z) {
											continue;
										} else if (x == z.x_coord && y == z.y_coord) {
				
											a.x_coord = prevA_XCoord;
											a.y_coord = prevA_YCoord;
										}
									}
								}
								if (!(bFight)) {
									int x = b.x_coord;
									int y = b.y_coord;
									for (Critter z : population) {
										if (b == z) {
											continue;
										} else if (x == z.x_coord && y == z.y_coord) {
									
											b.x_coord = prevB_XCoord;
											b.y_coord = prevB_YCoord;
										}
									}
								}

								// both Critters Flee and end up on same spot
								if ((!aFight && !bFight) && (a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
									b.x_coord = prevB_XCoord;
									b.y_coord = prevB_YCoord;
								}

								// if = then failed to escape
								if ((a.x_coord == b.x_coord && a.y_coord == b.y_coord)) {
									// if both critters are still alive
									if (a.energy > 0 && b.energy > 0) {
										if (aFight) {
											aRoll = Critter.getRandomInt(a.energy);
										} else {
											aRoll = 0;
										}
										if (bFight) {
											bRoll = Critter.getRandomInt(b.energy);
										} else {
											bRoll = 0;
										}
										if (aRoll > bRoll) { // a wins!!
											a.energy += b.energy / 2;
											b.energy = 0; // makes sure b will not fight
															// again
										} else if (bRoll > aRoll) {// b wins!
											b.energy += a.energy / 2;
											a.energy = 0; // makes sure a will not fight
															// again
										} else {// roll same number A wins!
											a.energy += b.energy / 2;
											b.energy = 0;
										}

									}
								}

							}
						}
					}
				}

				// THIRD apply rest energy cost
				for (Critter a : population) {
					a.energy -= Params.rest_energy_cost;
				}
				// FOURTH remove dead bugs
				java.util.ArrayList<Critter> deadCrit = new java.util.ArrayList<Critter>();
				for (Critter a : population) {
					if (a.energy <= 0) {
						deadCrit.add(a); // collects dead bugs
					}
				}
				population.removeAll(deadCrit);
				// FIFTH put children with population
				population.addAll(babies);
				babies.clear();

				// SIXTH generate Algae(food source)
				for (int i = 0; i < Params.refresh_algae_count; i++) {
					try {
						Critter.makeCritter(myPackage + ".Algae");
					} catch (InvalidCritterException e) {
						e.printStackTrace();
					}
				}
				// Reset Moved boolean for all Critters
				for (Critter x : population) {
					x.moved = false;
				}
			
	}

	public static void displayWorld() {
		// Prints top border
		System.out.print("+");
		for (int z = 0; z < Params.world_width; z++) {
			System.out.print("-");
		}
		System.out.println("+");

		// Prints side borders
		int heightCounter = 0;
		for (int y = 0; y < Params.world_width + 2; y++) {
			if (heightCounter < Params.world_height) {
				if (y == 0) {
					System.out.print("|");
				} else if (y == Params.world_width + 1) {
					System.out.println("|");
					y = -1;
					heightCounter++;
				}
				// print Critters
				else {

					boolean critterHere = false;
					boolean printedCritter = false;
					for (int counter = 0; counter < population.size(); counter++) {
						if (population.get(counter).x_coord == y - 1
								&& population.get(counter).y_coord == heightCounter) {
							if (!printedCritter) {
								System.out.print(population.get(counter));
								critterHere = true;
								printedCritter = true;
							}
						}
					}
					if (!critterHere) {
						System.out.print(" ");
					}
				}
			}
		}

		// Prints bottom border
		System.out.print("+");
		for (int z = 0; z < Params.world_width; z++) {
			System.out.print("-");
		}
		System.out.print("+");
	}

	/*
	 * create and initialize a Critter subclass critter_class_name must be the
	 * name of a concrete subclass of Critter, if not an InvalidCritterException
	 * must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		try {
			// returns class associated with string name
			// System.out.println("enter make");
			Class<?> myClass = Class.forName(critter_class_name);
			// System.out.println("done with forName");
			// make new instance of critter_class_name critter
			Critter newCritter = (Critter) myClass.newInstance();
			// System.out.println("done with instance");
			// set parameters for critter
			newCritter.x_coord = getRandomInt(Params.world_width);
			newCritter.y_coord = getRandomInt(Params.world_height);
			newCritter.energy = Params.start_energy;
			// added parameter for checking if walked or ran in a timeStep
			newCritter.moved = false;
			// add critter to world
			population.add(newCritter);
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (NoClassDefFoundError e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
	}

	/**
	 * Gets a list of critters of a specific type.
	 * 
	 * @param critter_class_name
	 *            What kind of Critter is to be listed. Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		try {
			Class<?> critClass = Class.forName(critter_class_name);
			for (Critter a : population) {
				if (critClass.isInstance(a)) {
					result.add(a);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (NoClassDefFoundError e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (Exception e) {
			throw new InvalidCritterException(critter_class_name);
		}
		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * 
	 * @param critters
	 *            List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string, 1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();
	}

	/*
	 * the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this
	 * class and then use the setter functions contained here.
	 * 
	 * NOTE: you must make sure thath the setter functions work with your
	 * implementation of Critter. That means, if you're recording the positions
	 * of your critters using some sort of external grid or some other data
	 * structure in addition to the x_coord and y_coord functions, then you MUST
	 * update these setter functions so that they correctup update your
	 * grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}

		/*
		 * This method getPopulation has to be modified by you if you are not
		 * using the population ArrayList that has been provided in the starter
		 * code. In any case, it has to be implemented for grading tests to
		 * work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using
		 * the babies ArrayList that has been provided in the starter code. In
		 * any case, it has to be implemented for grading tests to work. Babies
		 * should be added to the general population at either the beginning OR
		 * the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
	}

	

}
