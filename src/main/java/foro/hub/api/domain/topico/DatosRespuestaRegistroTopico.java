package foro.hub.api.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaRegistroTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaDeCreacion,
    String autor,
    String curso
) {
}
