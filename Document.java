public class Document {
    int docID;
    LinkedList <String> index;

    public Document() {
        docID = 0;
        index = new LinkedList <String>();
    }

    public void addNew (String word)
    {
        index.insert(word);
    }

    public boolean found(String word)
    {
        if (index.empty())
            return false;

        index.findFirst();
        for ( int i = 0 ; i < index.size ; i++)
        {
            if ( index.retrieve().compareTo(word) == 0)
                return true;
            index.findNext();
        }
        return false;
    }
}


