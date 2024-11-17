public class InvertedIndex {
    Frequency [] fr;
    LinkedList <word> invertedindex;
    sorting s;
    totalFrequency TF;
    public InvertedIndex() {
        invertedindex = new LinkedList <word>();
        fr = new Frequency [50];
        TF= new totalFrequency();
    }

    public int size()
    {
        return invertedindex.size();
    }

    public boolean addNew (int docID, String word)
    {
        if (invertedindex.empty())
        {
            word t = new word ();
            t.setWord(word);
            t.add_docID(docID);
            invertedindex.insert(t);
            return true;
        }
        else
        {
            invertedindex.findFirst();
            while ( ! invertedindex.last())
            {
                //if the word already added
                if ( invertedindex.retrieve().word.compareTo(word) == 0)
                {
                    word t = invertedindex.retrieve();
                    t.add_docID(docID);
                    invertedindex.update(t);
                    return false;
                }
                invertedindex.findNext();
            }
            //repeat once outside the loop
            if ( invertedindex.retrieve().word.compareTo(word) == 0)
            {
                word t = invertedindex.retrieve();
                t.add_docID(docID);
                invertedindex.update(t);
                return false;
            }
            word t = new word ();
            t.setWord(word);
            t.add_docID(docID);
            invertedindex.insert(t);
        }
        return true;
    }
    public boolean found(String word)
    {
        if (invertedindex.empty())
            return false;

        invertedindex.findFirst();
        for ( int i = 0 ; i < invertedindex.size ; i++)
        {
            if ( invertedindex.retrieve().word.compareTo(word) == 0)
                return true;
            invertedindex.findNext();
        }
        return false;
    }
    public void printDocment()
    {
        if (this.invertedindex.empty())
            System.out.println("Empty Inverted Index");
        else
        {
            this.invertedindex.findFirst();
            while ( ! this.invertedindex.last())
            {
                System.out.println(invertedindex.retrieve());
                this.invertedindex.findNext();
            }
            System.out.println(invertedindex.retrieve());
        }
    }
    public word getRetrive(){
        return invertedindex.retrieve();
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
        {
            boolean [] docs = invertedindex.retrieve().getDocs();
            for ( int i = 0 ; i < docs.length ; i++)
                if (docs[i])
                    result.insert(i);
        }
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
        {
            boolean [] docs = invertedindex.retrieve().getDocs();
            for ( int i = 0 ; i < docs.length ; i++)
                if (docs[i])
                    r1.insert(i);
        }
        LinkedList<Integer> result = new LinkedList<Integer> ();
        for ( int i = 1 ; i< ANDs.length ; i++)
        {
            if (this.found (ANDs[i].toLowerCase().trim()))
            {
                boolean [] docs = invertedindex.retrieve().getDocs();
                for ( int j = 0 ; j < docs.length ; j++)
                {
                    if (docs[j] )  {
                        r1.findFirst();
                        boolean found =  false;
                        while ( ! r1.last())
                        {
                            if ( r1.retrieve()==j)
                            {
                                found = true;
                                break;
                            }
                            r1.findNext();
                        }
                        if ( r1.retrieve()== j)
                            found = true;

                        if (found)
                            result.insert(j);
                    }
                }
            }
        }
        return result;
    }

    public LinkedList<Integer> OR_Function (String str)
    {
        String [] ORs = str.split(" OR ");

        LinkedList<Integer> result = new LinkedList<Integer> ();
        if (this.found (ORs[0].toLowerCase().trim()))
        {
            boolean [] docs = invertedindex.retrieve().getDocs();
            for ( int i = 0 ; i < docs.length ; i++)
                if (docs[i])
                    result.insert(i);
        }
        for ( int i = 1 ; i< ORs.length ; i++)
        {
            if (this.found (ORs[i].toLowerCase().trim()))
            {
                boolean [] docs = invertedindex.retrieve().getDocs();
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
        }
        return result;
    }

    public void countFreq(String str){
        TF.freqForInvertedIndex(str, this);
    }

}
