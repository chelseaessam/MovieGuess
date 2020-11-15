import java.util.*;
import java.io.File;
public class Game {
    //Get movie name without space in arraylist
    public static ArrayList movieName(String movie)
    {
        ArrayList movieChosen=new ArrayList();
        for(int i=0;i<movie.length();i++)
        {
            if(!isletter(movie.charAt(i)))
            {
                continue;
            }
            else
                movieChosen.add(movie.charAt(i));
        }
        return movieChosen;
    }
    //choose random movie from file containing movie names
    public static String chooseMovie(File myfile)
    {
        Scanner filescanner;
        Random random=new Random();
        int line=0;
        ArrayList movies=new ArrayList();
        try
        {
            filescanner=new Scanner(myfile);
            while(filescanner.hasNextLine())
            {
                movies.add(filescanner.nextLine());
                line++;
            }
            int index=random.nextInt(line);
            String chosenMovie=(String)movies.get(index);
            return chosenMovie;


        }
        catch (Exception e)
        {
            return e.getMessage();
        }
    }
    //display movie to be guessed
    public static void displayMovie(ArrayList movie,ArrayList indexGuessed)
    {
        System.out.print("You are guessing : ");
        for(int i=0;i<movie.size();i++)
        {

            if(indexGuessed.contains(i))
            {
                System.out.print(movie.get(i));
            }
            else
                System.out.print("-");

        }
        System.out.println("");
    }
    //display wrong guessed letters
    public static void wrongLetters(ArrayList wrongLetters)
    {
        System.out.print("You have guessed ("+wrongLetters.size()+") wrong letters:");
        for(int i=0;i<wrongLetters.size();i++)
        {
            System.out.print(wrongLetters.get(i)+" ");
        }
        System.out.println("");

    }
    //check if wrong letter is already guessed before
    public static boolean exists(ArrayList wrongLetters,char letter)
    {
        for(int i=0;i<wrongLetters.size();i++)
        {
            char letterList=(char)wrongLetters.get(i);
            if(letter==letterList)
            {
                return true;
            }
        }return false;
    }
    //check if right letter is already guessed before
    public static boolean exists(ArrayList indexGuessed,int index)
    {
        for(int i=0;i<indexGuessed.size();i++)
        {
            int indexList=(int)indexGuessed.get(i);
            if(index==indexList)
            {
                return true;
            }
        }return false;
    }
    //check if guess is right or wrong or already guessed wrong
    public static int guess(String movie,ArrayList movieList,char letter,ArrayList indexGuessed,ArrayList wrongLetters)
    {
        //1 means true guess
        //0 means false guess first time
        //-1 means false guess multiple time
        int userGuess=0;
        if(movie.indexOf(letter)!=-1)
        {
            for(int i=0;i<movieList.size();i++)
            {
                char currentletter=(char)movieList.get(i);
                if(letter==currentletter)
                {
                    if(!exists(indexGuessed,i))
                    indexGuessed.add(i);
                    userGuess=1;
                }
            }

        }
        else
        {
            if(!exists(wrongLetters,letter))
            {
            wrongLetters.add(letter);
            userGuess=0;
            }
            else
                userGuess=-1;

        }
       return userGuess;
    }
    //to check if char is alphabetic
    public static boolean isletter(char input)
    {
        if((input>=65 && input<=90) || (input>=97 && input<=122))
            return true;
        else
            return false;
    }


}
