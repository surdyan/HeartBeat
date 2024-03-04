package com.example.demo;

public class Server3 {
    public static void main(String[] args) {
        HeartbeatServer server = new HeartbeatServer("Server3", 6003, new int[]{6001, 6002});
        server.start();
    }
}
