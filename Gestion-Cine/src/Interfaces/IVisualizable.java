package Interfaces;

import java.time.LocalDate;

public interface IVisualizable {
    void verCartelera();
    void verReservas(String dni, LocalDate dia);
    void verFunciones(LocalDate dia);
}
