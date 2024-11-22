public class word {
    String word;
    boolean [] docIDs;
    int [] rank;

    public word() {
        word = "";
        docIDs = new boolean [50];
        rank = new int [50];
        for (int i = 0 ; i < docIDs.length ; i++)
        {
            docIDs [i] = false;
            rank[i] = 0;
        }
    }
    public word(String word, int [] rank)
    {
        this.word = word;
        docIDs = new boolean [50];
        rank = new int [50];
        for (int i = 0 ; i < rank.length ; i++)
            if (rank[i] != 0)
            {
                docIDs [i] = true;
                rank[i] = rank[i];
            }
    }
    public void add_docID ( int docID)
    {
        docIDs[docID] = true;
        rank[docID] ++;
    }

    public void setWord(String word)
    {
        this. word = word;
    }

    public String getWord()
    {
        return word;
    }

    public boolean [] getDocs ()
    {
        boolean [] test = new boolean [rank.length];

        for ( int i = 0 ; i < test.length ; i++)
            test[i] = docIDs[i];

        return test;
    }

    public int [] getRank()
    {
        int[] test = new int [rank.length];
        for ( int i = 0 ; i < test.length ; i++)
            test[i] = rank[i];
        return test;
    }
    public String toString(){
        String doc="";
        boolean first=true;
        for (int i=0;i<docIDs.length;i++){
            if (docIDs[i]){
                if(first){
                    doc+=String.valueOf(i);
                    first=false;
                }
                else
                    doc+=","+String.valueOf(i);
            }
        }
        return word + "[" + doc + "]";
    }
}
