

public class AdditiveCipher{
    /**
     * Additive encryption formula:
     * C = m + k mod(alphabet.length)
     * C: cipher text
     * m: message
     * k: key
     * alphabet: the alphabet used to represent messages and cipherTexts
     * !- This is the Naive approach to additiveCipher😄
     */
    int k = 3; // default key value (3) is the one used with Caesar Cipher
    //Modern english alphabet
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    //mod is the length of the alphabet
    private  final int _mod = alphabet.length() - 1;

    /** 
     * encrypt function is used to encrypt a Message,
     * encrypt uses encode and decode functions
     *(message)-> encrypt(message) -> decode(CipherText) 
    */
    public String encrypt(String message, int key){
        String res ="";
        int[] encode = new int[message.length()];
        int index;
        for (int i = 0; i < message.length(); i++) {
            index = alphabet.indexOf(message.charAt(i));
            if (index > 0) encode[i] = index;
            else { System.out.println(message.charAt(i) + " is not included in alphabet");}
        }

        //decoding, from numbers to letters
        for (int i : encode) {
            res += alphabet.indexOf(i + key % _mod );
        }
            return res;
    }
}