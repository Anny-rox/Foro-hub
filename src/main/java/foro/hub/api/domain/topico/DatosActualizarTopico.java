package foro.hub.api.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
    @NotBlank
    String titulo,
    @NotBlank
    String mensaje,
    @NotBlank
    String autor,
    @NotBlank
    String curso
) {
}
