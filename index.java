public class index {


    Document [] indices;
    totalFrequency TF;


    public index() {
        indices = new Document [50];
        for ( int i = 0 ; i < indices.length ; i++)
        {
            indices [i] = new Document();
            indices [i].docID = i;
            TF= new totalFrequency();
        }
    }

    public void Add( int docID, String data)
    {
        indices[docID].addWord(data);
    }


    public  boolean [] getDocs(String str)
    {
        boolean [] Doc = new boolean [50];
        for (int i = 0 ; i < Doc.length ; i++)
            Doc[i] = false;

        for (int i = 0 ; i < Doc.length ; i++)
            if (indices[i].Find(str))
                Doc[i] = true;

        return Doc;
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
        boolean [] docs = getDocs(str);
        for ( int i = 0 ; i < docs.length ; i++)
            if (docs[i])
                result.insert(i);
        return result;
    }

    private LinkedList<Integer> And_Or (String str) {
        String [] AndOrs = str.split(" OR ");
        LinkedList<Integer> result = ANDFunc (AndOrs[0]);

        for ( int i = 1 ; i < AndOrs.length ; i++  )
        {
            LinkedList<Integer> r2 =ANDFunc (AndOrs[i]);

            r2.findFirst();
            for ( int j = 0 ; j < r2.size() ; j++)
            {
                boolean find = false;
                result.findFirst();
                while (! result.last())
                {
                    if (result.retrieve().compareTo(r2.retrieve()) == 0 )
                    {
                        find = true;
                        break;
                    }
                    result.findNext();
                }
                if (result.retrieve().compareTo(r2.retrieve()) == 0 )
                {
                    find = true;
                    break;
                }

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

        LinkedList<Integer> result = new LinkedList<Integer>();
        boolean [] docs = getDocs(ANDs[0].toLowerCase().trim());
        for ( int i = 0 ; i < docs.length ; i++)
            if (docs[i])
                result.insert(i);

        for ( int i = 1 ; i< ANDs.length ; i++)
        {

            LinkedList<Integer> b1 = result;
            result = new LinkedList<Integer> ();

            docs = getDocs(ANDs[i].toLowerCase().trim());
            for ( int j = 0 ; j < docs.length ; j++)
            {
                if (docs[j] )  {
                    b1.findFirst();
                    boolean find =  false;
                    while ( ! b1.last())
                    {
                        if ( b1.retrieve()==j)
                        {
                            find = true;
                            break;
                        }
                        b1.findNext();
                    }
                    if ( b1.retrieve()== j)
                        find = true;
                    if (find)
                        result.insert(j);
                }
            }
        }
        return result;
    }

    private LinkedList<Integer> ORFunc (String str)
    {
        String [] ORs = str.split(" OR ");

        LinkedList<Integer> result = new LinkedList<Integer> ();
        boolean [] docs = getDocs(ORs[0].toLowerCase().trim());
        for ( int i = 0 ; i < docs.length ; i++)
            if (docs[i])
                result.insert(i);

        for ( int i = 1 ; i< ORs.length ; i++)
        {
            docs = getDocs(ORs[i].toLowerCase().trim());
            for ( int j = 0 ; j < docs.length ; j++)
            {
                if (docs[j] )  {

                    result.findFirst();
                    boolean find =  false;

                    while (! result.last() )
                    {
                        if ( result.retrieve() == j)
                        {
                            find = true;
                            break;
                        }
                        result.findNext();
                    }
                    if ( result.retrieve() == j)
                    {
                        find = true;
                        break;
                    }

                    if (! find)
                        result.insert(j);
                }
            }
        }
        return result;
    }

    public void countFreq(String str)
    {
        TF.freqForIndex(str, this);
    }

}




