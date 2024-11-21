package br.com.catolica.ms_email.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDTO {
    private String mailSender;
    private String mailRecipient;
    private String body;
    private String title;
}
