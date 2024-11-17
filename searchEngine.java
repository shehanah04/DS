import java.io.File;
import java.util.Scanner;

public class searchEngine {
    int tokens = 0;
    int vocab = 0;

    index index;
    InvertedIndex invertedindex;
    invertedIndexBST invertedindexBST;
    invertedIndexAVL invertedindexAVL;

    invertedIndexBST test;

    public searchEngine ()
    {
        index = new index();
        invertedindex = new InvertedIndex();
        invertedindexBST = new invertedIndexBST();
        invertedindexAVL = new invertedIndexAVL();

        test = new invertedIndexBST();
    }

    public void LoadData ()
    {
        try{
            File stopfile = new File ("/Users/shehanah/Desktop/data/stop.txt");
            Scanner reader = new Scanner (stopfile).useDelimiter("\\Z");
            String stops = reader.next();

            stops = stops.replaceAll("\n", " ");

            File docsfile = new File("/Users/shehanah/Desktop/data/dataset.csv");
            Scanner reader1 = new Scanner (docsfile);
            String line = reader1.nextLine();

            for ( int lineID = 0 ; lineID <50 ; lineID ++ )
            {
                line = reader1.nextLine().toLowerCase();

                int pos = line.indexOf(',');
                int docID = Integer.parseInt( line .substring(0, pos));

                String data = line.substring(pos+1, line.length() - pos).trim();
                data = data.substring(0, data.length() -1);

                data = data.toLowerCase();
                data =  data.replaceAll("[\']", " ");
                data = data.replaceAll("[^a-zA-Z0-9]", " ").trim() ;

                String [] words = data.split(" "); // --1

                for (int i = 0; i < words.length ; i++)
                {
                    String word = words[i].trim(); //--2

                    if ( word.compareToIgnoreCase("") != 0)
                        tokens ++;

                    this.test.addNew(docID, word);

                    if ( ! stops.contains(word + " ")) //--3
                    {
                        this.index.addDocument(docID, word);
                        this.invertedindex.addNew(docID, word);
                        this.invertedindexBST.addNew(docID, word);
                        this.invertedindexAVL.addNew(docID, word);
                    }
                }

                // this.index.printDocment(docID);
                //System.out.println("");
            }
            //this.invertedindex.printDocment();
            //this.invertedindexBST.printDocument();
            //this.invertedindexAVL.printDocument();

            vocab = test.size();

            System.out.println("tokens " + tokens);
            System.out.println("vocabs " + vocab);

            reader.close();
            reader1.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public LinkedList<Integer> Boolean_Retrieval(String str , int DSType)
    {
        LinkedList<Integer> docs = new LinkedList<Integer> ();
        switch (DSType)
        {
            case 1 :
                docs = index.booleanRetrival(str);
                break;
            case 2 :
                docs = invertedindex.booleanRetrival(str);
                break;
            case 3:
                docs = invertedindexBST.booleanRetrival(str);
                break;
            case 4:
                docs = invertedindexAVL.booleanRetrival(str);
                break;
            default :
                System.out.println("Bad data structure");

        }
        return docs;
    }

    public void Ranked_Retrieval(String str , int DSType)
    {
        switch (DSType)
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
                System.out.println("Bad data structure");
        }
    }


}