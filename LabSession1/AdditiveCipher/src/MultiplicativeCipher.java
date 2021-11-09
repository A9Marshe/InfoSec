/*
**Session 2: 10/27/2021
    MULTIPLICATIVE Cipher & affine cipher
*/


// MULTIPLICATIVE CIPHER
public class MultiplicativeCipher {
    // Ci = (Pi * k) mod (alphabet.length)
    // P : original text
    //k: key
    private String alphabet;
    private int key;
    //mod is the length of the alphabet
    private  final int _mod;

    MultiplicativeCipher(){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
        this.key = 3; // default key value (3) is the one used with Caesar Cipher
        this._mod = alphabet.length() - 1;
    }
    MultiplicativeCipher(String alphabet){
        this.alphabet = alphabet;
        this._mod = alphabet.length() - 1;
    }
    MultiplicativeCipher(String alphabet, int key){
        this.alphabet = alphabet;
        this.key = key;
        this._mod = alphabet.length() - 1;
    }

    //encoding function
    private int[] _encode(String message){
    
        //characters validation
        int[] result;
        String validMessage ="";
        for (int i = 0; i < message.length(); i++) {
            if(alphabet.indexOf( message.charAt(i)) >= 0) validMessage += message.charAt(i);
            else { System.out.println("the character (" + message.charAt(i) + ") is not included in alphabet");}
        }
        
        //encoding
        result = new int[validMessage.length()];
        for (int i = 0; i < validMessage.length(); i++) {
            result[i] = alphabet.indexOf( validMessage.charAt(i));
        }
        return result;
    }
    
    //decoding function
    private String _decode(int[] encodedMessage){
        String res = "";
        for (int i : encodedMessage) {
            res += alphabet.charAt(i);  
        }
        return res;
    }

    //multiplicative encryption function
    public String encrypt(String message){
        int[] encodedMessage = _encode(message);
        int[] res = new int[encodedMessage.length];
        for (int i = 0; i < encodedMessage.length; i++) {
            res[i] = (encodedMessage[i] * key) % _mod;
        }
        return _decode(res);
    }

    public int _inverseKey(int myMod , int myKey){
        int m = myMod, num = myKey,t1 = 0, t2 = 1;
        int rem, q, temp;
        while(num > 0){
            q = m/num;
            rem = m % num ;
            temp = t1 - (q * t2);
            t1 = t2;
            t2 = temp;
            m = num;
            num = rem;
        }
        if(m ==1) return  (t1 % myMod )+ myMod;
        return 0;
    }


    /** decryption function
     *  decyption formula :
     * c = m * (k)^-1 mod(alphabet.length) (when >0)
     * c = (m * (k)^-1 mod(alphabet.length)) + alphabet.length (when <0)
    */ 
    public String decrypt(String message){
        //calculating inverse key value;
        int kInverse = _inverseKey(_mod, key);
        if (kInverse != 0){
        int[] encodedMessage= _encode(message);

        for (int i = 0; i < encodedMessage.length; i++) {
            encodedMessage[i] =(encodedMessage[i] * kInverse) % _mod;
        }
        return _decode(encodedMessage);
        }
        return ("Invalid Key");
    }
}
