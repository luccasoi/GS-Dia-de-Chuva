package br.fiap.luccas563794.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "GS174_LEITURA")
@Getter @Setter @NoArgsConstructor
public class Leitura {

    public static final String NORMAL = "NORMAL";
    public static final String ATENCAO = "ATENCAO";
    public static final String ALERTA = "ALERTA";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "LAMINA_CM", nullable = false)
    private Integer laminaCm;

    @Column(name = "STATUS", nullable = false, length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRECHO_ID", nullable = false)
    private Trecho trecho;
}
