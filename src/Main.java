import java.util.*;
import java.io.File;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        //input scanner
        Scanner userScanner = new Scanner(System.in);
        //file to be read
        File myfile = new File("movies.txt");
        while (true)
        {
            //movie chosen randomly
            String movie = Game.chooseMovie(myfile);
            //list of movie characters without spaces
            ArrayList movieList = Game.movieName(movie);
            //guessed indexes of letter of the movie name
            ArrayList indexGuessed = new ArrayList();
            //list of wrong letters guessed
            ArrayList wrongLetters = new ArrayList();
            //user input string
            String userLetter;
            //first character of user input
            char userChar = ' ';
            //boolean of user input
            boolean isletter = false;


            //number of guessed allowed
            int guess = 10;
            while (guess > 0) {
                //display movie to be guessed
                Game.displayMovie(movieList, indexGuessed);
                //display wrong letters guessed
                Game.wrongLetters(wrongLetters);
                //checking if input is letter before proceeding to handling the guess
                do {
                    System.out.print("Guess a letter :");
                    userLetter = userScanner.nextLine();
                    userLetter = userLetter.toLowerCase();
                    try {
                        userChar = userLetter.charAt(0);
                        isletter = Game.isletter(userChar);

                    } catch (Exception e) {
                        System.out.println("Please enter a letter!");
                    }

                }
                while (!isletter);
                //handling the guess
                int checkGuess = Game.guess(movie, movieList, userChar, indexGuessed, wrongLetters);
                if (checkGuess == 1) {
                    //if guess is right check if it is the final letter to be guessed
                    if (indexGuessed.size() == movieList.size()) {
                        System.out.println("You Win!");
                        System.out.println("You have guessed " + movie + " correctly!");
                        break;
                    }
                }
                //if guess is wrong and the wrong letter is entered for first time
                else if (checkGuess == 0) {
                    guess--;
                }

            }
            //if user failed to guess the movie and ran out of guesses
            if (guess == 0) {
                System.out.println("You have run out of guesses .The movie is :" + movie);
            }
            System.out.println("Do you want to play again?Y/N");
            String answer=userScanner.next();
            if(answer.equals("N") || answer.equals("n"))
                break;
        }
    }
}
