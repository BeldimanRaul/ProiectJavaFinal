package com.example.proiectjavafinal;

import java.util.HashSet;
import java.util.Set;

public class ManagerUtilizatori {
    private static Set<String> utilizatori = new HashSet<String>();

    public static boolean adaugaUtilizatori(User user) {
        if (utilizatori.contains(user.getUsername())) {
            return false;
        }else{
            utilizatori.add(user.getUsername());
            return true;
        }
    }

    public static boolean verificaUnicitateUsername(String username) {
        return !utilizatori.contains(username);
    }
}
