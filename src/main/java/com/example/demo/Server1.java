package com.example.demo;

public class Server1 {
    public static void main(String[] args) {
        HeartbeatServer server = new HeartbeatServer("Server1", 6001, new int[]{6002, 6003});
        server.start();
    }
}
