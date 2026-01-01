package global;

import java.util.Scanner;

import classes.RealEstate;

public class Globals {
    public final static Scanner SCANNER = new Scanner(System.in);

    public static RealEstate realEstate = new RealEstate();

    public static boolean exitFromSoft = false;
}
