package util;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public String nombreCorto(String nombre) {
        String f = nombre.trim();
        StringBuilder sb = new StringBuilder();
        int palabras = 0;
        for(int i = 0; i < f.length(); ++i) {
            if (palabras == 3) {
                return sb.toString();
            }
            char caracter = f.charAt(i);
            if(Character.isLetterOrDigit(caracter)) {
                sb.append(caracter);
            } else if (Character.isWhitespace(caracter)){
                sb.append(caracter);
                if(Character.isLetterOrDigit(f.charAt(i+1))){
                    palabras = palabras + 1;
                }
            }  else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    public String eliminarTagsDuplicados(String [] array) {
        Set<String> set = Stream.of(array).collect(Collectors.toSet());
        return set.toString().replace("[", "").replace("]", "").replace(", ", ",");
    }
}
