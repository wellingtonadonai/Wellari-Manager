package com.wellari.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {

    private int status;
    private List<String> mensagens;
    private LocalDateTime dataHora;

    public ErroResposta(int status, List<String> mensagens, LocalDateTime dataHora) {
        this.status = status;
        this.mensagens = mensagens;
        this.dataHora = dataHora;
    }

    public int getStatus() {
        return status;
    }

    public List<String> getMensagem() {
        return mensagens;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
