package Phaser_onAdvance;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser
{
	// override the onAdvance method
	@Override
	protected boolean onAdvance(int phase, int registeredParties)
	{
		switch (phase)
		{
			case 0: return studentsArrived();
			case 1: return finishFirstExercise();
			case 2: return finishSecondExercise();
			case 3: return finishExam();
			default: return true;
		}
	}
	
	private boolean studentsArrived()
	{
		System.out.printf("Phaser: the exam is going to start\n");
		System.out.printf("Phaser: we have %d students\n", getRegisteredParties());
		return false;
	}
	
	private boolean finishFirstExercise()
	{
		System.out.printf("Phaser: all students have finished the first exercise\n");
		System.out.println("Phaser: start the second exercise");
		return false;
	}
	
	private boolean finishSecondExercise()
	{
		System.out.println("Phaser: all students have finished the second exercise");
		System.out.println("Phaser: start the last exercise");
		return false;
	}
	
	private boolean finishExam()
	{
		System.out.println("Phaser: all students have finished the exam");
		return true;
	}
}
