import java.util.Scanner;

public class main {
    public static Scanner Read = new Scanner (System.in);
    public static searchEngine SEQ = new searchEngine();

    public static int menu()
    {
        System.out.println("-----Searchify-----");
        System.out.println("1: Boolean Retrieval. ");
        System.out.println("2: Ranked Retrieval.");
        System.out.println("3: Indexed Documents.");
        System.out.println("4: Indexed Tokens.");
        System.out.println("5: Exit.");

        System.out.println("Welcome to Searchify, Enter your choice or 5 to Exit: ");
        int c1 = Read.nextInt();
        return c1;
    }

    public static void BooleanRetrievalMenu() {
      
        int c2 ;
        System.out.println("###################Boolean Retrieval####################");

        System.out.println("1: index");
        System.out.println("2: inverted index");
        System.out.println("3: inverted index with BST");
        System.out.println("4: inverted index with AVL");
        System.out.println("Enter your choice: ");
        c2 = Read.nextInt();
        Read.nextLine();
        
        System.out.print("Enter your Boolean query: ");
      String BooleanQueries=Read.nextLine();

        System.out.println("#Q " + BooleanQueries);

        System.out.print("Result doc IDs: ");
        SEQ.Boolean_Retrieval(BooleanQueries, c2).print();
        System.out.println("\n");
    }
    int c3;
    public static void RankedRetrievalmMenu() {
        
        int c3;
        System.out.println("########### Ranked Retrieval ###########");
        System.out.println("1: index");
        System.out.println("2: inverted index");
        System.out.println("3: inverted index With BST");
        System.out.println("4: inverted index With AVL");
        System.out.println("Enter your choice: ");
         c3 = Read.nextInt();
         Read.nextLine();

      
        System.out.print("Enter your Ranked query: ");
        String RankedQueries =Read.nextLine();

         System.out.println("##Q " + RankedQueries);
        SEQ.Ranked_Retrieval(RankedQueries, c3);
    }
    

    public static void IndexedDocumentsMenu()
    {
        System.out.println("######## Indexed Documents ######## ");
        System.out.println("Indexed Documents " + SEQ.index.indices.length);
    }

    public static void IndexedTokensMenu()
    {
        System.out.println("######### Indexed Tokens #########");
        System.out.println("tokens " + SEQ.tokens);
    }

    public static void main(String[] args) {

        SEQ.LoadData();

       
        int Menu;

        do {
            Menu = menu();
            switch (Menu)
            {
                //Boolean Retrieval: to enter a Boolean query that will return a set of unranked documents
                case 1:
                    BooleanRetrievalMenu();
                    break;

                //Ranked Retrieval: to enter a query that will return a ranked list of documents with their scores
                case 2:
                    RankedRetrievalmMenu();
                    break;

                //Indexed Documents: to show number of documents in the index
                case 3:
                    IndexedDocumentsMenu();
                    break;

                //Indexed Tokens: to show number of vocabulary and tokens in the index
                case 4:
                    IndexedTokensMenu();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("try again, choose from 1-4 or 5 to Exit: ");
            }
        } while (Menu != 5);
    }

}

