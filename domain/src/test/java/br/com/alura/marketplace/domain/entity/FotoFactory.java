package br.com.alura.marketplace.domain.entity;

import static lombok.AccessLevel.PRIVATE;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class FotoFactory {

    public static Request criaFoto() {
        return new Request(Foto.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static class Request {

        private final Foto.FotoBuilder builder;

        public Foto comTodosOsCampos() {
            return builder
                    .fileName("file-name-1.jpg")
                    .base64("Y2Fyb2xpbmEgSGVycmVyYQ==")
                    .criadoEm(LocalDateTime.now())
                    .atualizadoEm(LocalDateTime.now())
                    .build();
        }
    }
}
