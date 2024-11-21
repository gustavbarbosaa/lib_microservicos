package br.com.catolica.ms_email.services;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    @RabbitListener(queues = "${spring.rabbitmq.queue.name}")
    public void sendMail(String email) {
        try {
            // Envia o e-mail sem necessidade de resposta via RabbitMQ
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("gustavoaraujohab@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setSubject("Empréstimo realizado com sucesso!");
            mailMessage.setText("Parabéns, você acaba de realizar um empréstimo!");
            javaMailSender.send(mailMessage);
        } catch (Exception e) {
            e.printStackTrace();  // Loga a exceção para análise futura
        }
    }
}
