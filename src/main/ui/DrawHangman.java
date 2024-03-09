package ui;

// Class taking care of printing hangman as well as start image
public class DrawHangman {

    public DrawHangman() {

    }

    // Prints out start image 
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void startImage() {

        System.out.println("\r\n"
                + //
                " __        __     _                                   _             _   _ "
                +
                "                                              \r\n"
                + //
                " \\ \\      / /___ | |  ___  ___   _ __ ___    ___     | |_  ___     "
                +
                "| | | |  __ _  _ __    __ _  _ __ ___    __ _  _ __  \r\n"
                + //
                "  \\ \\ /\\ / // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\    | __|/ _ \\    | |_| | / _` || '_ "
                +
                "\\  / _` || '_ ` _ \\  / _` || '_ \\ \r\n"
                + //
                "   \\ V  V /|  __/| || (__| (_) || | | | | ||  __/    | |_| (_) |   |  _  || (_| || | | || (_| || "
                +
                "| | | | || (_| || | | |\r\n"
                + //
                "    \\_/\\_/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|     \\__|\\___/    |_| |_| \\__,_||_| |_| "
                +
                "\\__, ||_| |_| |_| \\__,_||_| |_|\r\n"
                + //
                "                                                                                         "
                +
                "|___/                          \r\n");
    }

    // EFFECTS: Prints the sticks of the hangman basis number of guesses left
    @SuppressWarnings({ "checkstyle:MethodLength", "checkstyle:SuppressWarnings" })
    public void drawFigure(int guessesLeft) {

        System.out.println("+----------+");

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

                System.out.println();
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

                System.out.println();

            }
        }

        System.out.println("+----------+");
        System.out.println();
    }
}
