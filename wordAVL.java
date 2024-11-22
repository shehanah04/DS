public class wordAVL {
    String word;


    AVL <Integer, Integer> docIDS_rank;

    public wordAVL() {
        word = "";
        docIDS_rank = new AVL <Integer, Integer> ();
    }
    public wordAVL(String word)
    {
        this.word = word;
        docIDS_rank = new AVL <Integer, Integer> ();
    }

    public void add_docID ( int docID)
    {
        if (docIDS_rank.empty())
            docIDS_rank.insert(docID, 1);
        else
        {
            if (docIDS_rank.find(docID))
            {
                int ranked = docIDS_rank.retrieve();
                ranked++;
                docIDS_rank.update(ranked);
            }
            else
                docIDS_rank.insert(docID, 1);
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
        return docIDS_rank.getKeys();
    }

    public LinkedList<Integer> getRank()
    {
        return this.docIDS_rank.getData();
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
