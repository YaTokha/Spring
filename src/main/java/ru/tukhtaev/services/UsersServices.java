package ru.tukhtaev.services;

public interface UsersServices {
    void signUp(String firstName, String lastName, String email, String password);
    void signIn(String email, String password);
}
