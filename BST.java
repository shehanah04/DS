public class BST<K extends Comparable<K>, T>{

    class BSTNode<K extends Comparable<K>, T> {
        public K key;
        public T data;
        public BSTNode<K,T> left, right;

        /** Creates a new instance of BSTNode */
        public BSTNode(K k, T val) {
            key = k;
            data = val;
            left = right = null;
        }

        public BSTNode(K k, T val, BSTNode<K,T> l, BSTNode<K,T> r) {
            key = k;
            data = val;
            left = l;
            right = r;
        }
    }

    BSTNode<K, T> root;
    BSTNode<K, T > curr;
    int count ;

    public BST()
    {
        root = curr = null;
        count = 0;
    }


    public int size()
    {
        return count;
    }


    public boolean empty()
    {
        return root == null;
    }


    public void clear()
    {
        root = curr = null;
        count = 0;
    }


    public T retrieve()
    {
        T data =null;
        if (curr != null)
            data = curr.data;
        return data;
    }


    public void update(T e)
    {
        if (curr != null)
            curr.data = e;
    }


    public boolean find(K key)
    {
        BSTNode<K,T> p = root;

        if(empty())
            return false;

        while(p != null) {
            if(p.key.compareTo(key) == 0) {
                curr = p;
                return true;
            }
            else if(key.compareTo(p.key) < 0)
                p = p.left;
            else
                p = p.right;
        }
        return false;
    }



    public boolean insert(K key, T data)
    {

        if(empty())
        {
            curr = root = new BSTNode <K, T> ( key, data);
            count ++;
            return true;
        }
        BSTNode<K,T> par = null;
        BSTNode<K,T> child  = root;

        while(child != null) {
            if(child.key.compareTo(key) == 0) {
                return false;
            }
            else if(key.compareTo(child.key) < 0)
            {
                par = child;
                child = child.left;
            }
            else
            {
                par = child;
                child = child.right;
            }
        }

        if(key.compareTo(par.key) < 0)
        {
            par.left = new BSTNode <K, T> ( key, data);
            curr = par.left;
        }

        else
        {
            par.right = new BSTNode <K, T> ( key, data);
            curr = par.right;
        }
        count ++;
        return true;
    }

    public boolean remove(K key)
    {
        Boolean removed = new Boolean(false);
        BSTNode<K,T> p;

        p = remove_aux(key, root, removed);
        root = p;

        if (curr.key.compareTo(key) == 0)
            curr = root;
        if (removed)
            count -- ;

        return removed;
    }

    private BSTNode<K,T> remove_aux(K key, BSTNode<K,T> p, boolean flag)
    {
        BSTNode<K,T> q, child = null;
        if(p == null)
            return null;
        if(key.compareTo(p.key ) < 0)
            p.left = remove_aux(key, p.left, flag); //go left
        else if(key.compareTo(p.key) > 0)
            p.right = remove_aux(key, p.right, flag); //go right
        else {

            flag = true;
            if (p.left != null && p.right != null)
            { //two children
                q = find_min(p.right);
                p.key = q.key;
                p.data = q.data;
                p.right = remove_aux(q.key, p.right, flag);
            }
            else
            {
                if (p.right == null) //one child
                    child = p.left;
                else if (p.left == null) //one child
                    child = p.right;
                return child;
            }
        }
        return p;
    }
    private BSTNode<K,T> find_min(BSTNode<K,T> p)
    {
        if(p == null)
            return null;

        while(p.left != null){
            p = p.left;
        }
        return p;
    }

    public void Traverse()
    {
        if (root != null)
            traverseTree(root);
    }

    private void traverseTree (BSTNode<K,T> node  )
    {
        if (node == null)
            return;
        traverseTree( node.left);
        System.out.println(node.data);
        traverseTree( node.right);

    }

    public void TraverseT()
    {
        if (root != null)
            traverseTreeT(root);
    }

    private void traverseTreeT (BSTNode<K,T> node)
    {
        if (node == null)
            return;
        traverseTreeT( node.left );
        if (node.data instanceof BST )
        {
            System.out.println( "Node key ==== "+ node.key);
            ((BST <String,word>) node.data).Traverse();
        }
        else
            System.out.println(node.data);

        traverseTreeT( node.right);

    }

    public LinkedList <T> getData()
    {
        LinkedList <T> data = new LinkedList <T>();
        if (root != null)
            getDataT(root, data);
        return data;
    }
    private void getDataT (BSTNode<K,T> node , LinkedList <T> data )
    {
        if (node == null)
            return;
        getDataT( node.left ,data );
        data.insert(node.data);
        getDataT( node.right, data);
    }


    public LinkedList <K> getKeys()
    {
        LinkedList <K> keys = new LinkedList <K>();
        if (root != null)
            getKeysT(root, keys);
        return keys;
    }
    private void getKeysT (BSTNode<K,T> node , LinkedList <K> keys )
    {
        if (node == null)
            return;
        getKeysT( node.left ,keys );
        keys.insert(node.key);
        getKeysT( node.right, keys);
    }


}

