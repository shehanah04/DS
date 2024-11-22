public class invertedIndexAVL {


    AVL <String, wordAVL> invertedindexAVL;
    Frequency [] fr;
    totalFrequency TF;


    public invertedIndexAVL() {
        invertedindexAVL = new AVL <String, wordAVL>();
        fr = new Frequency[50];
        TF= new totalFrequency();
    }


    public int size()
    {
        return invertedindexAVL.size();
    }


    public boolean Add(int docID, String word)
    {
        if (invertedindexAVL.empty())
        {
            wordAVL a = new wordAVL ();
            a.setWord(word);
            a.add_docID(docID);
            invertedindexAVL.insert(word, a);
            return true;
        }
        else
        {
            if (invertedindexAVL.find(word))
            {
                wordAVL a = invertedindexAVL.retrieve();
                a.add_docID(docID);
                invertedindexAVL.update(a);
                return false;

            }

            wordAVL a = new wordAVL ();
            a.setWord(word);
            a.add_docID(docID);
            invertedindexAVL.insert(word, a);
            return true;
        }
    }


    public boolean Find(String word)
    {

        return invertedindexAVL.find(word);
    }
    public wordAVL getRetrive(){
        return invertedindexAVL.retrieve();
    }
    public LinkedList<Integer> booleanRetrieval(String str )
    {
        if (! str.contains(" OR ") && ! str.contains(" AND "))
        {
            str = str.toLowerCase().trim();
            return oneWord(str);

        }

        else if (str.contains(" OR ") && str.contains(" AND "))
        {
            return And_Or(str);
        }

        else  if (str.contains(" AND "))
            return ANDFunc (str);

        return ORFunc (str);
    }
    private LinkedList<Integer> oneWord (String str) {

        LinkedList<Integer> result = new LinkedList<Integer>();
        if (this.Find (str))
            result = invertedindexAVL.retrieve().docIDS_rank.getKeys();
        return result;


    }
    private LinkedList<Integer> And_Or (String str) {
        String [] AND_ORs = str.split(" OR ");
        LinkedList<Integer> r1 = ANDFunc (AND_ORs[0]);

        for ( int i = 1 ; i < AND_ORs.length ; i++  )
        {
            LinkedList<Integer> r2 =ANDFunc (AND_ORs[i]);

            r2.findFirst();
            for ( int j = 0 ; j < r2.size() ; j++)
            {
                boolean find = false;
                r1.findFirst();
                while (! r1.last())
                {
                    if (r1.retrieve().compareTo(r2.retrieve()) == 0 )
                    {
                        find = true;
                        break;
                    }
                    r1.findNext();
                }
                if (r1.retrieve().compareTo(r2.retrieve()) == 0 )
                {
                    find = true;
                    break;
                }

                if (!find )
                    r1.insert(r2.retrieve());

                r2.findNext();
            }
        }
        return r1;

    }

    private LinkedList<Integer> ANDFunc (String str)
    {
        String [] ANDs = str.split(" AND ");

        LinkedList<Integer> r1 = new LinkedList<Integer>();
        if (this.Find (ANDs[0].toLowerCase().trim()))
            r1 = invertedindexAVL.retrieve().docIDS_rank.getKeys();

        LinkedList<Integer> result = new LinkedList<Integer>();
        for ( int i = 0 ; i< ANDs.length ; i++)
        {

            if (this.Find (ANDs[i].toLowerCase().trim()))
            {
                LinkedList<Integer> docs = invertedindexAVL.retrieve().docIDS_rank.getKeys();

                docs.findFirst();
                for ( int j = 0 ; j < docs.size ; j++)
                {
                    r1.findFirst();
                    boolean find =  false;
                    while ( ! r1.last())
                    {
                        if ( r1.retrieve()==docs.retrieve())
                        {
                            find = true;
                            break;
                        }
                        r1.findNext();
                    }
                    if ( r1.retrieve()== docs.retrieve())
                        find = true;

                    if (find)
                        result.insert(docs.retrieve());

                    docs.findNext();
                }
            }
        }
        return result;
    }
    private LinkedList<Integer> ORFunc (String str)
    {
        String [] ORs = str.split(" OR ");

        LinkedList<Integer> result =  new LinkedList<Integer> ();
        if (this.Find (ORs[0].toLowerCase().trim()))
            result = invertedindexAVL.retrieve().docIDS_rank.getKeys();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            if (this.Find (ORs[i].toLowerCase().trim()))
            {
                LinkedList<Integer> docs = invertedindexAVL.retrieve().docIDS_rank.getKeys();
                docs.findFirst();
                for ( int j = 0 ; j < docs.size ; j++)
                {
                    result.findFirst();
                    boolean find =  false;
                    while (! result.last())
                    {
                        if ( result.retrieve()== docs.retrieve())
                        {
                            find = true;
                            break;
                        }
                        result.findNext();
                    }
                    if ( result.retrieve() == docs.retrieve())
                        find = true;

                    if (! find)
                        result.insert(j);

                    docs.findNext();
                }
            }
        }
        return result;
    }


    public void countFreq(String str)
    {
        TF.freqForInvertedIndexAVL(str,this);
    }
}
