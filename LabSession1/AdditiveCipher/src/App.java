
public class App {
    public static void main(String[] args) throws Exception {
        // //Session 1
        // AdditiveCipher worker = new AdditiveCipher();
        // System.out.println(worker.decrypt(worker.encrypt("aba1c")));
        
        //Session 2
        //  MultiplicativeCipher Multiplicativeworker = new MultiplicativeCipher();
        // //encrypting
        // //obtaining inverse of key to decrypt
        // System.out.println("should work " +worker2.inverseKey(26,5));
        // System.out.println("should not work " +worker2.inverseKey(2,26));
        // System.out.println(Multiplicativeworker.encrypt("hello"));
        // System.out.println(Multiplicativeworker.decrypt("vmiir"));

        AffineCipher affineWorker = new AffineCipher();
        System.out.println(affineWorker.encrypt("hel1lo"));
        System.out.println(affineWorker.decrypt("ypllu"));
    }
}
