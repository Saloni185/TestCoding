package src;

import jdk.jfr.StackTrace;

public class Rover {
    public static final Integer N = 1;
    public static final Integer E = 2;
    public static final Integer S = 3;
    public static final Integer W = 4;
    Integer x = 0;
    Integer y = 0;
    Integer facing = N;
    public Rover() {
    }
    public void setPosition(Integer x, Integer y, Integer facing) {
        this.x = x;
        this.y = y;
        this.facing = facing;
    }
    public void printPosition() {
        char dir = 'N';
        if (facing == 1) {
            dir = 'N';
        } else if (facing == 2) {
            dir = 'E';
        } else if (facing == 3) {
            dir = 'S';
        } else if (facing == 4) {
            dir = 'W';
        }
        System.out.println(x+" " +y+" "+ dir);
    }
    public void process(String commands) {
        for (int idx = 0; idx < commands.length(); idx++ ) {
            process(commands.charAt(idx));
        }
    }
    private void process(Character command) {
        if (command.equals('L')) {
            turnLeft();
        } else if (command.equals('R')) {
            turnRight();
        } else if (command.equals('M')) {
            move();
        } else {
            throw new IllegalArgumentException("Speak in Mars language, please!");
        }
    }
    private void move() {
        if (facing == N) {
           y = this.y;
        } else if (facing == E) {
           x= this.x ;
        } else if (facing == S) {
           y= this.y--;
        } else if (facing == W) {
           x= this.x--;
        }
    }
    private void turnLeft() {
        facing = (facing - 1) < N ? W : facing - 1;
    }
    private void turnRight() {
        facing = (facing +1) > W ? N : facing+1;
    }

    @Test
    public void testInitialPosition() {
        Rover rover = new Rover();
        rover.setPosition(1, 2, Rover.N);
        Assert.assertEquals(1, rover.x);
        Assert.assertEquals(2, rover.y);
        Assert.assertEquals(Rover.N, rover.facing);
    }

    @Test
    public void testMove() {
        Rover rover = new Rover();
        rover.setPosition(1, 2, Rover.N);
        rover.move();
        Assert.assertEquals(1, rover.x);
        Assert.assertEquals(3, rover.y);
        Assert.assertEquals(Rover.N, rover.facing);
    }

    @Test
    public void testScenario() {
        Rover rover = new Rover();
        rover.setPosition(1, 2, Rover.N);
        rover.process("LMLMLMLMM");
        Assert.assertEquals(1, rover.x);
        Assert.assertEquals(3, rover.y);
        Assert.assertEquals(Rover.N, rover.facing);

        rover.setPosition(3, 3, Rover.E);
        rover.process("MMRMMRMRRM");
        Assert.assertEquals(5, rover.x);
        Assert.assertEquals(1, rover.y);
        Assert.assertEquals(Rover.E, rover.facing);
    }

    public static void main(String args[]) {
        Rover rover = new Rover();
        rover.setPosition(1, 2, N);
        rover.process("LMLMLMLMM");
        rover.printPosition(); // prints 1 3 N
        rover.setPosition(3, 3, E);
        rover.process("MMRMMRMRRM");
        rover.printPosition(); // prints 5 1 E
    }
}