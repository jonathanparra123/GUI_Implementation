package CS2410.assn2;

public class Fizzy
{
    private static String multi3 = "Fizz";
    private static String multi5 = "Buzz";
    private static String multiB = "FizzBuzz";

    public static void main(String[] args)
    {
        for (int i = 1; i < 101; i++)
        {

           if (Fizz(i)) {System.out.println(multi3);};
                 if (Buzz(i)) {System.out.println(multi5);};
                 if (FizzBuzz(i)){System.out.println(multiB);};
                 if(other(i)){System.out.println(i);};


        }

    }

public static boolean Fizz(int i)
{
    int calculator = i % 3;
    int calculator2 = i % 5;

    if (calculator == 0 && calculator2 != 0) return true;
    else return false;
}

public static boolean Buzz(int i)
{
    int calculator = i % 3;
    int calculator2 = i % 5;
    if (calculator2 == 0 && calculator != 0) return true;
    else return false;
}

public static boolean FizzBuzz(int i)
{
    int calculator = i % 3;
    int calculator2 = i % 5;

    if(calculator == 0 && calculator2 == 0) return true;
    else return false;
}

public static boolean other (int i )
{
    int calculator = i % 3;
    int calculator2 = i % 5;

    if (calculator != 0 && calculator2 != 0) return  true;
    else  return false;
}

}



