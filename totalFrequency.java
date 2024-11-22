public class totalFrequency {
    sorting s;
    Frequency [] fr;

    // counts frequency for inverted index
    public void freqForInvertedIndex(String str,InvertedIndex list)
    {
        str = str.toLowerCase().trim();
        String [] words = str.split(" ");
        fr = new Frequency[50];
        for ( int i = 0 ; i < 50 ; i++ )
        {
            fr[i] = new Frequency();
            fr[i].docID = i;
            fr[i].f = 0;
            fr[i].msg = "Document " + i + " : ";
        }

        for ( int i = 0 ; i < words.length ; i++)
        {
            if (list.Find (words[i]))
            {
                boolean [] docs = list.getRetrive().getDocs();
                int [] rank = list.getRetrive().getRank();

                for ( int j = 0 ; j < docs.length ; j ++)
                {
                    if (docs[j] == true)
                    {
                        int index = j;
                        fr[index].docID = index;
                        fr[index].f += rank[j];
                        fr[index].msg +=" ( " + words[i] + ", " + rank[j] + " ) +";
                    }
                }
            }
        }

        for ( int x = 0 ; x < fr.length ; x ++)
        {
            fr[x].msg = fr[x].msg.substring(0, fr[x].msg.length()-1);
            fr[x].msg += " = " + fr[x].f;
        }

        s.mergesort(fr, 0, fr.length-1 );

        System.out.println("DocIDt\tScore");
        for ( int x = 0 ;  fr[x].f != 0 ; x++)
           System.out.println(fr[x].docID + "\t\t" + fr[x].f);
    }
    // counts frequency for inverted index with AVL
    public void freqForInvertedIndexAVL(String str,invertedIndexAVL list)
    {
        str = str.toLowerCase().trim();
        String [] words = str.split(" ");
        fr = new Frequency[50];
        for ( int i = 0 ; i < 50 ; i++ )
        {
            fr[i] = new Frequency();
            fr[i].docID = i;
            fr[i].f = 0;
            fr[i].msg = "Document " + i + " : ";
        }
        for ( int i = 0 ; i < words.length ; i++)
        {
            if (list.Find(words[i]))
            {
                LinkedList<Integer> docs = list.getRetrive().getDocs();
                LinkedList<Integer> rank = list.getRetrive().getRank();

                docs.findFirst();
                rank.findFirst();
                for ( int j = 0 ; j < docs.size() ; j ++)
                {
                    int index = docs.retrieve();
                    fr[index].docID = index;
                    fr[index].f += rank.retrieve();
                    fr[index].msg +=" ( " + words[i] + ", " + rank.retrieve() + " ) +";
                    docs.findNext();
                    rank.findNext();
                }
            }
        }

        for ( int x = 0 ; x < fr.length ; x ++)
        {
            fr[x].msg = fr[x].msg.substring(0, fr[x].msg.length()-1);
            fr[x].msg += " = " + fr[x].f;
        }

        s.mergesort(fr, 0, fr.length-1 );


        System.out.println("DocIDt\tScore");
        for ( int x = 0 ;  fr[x].f != 0 ; x++)
           System.out.println(fr[x].docID + "\t\t" + fr[x].f);
    }
    // counts frequency for inverted index with BST
    public void freqForInvertedIndexBST(String str,invertedIndexBST list)
    {
        str = str.toLowerCase().trim();
        String [] words = str.split(" ");
        fr = new Frequency[50];
        for ( int i = 0 ; i < 50 ; i++ )
        {
            fr[i] = new Frequency();
            fr[i].docID = i;
            fr[i].f = 0;
            fr[i].msg = "Document " + i + " : ";
        }

        for ( int i = 0 ; i < words.length ; i++)
        {
            if (list.Find(words[i]))
            {
                LinkedList<Integer> docs = list.getRetrive().getDocs();
                LinkedList<Integer> rank = list.getRetrive().getRank();

                docs.findFirst();
                rank.findFirst();
                for ( int j = 0 ; j < docs.size() ; j ++)
                {
                    int index = docs.retrieve();
                    fr[index].docID = index;
                    fr[index].f += rank.retrieve();
                    fr[index].msg +=" ( " + words[i] + ", " + rank.retrieve() + " ) +";
                    docs.findNext();
                    rank.findNext();
                }
            }
        }

        for ( int x = 0 ; x < fr.length ; x ++)
        {
            fr[x].msg = fr[x].msg.substring(0, fr[x].msg.length()-1);
            fr[x].msg += " = " + fr[x].f;
        }

        s.mergesort(fr, 0, fr.length-1 );

        System.out.println("DocIDt\tScore");
        for ( int x = 0 ;  fr[x].f != 0 ; x++)
            System.out.println(fr[x].docID + "\t\t" + fr[x].f);
    }
    // counts frequency for index
    public void freqForIndex(String str,index list)
    {
        str = str.toLowerCase().trim();
        String [] words = str.split(" ");
        fr = new Frequency[50];
        for ( int i = 0 ; i < 50 ; i++ )
        {
            fr[i] = new Frequency();
            fr[i].docID = i;
            fr[i].f = 0;
            fr[i].msg = "Document " + i + " : ";
        }

        for ( int docs = 0 ; docs <50 ; docs++)
        {
            for ( int i = 0 ; i < words.length ; i++)
            {
                list.indices[docs].index.findFirst();
                int wordcount = 0;
                for ( int x = 0 ; x < list.indices[docs].index.size() ; x++ )
                {
                    if (list.indices[docs].index.retrieve().compareTo(words[i])==0)
                        wordcount ++;
                    list.indices[docs].index.findNext();
                }
                fr[docs].f += wordcount;
                fr[docs].msg +=" ( " + words[i] + ", " + wordcount + " ) +";
            }
        }

        for ( int x = 0 ; x < fr.length ; x ++)
        {
            fr[x].msg = fr[x].msg.substring(0, fr[x].msg.length()-1);
            fr[x].msg += " = " + fr[x].f;
        }

        s.mergesort(fr, 0, fr.length-1 );

        System.out.println("DocIDt\tScore");
        for ( int x = 0 ;  fr[x].f != 0 ; x++)
            System.out.println(fr[x].docID + "\t\t" + fr[x].f);
    }
}
