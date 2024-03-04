Documentație pentru Proiectul Heartbeat

Descriere

Proiectul Heartbeat este un sistem simplu de comunicare între servere, care utilizează mesaje de tip heartbeat pentru a menține o conexiune activă între ele. Acest proiect conține trei servere care se pot conecta și comunica între ele, trimițând și primind mesaje de tip heartbeat la intervale regulate.

Structura proiectului
* Server1.java: Clasa principală pentru Server 1.
* Server2.java: Clasa principală pentru Server 2.
* Server3.java: Clasa principală pentru Server 3.
* HeartbeatServer.java: Clasa care implementează funcționalitatea de bază a serverului și gestionarea comunicării.

Exemplu de utilizare
Server1.java

public class Server1 {
    public static void main(String[] args) {
        // Crearea și pornirea serverului Server1
        HeartbeatServer server = new HeartbeatServer("Server1", 6001, new int[]{6002, 6003});
        server.start();
    }
}

* Acest cod crează și pornește Server1, care va trimite heartbeat-uri către Server2 și Server3 la intervale regulate.

HeartbeatServer.java

public class HeartbeatServer {
    // Constructor și alte metode...

    public void start() {
        // Inițializarea și pornirea serverului
    }

    private void sendHeartbeat() {
        // Trimite heartbeat-uri la celelalte servere
    }

    static class ClientHandler extends Thread {
        // Gestionarea mesajelor primite de la celelalte servere
    }

    private static String getTimestamp() {
        // Obține un timestamp formatat
    }
}

* Clasa HeartbeatServer implementează funcționalitatea de bază a serverului, inclusiv trimiterea heartbeat-urilor și gestionarea mesajelor primite de la celelalte servere.

Funcționalități cheie
* Trimiterea heartbeat-urilor către celelalte servere la intervale regulate.
* Primirea și gestionarea mesajelor primite de la celelalte servere.
* Afișarea timestamp-ului pentru fiecare eveniment (trimitere/primire a mesajelor).

Note
* Pentru a rula proiectul, fiecare server trebuie să fie pornit separat.
* Mesajele de tip heartbeat sunt trimise și primite la fiecare 5 secunde.
