public class wordAVL {
    String word;

    // with each word AVL Tree for the document Numbers with ranke
    // for each word in the documet it self
    AVL <Integer, Integer> docIDS_ranked;

    public wordAVL() {
        word = "";
        docIDS_ranked = new AVL <Integer, Integer> ();
    }

    public wordAVL(String word)
    {
        this.word = word;
        docIDS_ranked = new AVL <Integer, Integer> ();
    }

    public void add_docID ( int docID)
    {
        if (docIDS_ranked.empty())
            docIDS_ranked.insert(docID, 1);
        else
        {
            if (docIDS_ranked.find(docID))
            {
                int ranked = docIDS_ranked.retrieve();
                ranked++;
                docIDS_ranked.update(ranked);
            }
            else
                docIDS_ranked.insert(docID, 1);
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
        return docIDS_ranked.getKeys();
    }

    public LinkedList<Integer> getRanked()
    {
        return this.docIDS_ranked.getData();
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
