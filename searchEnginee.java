import java.util.Scanner;
import java.io.File;


public class searchEnginee {
    public static Scanner Read = new Scanner (System.in);
    private int tokens =0;
    private int vocabularies =0;

    private index index;
    private InvertedIndex invertedindex;
    private invertedIndexBST invertedindexBST;
    private invertedIndexAVL invertedindexAVL;
   

    private invertedIndexBST counter;

    public searchEnginee(){

        index = new index();
        invertedindex = new InvertedIndex();
        invertedindexBST = new invertedIndexBST();
        invertedindexAVL = new invertedIndexAVL();

        counter = new invertedIndexBST();
    }

    public void LoadData () {
        try{
            File stopfile = new File ("stop.txt");
            Scanner read = new Scanner (stopfile).useDelimiter("\\Z");
            //reading the first line
            String stops = read.next();

            stops = stops.replaceAll("\n", " ");

            File docsfile = new File("dataset.csv");
            Scanner read2 = new Scanner (docsfile);
            String line = read2.nextLine();

            for ( int lineID = 0 ; lineID <50 ; lineID ++ )
            {
                line = read2.nextLine().toLowerCase();

                int pos = line.indexOf(',');
                int docID = Integer.parseInt( line .substring(0, pos));

                String data = line.substring(pos+1, line.length() - pos).trim();
                data = data.substring(0, data.length() -1);
                data = data.replaceAll("-"," ");
                data =  data.replaceAll("[\']", " ");
                data = data.replaceAll("[^a-zA-Z0-9]", " ").trim() ;


                String [] words = data.split(" ");

                for (int i = 0; i < words.length ; i++)
                {
                    String word = words[i].trim();

                    if ( word.compareToIgnoreCase("") != 0)
                        tokens ++;

                    this.counter.Add(docID, word);

                    if ( ! stops.contains(word + " "))
                    {
                        this.index.Add(docID, word);
                        this.invertedindex.Add(docID, word);
                        this.invertedindexBST.Add(docID, word);
                        this.invertedindexAVL.Add(docID, word);
                    }
                }
            }
            vocabularies = counter.size();
            System.out.println("tokens " + tokens);
            System.out.println("vocab " + vocabularies);

            read.close();
            read2.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

        public  void TermRetrievalMenu(){
        
            System.out.println("---------------- Term Retrieval ----------------");
            System.out.println("1: index");
            System.out.println("2: inverted index");
            System.out.println("3: inverted index with BST");
            System.out.println("4: inverted index with AVL");
            System.out.println("Enter your choice: ");
            int c1 = Read.nextInt();
    
            System.out.println("Enter a Term: ");
            String str = Read.next().toLowerCase();
    
            System.out.print("Documents ID for your term search: ");
            Term_Retrieval(str.trim(), c1).print();
            System.out.println("\n");
    
        }

        private LinkedList<Integer> Term_Retrieval(String str , int choice) {

        LinkedList<Integer> docs = new LinkedList<Integer> ();
        switch (choice)
        {
            case 1 :
            {
                boolean [] docs1 = index.getDocs(str);
                for ( int i = 0 ; i < 50 ; i++)
                    if ( docs1[i] == true)
                        docs.insert(i);
            }
            break;
            case 2 :
                if (invertedindex.Find(str))
                {
                    boolean [] docs1 = invertedindex.invertedindex.retrieve().getDocs();
                    for ( int i = 0 ; i < 50 ; i++)
                        if ( docs1[i] == true)
                            docs.insert(i);
                }
                break;
            case 3:
                if (invertedindexBST.Find(str))
                    docs = invertedindexBST.invertedindexBST.retrieve().getDocs();
                break;
            case 4:
                if (invertedindexAVL.Find(str))
                    docs = invertedindexAVL.invertedindexAVL.retrieve().getDocs();
                break;
            default :
                System.out.println(" ");

        }
        return docs;
    }

    public  void BooleanRetrievalMenu() {
      
        int c2 ;
        System.out.println("---------------- Boolean Retrieval ----------------");

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

        System.out.print("Result documents IDs: ");
        Boolean_Retrieval(BooleanQueries, c2).print();
        System.out.println("\n");
    }

    private LinkedList<Integer> Boolean_Retrieval(String str , int choice)
    {
        LinkedList<Integer> docs = new LinkedList<Integer> ();
        switch (choice)
        {
            case 1 :
                docs = index.booleanRetrieval(str);
                break;
            case 2 :
                docs = invertedindex.booleanRetrieval(str);
                break;
            case 3:
                docs = invertedindexBST.booleanRetrieval(str);
                break;
            case 4:
                docs = invertedindexAVL.booleanRetrieval(str);
                break;
            default :
                System.out.println(" ");

        }
        return docs;
    }

    private void RankedRetrievalmMenu() {
        
        int c3;
        System.out.println("---------------- Ranked Retrieval ----------------");
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
        Ranked_Retrieval(RankedQueries, c3);
    }

    private void Ranked_Retrieval(String str , int choice)
    {
        switch (choice)
        {
            case 1 :
                index.countFreq(str);
                break;
            case 2 :
                invertedindex.countFreq(str);
                break;
            case 3:
                invertedindexBST.countFreq(str);
                break;
            case 4:
                invertedindexAVL.countFreq(str);
                break;
            default :
                System.out.println(" ");
        }
    }

    public void IndexedDocumentsMenu()
    {
        System.out.println("---------------- Indexed Documents ----------------");
        // Iterate through all the documents in the 'indices' array
        for (int i = 0; i < index.indices.length; i++) {
            // Get the number of words in the current document
            int wordCount = index.indices[i].index.size();

            // Print the document ID and the word count
            System.out.println("Document ID: " + index.indices[i].docID + " - Word Count: " + wordCount);
        }
    }

    public void IndexedTokensMenu()
    {
        System.out.println("---------------- Indexed Tokens ----------------");
        for (int i = 0; i < index.indices.length; i++) {
            LinkedList<String> documentWords = index.indices[i].index;  // Get current document's words

            LinkedList<String> countedWords = new LinkedList<String>();
            documentWords.findFirst();
            while (!documentWords.last()) {
                String word = documentWords.retrieve();
                // If the word has not been counted for this document yet
                if (!countedWords.Find(word)) {
                    int docCount = 0;
                    for (int j = 0; j < index.indices.length; j++) {
                        if (index.indices[j].Find(word)) {
                            docCount++;
                        }
                    }

                    System.out.println("Word:\"" + word + "\" Appears in: " + docCount + " documents");
                    countedWords.insert(word);  //insert if the word is counted
                }

                documentWords.findNext();
            }
        }
    }
    
    public static void main(String[] args) {
        searchEnginee Searchify=new searchEnginee();

        Searchify.LoadData();
        int Menu;

        do {
        
            System.out.println("-----Searchify-----");
            System.out.println("1:Term Retrieval");
            System.out.println("2: Boolean Retrieval. ");
            System.out.println("3: Ranked Retrieval.");
            System.out.println("4: Indexed Documents.");
            System.out.println("5: Indexed Tokens.");
            System.out.println("6: Exit.");
    
            System.out.println("Welcome to Searchify, Enter your choice or 6 to Exit: ");
            Menu = Read.nextInt();
            
            switch (Menu)
            {
                //Term Retrieval: to enter a term and return all document  choice containing that term
                case 1:
                    Searchify.TermRetrievalMenu();
                    break;
                //Boolean Retrieval: to enter a Boolean query that will return a set of unranked documents
                case 2:
                Searchify.BooleanRetrievalMenu();
                    break;

                //Ranked Retrieval: to enter a query that will return a ranked list of documents with their scores
                case 3:
                Searchify.RankedRetrievalmMenu();
                    break;

                //Indexed Documents: to show number of documents in the index
                case 4:
                Searchify.IndexedDocumentsMenu();
                    break;

                //Indexed Tokens: to show number of vocabulary and tokens in the index
                case 5:
                Searchify.IndexedTokensMenu();
                    break;

                case 6:
                    break;

                default:
                    System.out.println("try again, choose from 1-5 or 6 to Exit: ");
            }
        } while (Menu != 6);
    }

}  
 



