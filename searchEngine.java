import java.io.File;
import java.util.Scanner;

public class searchEngine {

    int tokens =0;
    int vocabularies =0;

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
            Scanner read = new Scanner (stopfile).useDelimiter("\\Z");
            String stops = read.next();

            stops = stops.replaceAll("\n", " ");

            File docsfile = new File("/Users/shehanah/Desktop/data/dataset.csv");
            Scanner read2 = new Scanner (docsfile);
            String line = read2.nextLine();

            for ( int lineID = 0 ; lineID <50 ; lineID ++ )
            {
                line = read2.nextLine().toLowerCase();

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

                    this.test.Add(docID, word);

                    if ( ! stops.contains(word + " ")) //--3
                    {
                        this.index.Add(docID, word);
                        this.invertedindex.Add(docID, word);
                        this.invertedindexBST.Add(docID, word);
                        this.invertedindexAVL.Add(docID, word);
                    }
                }

                // this.index.printDocment(docID);
                //System.out.println("");
            }
            //this.invertedindex.printDocment();
            //this.invertedindexBST.printDocument();
            //this.invertedindexAVL.printDocument();

            vocabularies = test.size();

            System.out.println("tokens " + tokens);
            System.out.println("vocab " + vocabularies);

            read.close();
            read2.close();
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