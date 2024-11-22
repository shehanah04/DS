public class wordBST {
    String word;


    BST <Integer, Integer> docIDs_rank;

    public wordBST() {
        word = "";
        docIDs_rank = new BST <Integer, Integer> ();
    }
    public wordBST(String word)
    {
        this.word = word;
        docIDs_rank= new BST <Integer, Integer> ();
    }

    public void add_docID ( int docID)
    {
        if (docIDs_rank.empty())
            docIDs_rank.insert(docID, 1);
        else
        {
            if (docIDs_rank.find(docID))
            {
                int ranked = docIDs_rank.retrieve();
                ranked++;
                docIDs_rank.update(ranked);
            }
            else
                docIDs_rank.insert(docID, 1);
        }
    }

    public void setWord(String word)
    {
        this. word = word;
    }

    public String getWord()
    {
        return word;
    }

    public LinkedList<Integer> getDocs ()
    {
        return docIDs_rank.getKeys();
    }

    public LinkedList<Integer> getRank()
    {
        return this.docIDs_rank.getData();
    }

    @Override
    public String toString() {
        String docs = "";
        LinkedList<Integer> IDs = getDocs ();
        IDs.findFirst();
        for (int i = 0, j = 0 ; i < IDs.size(); i++)
        {
            if ( i == 0)
                docs += " " + String.valueOf(i) ;
            else
                docs += ", " + String.valueOf(i) ;
            IDs.findNext();
        }
        return word + "[" + docs + ']';
    }

}
