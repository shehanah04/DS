import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner (System.in);
    public static searchEngine SE = new searchEngine();




    public static int menu()
    {
        System.out.println("1. Boolean Retrieval. ");
        System.out.println("2. Ranked Retrieval.");
        System.out.println("3. Indexed Documents.");
        System.out.println("4. Indexed Tokens.");
        System.out.println("5. Exist.");

        System.out.println("enter choice");
        int choice = input.nextInt();
        return choice;
    }


    public static void Boolean_Retrieval_menu()
    {
        String [] Questions = { "market AND sports"
                ,"weather AND warming"
                ,"business AND world"
                ,"weather OR warming"
                ,"market OR sports"
                ,"market OR sports AND warming"};

        int choice1 ;
        System.out.println("################### Boolean Retrieval ####################");

        System.out.println("1. index");
        System.out.println("2. inverted index");
        System.out.println("3. inverted index using BST");
        System.out.println("4. inverted index using AVL");
        System.out.println("enter your choice");
        choice1 = input.nextInt();

        System.out.print("Q#: ");
        for ( int i = 0 ; i < Questions.length; i++)
        {
            String str = Questions[i];
            // 1 inverted index List , 2 inverted index AVL

            System.out.println(str);
            System.out.print("Result doc IDs: ");
            SE.Boolean_Retrieval(str, choice1 ).print();
            System.out.println("\n");
        }
    }

    public static void Ranked_Retrieval_menu()
    {
        String [] Questions = { "market sports"
                ,"weather warming"
                ,"business world market"};

        System.out.println("######## Ranked Retrieval ######## ");
        System.out.println("1. index");
        System.out.println("2. inverted index");
        System.out.println("3. inverted index using BST");
        System.out.println("4. inverted index using AVL");
        System.out.println("enter your choice");
        int choice2 = input.nextInt();

        System.out.print("Q#: ");
        for ( int i = 0 ; i < Questions.length; i++)
        {
            String str = Questions[i];

            System.out.println("## Q: " + str);
            SE.Ranked_Retrieval(str, choice2);
            System.out.println("\n");
        }
    }

    public static void Indexed_Documents_menu()
    {
        System.out.println("######## Indexed Documents ######## ");
        System.out.println("Indexed Documents " + SE.index.indexes.length);
    }

    public static void Indexed_Tokens_menu()
    {
        System.out.println("######## Indexed Tokens ######## ");
        System.out.println("tokens " + SE.tokens);
    }

    public static void main(String[] args) {

        SE.LoadData();

        // TODO code application logic here
        int choice;

        do {
            choice = menu();
            switch (choice)
            {
                //Boolean Retrieval: to enter a Boolean query that will return a set of unranked documents
                case 1:
                    Boolean_Retrieval_menu();
                    break;

                //Ranked Retrieval: to enter a query that will return a ranked list of documents with their scores
                case 2:
                    Ranked_Retrieval_menu();
                    break;

                //Indexed Documents: to show number of documents in the index
                case 3:
                    Indexed_Documents_menu();
                    break;

                //Indexed Tokens: to show number of vocabulary and tokens in the index
                case 4:
                    Indexed_Tokens_menu();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("bad choice, try again!");
            }
        } while (choice != 5);
    }

}

