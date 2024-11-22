public class invertedIndexBST {
    BST <String, wordBST> invertedindexBST;
    Frequency [] fr;
    totalFrequency TF;


    public invertedIndexBST() {
        invertedindexBST = new BST <String, wordBST>();
        fr = new Frequency[50];
        TF= new totalFrequency();
    }


    public int size()
    {
        return invertedindexBST.size();
    }


    public boolean Add(int docID, String word)
    {
        if (invertedindexBST.empty())
        {
            wordBST a = new wordBST ();
            a.setWord(word);
            a.add_docID(docID);
            invertedindexBST.insert(word, a);
            return true;
        }
        else
        {
            if (invertedindexBST.find(word))
            {
                wordBST a = invertedindexBST.retrieve();
                a.add_docID(docID);
                invertedindexBST.update(a);
                return false;

            }

            wordBST a = new wordBST ();
            a.setWord(word);
            a.add_docID(docID);
            invertedindexBST.insert(word, a);
            return true;
        }
    }


    public boolean Find(String word)
    {

        return invertedindexBST.find(word);
    }



    public wordBST getRetrive(){

        return invertedindexBST.retrieve();
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
            result = invertedindexBST.retrieve().docIDs_rank.getKeys();
        return result;


    }
    private LinkedList<Integer> And_Or (String str) {
        String [] AND_ORs = str.split(" OR ");
        LinkedList<Integer> result = ANDFunc (AND_ORs[0]);

        for ( int i = 1 ; i < AND_ORs.length ; i++  )
        {
            LinkedList<Integer> r2 =ANDFunc (AND_ORs[i]);

            r2.findFirst();
            for ( int j = 0 ; j < r2.size() ; j++)
            {
                boolean find = false;
                result.findFirst();
                while ( ! result.last())
                {
                    if (result.retrieve()== r2.retrieve())
                    {
                        find = true;
                        break;
                    }
                    result.findNext();
                }
                if (result.retrieve() == r2.retrieve())
                    find = true;

                if (!find )
                    result.insert(r2.retrieve());

                r2.findNext();
            }
        }
        return result;
    }

    private LinkedList<Integer> ANDFunc (String str)
    {
        String [] ANDs = str.split(" AND ");

        LinkedList<Integer> r1 = new LinkedList<Integer>();
        if (this.Find (ANDs[0].toLowerCase().trim()))
            r1 = invertedindexBST.retrieve().docIDs_rank.getKeys();


        for ( int i = 0 ; i< ANDs.length ; i++)
        {

            LinkedList<Integer> b1 = r1;
            r1 = new LinkedList<Integer> ();

            if (this.Find (ANDs[i].toLowerCase().trim()))
            {
                LinkedList<Integer> docs = invertedindexBST.retrieve().docIDs_rank.getKeys();

                docs.findFirst();
                for ( int j = 0 ; j < docs.size ; j++)
                {
                    b1.findFirst();
                    boolean found =  false;
                    while ( ! b1.last())
                    {
                        if ( b1.retrieve()==docs.retrieve())
                        {
                            found = true;
                            break;
                        }
                        b1.findNext();
                    }
                    if ( b1.retrieve()== docs.retrieve())
                        found = true;

                    if (found)
                        r1.insert(docs.retrieve());

                    docs.findNext();
                }
            }
        }
        return r1;
    }
    private LinkedList<Integer> ORFunc (String str)
    {
        String [] ORs = str.split(" OR ");

        LinkedList<Integer> r1 =  new LinkedList<Integer> ();
        if (this.Find (ORs[0].toLowerCase().trim()))
            r1 = invertedindexBST.retrieve().docIDs_rank.getKeys();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            if (this.Find (ORs[i].toLowerCase().trim()))
            {
                LinkedList<Integer> docs = invertedindexBST.retrieve().docIDs_rank.getKeys();
                docs.findFirst();
                for ( int j = 0 ; j < docs.size ; j++)
                {
                    r1.findFirst();
                    boolean find =  false;
                    while (! r1.last())
                    {
                        if ( r1.retrieve()== docs.retrieve())
                        {
                            find = true;
                            break;
                        }
                        r1.findNext();
                    }
                    if ( r1.retrieve() == docs.retrieve())
                        find = true;

                    if (! find)
                        r1.insert(docs.retrieve());

                    docs.findNext();
                }
            }
        }
        return r1;
    }
    public void countFreq(String str)
    {
        TF.freqForInvertedIndexBST(str,this);
    }


}

