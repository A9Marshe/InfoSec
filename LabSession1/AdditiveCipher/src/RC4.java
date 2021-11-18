
public class RC4 {
    private String alphabet;
    RC4(){
        this.alphabet = "abcdefghijklmnopqrstuvwxyz";
    }

    private String _init(String key){
        
    }

    private String _PermutateKey(){

    }

    private String _generateKeyStream(){

    }

    //characters validation
    private String validate(String message){
        String validMessage ="";
        for (int i = 0; i < message.length(); i++) {

            if(alphabet.indexOf( message.charAt(i)) >= 0) 
                validMessage += message.charAt(i);

            else { 
                System.out.println("the character (" + message.charAt(i) + ") is not included in alphabet");
            }
        }
        return validMessage;
    }
     //encoding function
     private int[] _encode(String message){
        String validMessage = validate(message);
        int[] result = new int[validMessage.length()];
        for (int i = 0; i < validMessage.length(); i++) {
            result[i] = alphabet.indexOf( validMessage.charAt(i));
        }
        return result;
    }
    
    //decoding function
    private String _decode(int[] encodedMessage){
        String result = "";
        for (int i = 0; i < encodedMessage.length; i++) {
            result += alphabet.charAt(encodedMessage[i]); 
        }
        return result;
    } 
}

