public class invertedIndexBST {
    BST <String, wordBST> invertedindexBST;
    Frequency [] freqs;
    totalFrequency TF;

    //==========================================================================
    public invertedIndexBST() {
        invertedindexBST = new BST <String, wordBST>();
        freqs = new Frequency[50];
        TF= new totalFrequency();
    }

    //==========================================================================
    public int size()
    {
        return invertedindexBST.size();
    }

    //==========================================================================
    public boolean addNew (int docID, String word)
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

    //=====================================================================
    public boolean found(String word)
    {
        return invertedindexBST.find(word);
    }

    //=====================================================================
    public void printDocument()
    {
        invertedindexBST.Traverse();
    }
    //=====================================================================
    public wordBST getRetrive(){
        return invertedindexBST.retrieve();
    }
    public LinkedList<Integer> booleanRetrival(String str )
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
            return AND_Function (str);

        return OR_Function (str);
    }
    private LinkedList<Integer> oneWord (String str) {

        LinkedList<Integer> result = new LinkedList<Integer>();
        if (this.found (str))
            result = invertedindexBST.retrieve().docIDS_ranked.getKeys();
        return result;


    }
    private LinkedList<Integer> And_Or (String str) {
        String [] AND_ORs = str.split(" OR ");
        LinkedList<Integer> r1 = AND_Function (AND_ORs[0]);

        for ( int i = 1 ; i < AND_ORs.length ; i++  )
        {
            LinkedList<Integer> r2 =AND_Function (AND_ORs[i]);

            r2.findFirst();
            for ( int j = 0 ; j < r2.size() ; j++)
            {
                boolean found = false;
                r1.findFirst();
                while (! r1.last())
                {
                    if (r1.retrieve().compareTo(r2.retrieve()) == 0 )
                    {
                        found = true;
                        break;
                    }
                    r1.findNext();
                }
                if (r1.retrieve().compareTo(r2.retrieve()) == 0 )
                {
                    found = true;
                    break;
                }

                if (!found )
                    r1.insert(r2.retrieve());

                r2.findNext();
            }
        }
        return r1;

    }

    public LinkedList<Integer> AND_Function (String str)
    {
        String [] ANDs = str.split(" AND ");

        LinkedList<Integer> r1 = new LinkedList<Integer>();
        if (this.found (ANDs[0].toLowerCase().trim()))
            r1 = invertedindexBST.retrieve().docIDS_ranked.getKeys();

        LinkedList<Integer> result = new LinkedList<Integer>();
        for ( int i = 0 ; i< ANDs.length ; i++)
        {

            if (this.found (ANDs[i].toLowerCase().trim()))
            {
                LinkedList<Integer> docs = invertedindexBST.retrieve().docIDS_ranked.getKeys();

                docs.findFirst();
                for ( int j = 0 ; j < docs.size ; j++)
                {
                    r1.findFirst();
                    boolean found =  false;
                    while ( ! r1.last())
                    {
                        if ( r1.retrieve()==docs.retrieve())
                        {
                            found = true;
                            break;
                        }
                        r1.findNext();
                    }
                    if ( r1.retrieve()== docs.retrieve())
                        found = true;

                    if (found)
                        result.insert(docs.retrieve());

                    docs.findNext();
                }
            }
        }
        return result;
    }
    public LinkedList<Integer> OR_Function (String str)
    {
        String [] ORs = str.split(" OR ");

        LinkedList<Integer> result =  new LinkedList<Integer> ();
        if (this.found (ORs[0].toLowerCase().trim()))
            result = invertedindexBST.retrieve().docIDS_ranked.getKeys();

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            if (this.found (ORs[i].toLowerCase().trim()))
            {
                LinkedList<Integer> docs = invertedindexBST.retrieve().docIDS_ranked.getKeys();
                docs.findFirst();
                for ( int j = 0 ; j < docs.size ; j++)
                {
                    result.findFirst();
                    boolean found =  false;
                    while (! result.last())
                    {
                        if ( result.retrieve()== docs.retrieve())
                        {
                            found = true;
                            break;
                        }
                        result.findNext();
                    }
                    if ( result.retrieve() == docs.retrieve())
                        found = true;

                    if (! found)
                        result.insert(j);

                    docs.findNext();
                }
            }
        }
        return result;
    }

    public void countFreq(String str){
        TF.freqForInvertedIndexBST(str,this);
    }


}

