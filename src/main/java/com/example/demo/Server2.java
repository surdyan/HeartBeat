package com.example.demo;

public class Server2 {
    public static void main(String[] args) {
        HeartbeatServer server = new HeartbeatServer("Server2", 6002, new int[]{6001, 6003});
        server.start();
    }
}
