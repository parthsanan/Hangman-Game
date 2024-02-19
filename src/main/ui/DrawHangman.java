package ui;

public class DrawHangman {

    public DrawHangman(int guessesLeft) {

        drawFigure(guessesLeft);

    }

    // EFFECTS: Prints the sticks of the hangman basis number of guesses left
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void drawFigure(int guessesLeft) {

        System.out.println("+----------+");

        while (true) {

            if (guessesLeft <= 6) {
                System.out.println("|          |");
            }

            if (guessesLeft <= 5) {
                System.out.println("|          O");
            }

            if (guessesLeft <= 4) {
                System.out.print("|         \\");

                if (guessesLeft <= 3) {

                    System.out.println(" /");

                } else {

                    System.out.println("");
                }
            }

            if (guessesLeft <= 2) {

                System.out.println("|          |");

            }

            if (guessesLeft <= 1) {

                System.out.print("|         /");

                if (guessesLeft <= 0) {

                    System.out.println(" \\");

                } else {

                    System.out.println("");

                }
            }

            System.out.println("+----------+");
            System.out.println("");

            break;

        }
    }
}
