class WordDictionary {

   TrieNode rootNode;

    public WordDictionary() {
        //initialize root node
        rootNode = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = rootNode;
        //iterate through the Word
        for(int i= 0;i<word.length();i++){
            //if the charcter in the word doesn't present in the node,then add it
            if(node.children[word.charAt(i)-'a']==null){
                node.children[word.charAt(i)-'a'] = new TrieNode();
            }
            //find the child and assign as the next node to check
            node = node.children[word.charAt(i)-'a']
        }
        //after the word gets added,then mark it as a word
        node.isWord = true;
    }
    
    public boolean search(String word) {
        return search(rootNode,word,0);
    }

    public boolean search(TrieNode node,String word,int index) {
        if(word.length() == index) return node.isWord;
        if(node==null) return false;

        if(word.charAt(index)!='.' && node.children[word.charAt(index)-'a']==null){
            return false;
        }
        if(Word.charAt(index)=='.'){
            boolean flag = false;
            for(int i=0;i<26;i++){
                if(node.children[i]!=null){
                    TrieNode temp = node.childs[i];
                    flag = flag || search(temp,key,index+1);
                    if(flag)return flag;
                }
            }
            return flag;
    } else{
        node = node.children[word.charAt(index)-'a'];
        if(node!= null) return search(node,word,index+1);
        else return false;
    }

    }
}
class TrieNode{
    //26 characters only lower english charcter
    TrieNode[] children = new TrieNode[26];
    boolean isWord;

}
/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */