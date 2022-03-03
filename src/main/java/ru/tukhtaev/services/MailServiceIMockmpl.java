package ru.tukhtaev.services;

public class MailServiceIMockmpl implements MailService {
    @Override
    public void sendMail(String email, String message) {
        System.out.println("Сообщение <" + message + "> отправлено на: " + email);
    }
}
