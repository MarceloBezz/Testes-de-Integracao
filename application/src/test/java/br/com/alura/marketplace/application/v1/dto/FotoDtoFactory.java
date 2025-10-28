package br.com.alura.marketplace.application.v1.dto;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public final class FotoDtoFactory {

    public static Request criaFotoDtoRequest() {
        return new Request(FotoDto.Request.builder());
    }

    @RequiredArgsConstructor(access = PRIVATE)
    public static class Request {
        private final FotoDto.Request.RequestBuilder builder;

        public FotoDto.Request comTodosOsCampos() {
            return builder.fileName("file-name-1.jpg")
                    .base64("Y2Fyb2xpbmEgSGVycmVyYQ==")
                    .build();
        }
    }
}
