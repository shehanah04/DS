public class totalFrequency {
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
                    }
                }
            }
        }

        mergesort(fr, 0, fr.length-1 );

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
                    docs.findNext();
                    rank.findNext();
                }
            }
        }
        mergesort(fr, 0, fr.length-1 );


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
                    docs.findNext();
                    rank.findNext();
                }
            }
        }

        mergesort(fr, 0, fr.length-1 );

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
            }
        }
        mergesort(fr, 0, fr.length-1 );

        System.out.println("DocIDt\tScore");
        for ( int x = 0 ;  fr[x].f != 0 ; x++)
            System.out.println(fr[x].docID + "\t\t" + fr[x].f);
    }



    public static void mergesort ( Frequency [] A , int l , int r )
    {
        if ( l >= r )
            return;
        int m = ( l + r ) / 2;
        mergesort (A , l , m ) ;          // Sort first half
        mergesort (A , m + 1 , r ) ;    // Sort second half
        merge (A , l , m , r ) ;            // Merge
    }

    private static void merge ( Frequency [] A , int l , int m , int r )
    {
        Frequency [] B = new Frequency [ r - l + 1];
        int i = l , j = m + 1 , k = 0;

        while ( i <= m && j <= r )
        {
            if ( A [ i ].f >= A [ j ].f)
                B [ k ++] = A [ i ++];
            else
                B [ k ++] = A [ j ++];
        }

        if ( i > m )
            while ( j <= r )
                B [ k ++] = A [ j ++];
        else
            while ( i <= m )
                B [ k ++] = A [ i ++];

        for ( k = 0; k < B . length ; k ++)
            A [ k + l ] = B [ k ];
    }

}
