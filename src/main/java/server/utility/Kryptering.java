package server.utility;

import com.google.gson.Gson;

public class Kryptering {

    public static String encryptdecrypt(String toBeEncryptedDecrypted) {

        //Vi vælger selv værdierne til nøglen
        char[] key = {'L', 'O', 'L'};
        //En StringBuilder er en klasse, der gør det muligt at ændre en string
        StringBuilder isEncryptedDecrypted = new StringBuilder();

        for (int i = 0; i < toBeEncryptedDecrypted.length(); i++) {
            isEncryptedDecrypted.append((char) (toBeEncryptedDecrypted.charAt(i) ^ key[i % key.length]));
        }

        return isEncryptedDecrypted.toString();

    }


    //metode til at dekryptere
    public static String decryptencrypt(String toBeDecryptedEncrypted) {


        toBeDecryptedEncrypted = new Gson().fromJson(toBeDecryptedEncrypted, String.class);

        //Vi vælger selv værdierne til nøglen
        char[] key = {'L', 'O', 'L'};

        //En StringBuilder er en klasse, der gør det muligt at ændre en string
        StringBuilder isDecryptedEncrypted = new StringBuilder();

        for (int i = 0; i < toBeDecryptedEncrypted.length(); i++) {
            isDecryptedEncrypted.append((char) (toBeDecryptedEncrypted.charAt(i) ^ key[i % key.length]));
        }

        return isDecryptedEncrypted.toString();

    }


}





