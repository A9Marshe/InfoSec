/*
**Session 2: 10/27/2021
    MULTIPLICATIVE Cipher & affine cipher
*/


// Affine CIPHER
public class AffineCipher {
    //class members
    private String alphabet;
    private int key1;
    private int key2;
    private static AdditiveCipher additiveWorker;
    private static MultiplicativeCipher multiplicativeWorker;

    //default constructor with predefined alphabet and key
    AffineCipher(){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.key1 = 3;
        this.key2 = 3;
        additiveWorker = new AdditiveCipher(alphabet, key1);
        multiplicativeWorker = new MultiplicativeCipher(alphabet, key2);
    }

    AffineCipher(String alphabet){
        this.alphabet = alphabet;
        this.key1 = 3;
        this.key2 = 3;
        additiveWorker = new AdditiveCipher(alphabet, key1);
        multiplicativeWorker = new MultiplicativeCipher(alphabet, key2);
    }
    
    AffineCipher(int key1, int key2){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.key1 = key1;
        this.key2 = key2;
        additiveWorker = new AdditiveCipher(alphabet, key1);
        multiplicativeWorker = new MultiplicativeCipher(alphabet, key2);
        System.out.println("additive key is: " + key1 + " \n multiplicative key is: " + key2 + "alphabet is: " + alphabet);
    }

    AffineCipher(String alphabet, int key1, int key2){
        this.alphabet = alphabet;
        this.key1 = key1;
        this.key2 = key2;
        additiveWorker = new AdditiveCipher(alphabet, key1);
        multiplicativeWorker = new MultiplicativeCipher(alphabet, key2);
    }

    public String encrypt(String message){
        //Formula: C = P *k1 + k2 mod26
        return additiveWorker.encrypt(multiplicativeWorker.encrypt(message));
    }
    public String decrypt(String message){
        return multiplicativeWorker.decrypt(additiveWorker.decrypt(message));
    }
}
