package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {

    private int choice;
    private String mode;
    private String variantMode;
    private String classicDifficulty;
    private Scanner input = new Scanner(System.in);

    public InputHandler() {

        chooseMode();

        if (mode == "Classic") {

            new ClassicHangman(chooseClassicDifficulty());

        } else if (mode == "Variant") {

            chooseVariantMode();

        }

    }

    public void chooseMode() {

        System.out.println("Game Mode:" + "\n");
        System.out.println("1. Classic" + "\n");
        System.out.println("2. Variant" + "\n");

        choice = input.nextInt();

        if (choice == 1) {

            setMode("Classic");

        } else if (choice == 2) {

            setMode("Variant");

        } else {

            input.nextLine();
            System.out.println("Invalid Input!");

            chooseMode();

        }

        System.out.println("You chose: " + getMode() + "\n");

    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public String chooseClassicDifficulty() {

        System.out.println("Difficulty:" + "\n");
        System.out.println("1. Rookie" + "\n");
        System.out.println("2. Novice" + "\n");
        System.out.println("3. Master" + "\n");

        choice = input.nextInt();

        ArrayList<Integer> validInputs = new ArrayList<Integer>();

        validInputs.add(1);
        validInputs.add(2);
        validInputs.add(3);

        while (true) {

            if (validInputs.contains(choice)) {

                if (choice == 1) {

                    setClassicDifficulty("Rookie");

                } else if (choice == 2) {

                    setClassicDifficulty("Novice");

                } else if (choice == 3) {

                    setClassicDifficulty("Master");

                }

                break;


            } else {

                input.nextLine();
                System.out.println("Invalid Input!");

                chooseClassicDifficulty();

            }

        }

        System.out.println("You chose: " + getClassicDifficulty() + "\n");
        return getClassicDifficulty();

    }

    public void chooseVariantMode() {

        System.out.println("Choose Variant:" + "\n");
        System.out.println("1. Timed" + "\n");
        System.out.println("2. Survival" + "\n");

        choice = input.nextInt();

        ArrayList<Integer> validInputs = new ArrayList<Integer>();

        validInputs.add(1);
        validInputs.add(2);

        while (true) {

            if (validInputs.contains(choice)) {

                if (choice == 1) {

                    setVariantMode("Timed");

                } else if (choice == 2) {

                    setVariantMode("Survival");

                }

                break;

            } else {

                input.nextLine();
                System.out.println("Invalid Input!");

                chooseVariantMode();

            }

        }

        System.out.println("You chose: " + getVariantMode() + "\n");

    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public String getClassicDifficulty() {
        return classicDifficulty;
    }

    public void setClassicDifficulty(String classicDifficulty) {
        this.classicDifficulty = classicDifficulty;
    }

    public String getVariantMode() {
        return variantMode;
    }

    public void setVariantMode(String variantMode) {
        this.variantMode = variantMode;
    }
}
