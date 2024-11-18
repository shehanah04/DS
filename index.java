public class index {


    Document [] indexes;
    Frequency [] fr;
    totalFrequency TF;


    public index() {
        fr = new Frequency [50];
        indexes = new Document [50];
        for ( int i = 0 ; i < indexes.length ; i++)
        {
            indexes [i] = new Document();
            indexes [i].docID = i;
            TF= new totalFrequency();
        }
    }

    public void addDocument ( int docID, String data)
    {
        indexes[docID].addNew(data);
    }

    public void printDocment (int docID)
    {
        if ( indexes[docID].index.empty())
            System.out.println("Empty Document");
        else
        {
            indexes[docID].index.findFirst();
            for ( int i = 0; i< indexes[docID].index.size ; i++)
            {
                System.out.print (indexes[docID].index.retrieve() + " ");
                indexes[docID].index.findNext();
            }
        }
    }
    //=================================================================
    public  boolean [] getDocs (String str)
    {
        boolean [] result = new boolean [50];
        for (int i = 0 ; i < result.length ; i++)
            result[i] = false;

        for (int i = 0 ; i < result.length ; i++)
            if (indexes[i].found(str))
                result[i] = true;

        return result;
    }

    //=================================================================
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
        boolean [] docs = getDocs(str);
        for ( int i = 0 ; i < docs.length ; i++)
            if (docs[i])
                result.insert(i);
        return result;
    }

    private LinkedList<Integer> And_Or (String str) {
        String [] AND_ORs = str.split(" OR ");
        LinkedList<Integer> result = AND_Function (AND_ORs[0]);

        for ( int i = 1 ; i < AND_ORs.length ; i++  )
        {
            LinkedList<Integer> r2 =AND_Function (AND_ORs[i]);

            r2.findFirst();
            for ( int j = 0 ; j < r2.size() ; j++)
            {
                boolean found = false;
                result.findFirst();
                while (! result.last())
                {
                    if (result.retrieve().compareTo(r2.retrieve()) == 0 )
                    {
                        found = true;
                        break;
                    }
                    result.findNext();
                }
                if (result.retrieve().compareTo(r2.retrieve()) == 0 )
                {
                    found = true;
                    break;
                }

                if (!found )
                    result.insert(r2.retrieve());

                r2.findNext();
            }
        }
        return result;

    }

    public LinkedList<Integer> AND_Function (String str)
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
                    boolean found =  false;
                    while ( ! b1.last())
                    {
                        if ( b1.retrieve()==j)
                        {
                            found = true;
                            break;
                        }
                        b1.findNext();
                    }
                    if ( b1.retrieve()== j)
                        found = true;
                    if (found)
                        result.insert(j);
                }
            }
        }
        return result;
    }

    public LinkedList<Integer> OR_Function (String str)
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
                    boolean found =  false;

                    while (! result.last() )
                    {
                        if ( result.retrieve() == j)
                        {
                            found = true;
                            break;
                        }
                        result.findNext();
                    }
                    if ( result.retrieve() == j)
                    {
                        found = true;
                        break;
                    }

                    if (! found)
                        result.insert(j);
                }
            }
        }
        return result;
    }

    public void countFreq(String str){
        TF.freqForIndex(str, this);
    }

}




