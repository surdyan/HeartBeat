package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HeartbeatServer {
    private String serverName;
    private int port;
    private int[] otherPorts;

    public HeartbeatServer(String serverName, int port, int[] otherPorts) {
        this.serverName = serverName;
        this.port = port;
        this.otherPorts = otherPorts;
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(serverName + " is running on port " + port + "...");

            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.scheduleAtFixedRate(this::sendHeartbeat, 0, 5, TimeUnit.SECONDS);

            new Thread(() -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        new ClientHandler(socket).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendHeartbeat() {
        for (int otherPort : otherPorts) {
            try {
                // Verificăm dacă portul celorlalte servere este deschis
                if (isPortOpen("localhost", otherPort)) {
                    Socket socket = new Socket("localhost", otherPort);
                    String message = serverName + " heartbeat";
                    System.out.println(getTimestamp() + " - Sent message to " + otherPort + ": " + message);
                    socket.getOutputStream().write((message + "\n").getBytes());
                    socket.close();
                }
            } catch (IOException e) {
                System.err.println("Error sending heartbeat to port " + otherPort);
            }
        }
    }

    private boolean isPortOpen(String host, int port) {
        try (Socket ignored = new Socket(host, port)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static class ClientHandler extends Thread {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(getTimestamp() + " - Received message from " + socket.getPort() + ": " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}
